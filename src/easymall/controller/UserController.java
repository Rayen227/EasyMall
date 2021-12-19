package easymall.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.User;
import easymall.service.UserService;
import easymall.util.MailUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user,HttpSession session, Model model) {
		User userData= userService.login(user.getUsername());
		if(userData==null){
			model.addAttribute("messageError", "您输入的用户名错误！");
			return "login";
		}
		else if(userData.getState()==0) {
			model.addAttribute("messageError", "您的用户还没有激活，请登录邮箱点击链接激活！");
			return "login";
		}
		else if(userData.getPassword().equals(user.getPassword())){
			session.setAttribute("user", userData);
			return "redirect:/index.jsp";
		}
		else {
			model.addAttribute("messageError", "您输入的密码错误！");
			return "login";
		}
	}
	
	@RequestMapping("/checkUser")
	public void checkUser(String username,HttpServletResponse response) throws IOException {
		if(userService.login(username)!=null) {
			response.getWriter().print("您输入的用户名已被注册！");
		}else {
			response.getWriter().print("恭喜，您输入的用户名可使用！");
		}
	}
	@RequestMapping("/regist")
	public String regist(User user ,String valistr,HttpSession session,Model model) throws AddressException, NoSuchAlgorithmException, MessagingException {
		Long curTime = System.currentTimeMillis();
		String code= String.valueOf(curTime);
		user.setState(0);
		user.setCode(code);
		
		if(user.getUsername()==null||"".equals((user.getUsername()))){
			model.addAttribute("msg", "用户名不能为空");
			return "regist";
		}
		if(user.getPassword()==null||"".equals((user.getPassword()))){
			model.addAttribute("msg", "密码不能为空");
			return "regist";
		}
		if("".equals(valistr)||valistr==null){
			model.addAttribute("msg", "验证码不能为空");
			return "regist";
		}
		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())){
			model.addAttribute("msg", "验证码不正确");
			return "regist";
		}
		if(userService.login(user.getUsername())!=null){
			model.addAttribute("msg", "验证码不正确");
			return "regist";
		}
		if(userService.regist(user)>0){
			new MailUtil().activateMail(user);
			model.addAttribute("messageError", "注册成功,请前往邮箱验证激活！");
			return "login";
		}else {
			model.addAttribute("msg", "注册失败！");
			return "regist";
		}
	}
}
