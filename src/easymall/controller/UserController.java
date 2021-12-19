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
			model.addAttribute("messageError", "��������û�������");
			return "login";
		}
		else if(userData.getState()==0) {
			model.addAttribute("messageError", "�����û���û�м�����¼���������Ӽ��");
			return "login";
		}
		else if(userData.getPassword().equals(user.getPassword())){
			session.setAttribute("user", userData);
			return "redirect:/index.jsp";
		}
		else {
			model.addAttribute("messageError", "��������������");
			return "login";
		}
	}
	
	@RequestMapping("/checkUser")
	public void checkUser(String username,HttpServletResponse response) throws IOException {
		if(userService.login(username)!=null) {
			response.getWriter().print("��������û����ѱ�ע�ᣡ");
		}else {
			response.getWriter().print("��ϲ����������û�����ʹ�ã�");
		}
	}
	@RequestMapping("/regist")
	public String regist(User user ,String valistr,HttpSession session,Model model) throws AddressException, NoSuchAlgorithmException, MessagingException {
		Long curTime = System.currentTimeMillis();
		String code= String.valueOf(curTime);
		user.setState(0);
		user.setCode(code);
		
		if(user.getUsername()==null||"".equals((user.getUsername()))){
			model.addAttribute("msg", "�û�������Ϊ��");
			return "regist";
		}
		if(user.getPassword()==null||"".equals((user.getPassword()))){
			model.addAttribute("msg", "���벻��Ϊ��");
			return "regist";
		}
		if("".equals(valistr)||valistr==null){
			model.addAttribute("msg", "��֤�벻��Ϊ��");
			return "regist";
		}
		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())){
			model.addAttribute("msg", "��֤�벻��ȷ");
			return "regist";
		}
		if(userService.login(user.getUsername())!=null){
			model.addAttribute("msg", "��֤�벻��ȷ");
			return "regist";
		}
		if(userService.regist(user)>0){
			new MailUtil().activateMail(user);
			model.addAttribute("messageError", "ע��ɹ�,��ǰ��������֤���");
			return "login";
		}else {
			model.addAttribute("msg", "ע��ʧ�ܣ�");
			return "regist";
		}
	}
}
