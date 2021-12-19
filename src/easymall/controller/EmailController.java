package easymall.controller;

import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.User;
import easymall.service.UserService;

@Controller
public class EmailController {
	
	@Autowired
    private UserService userService;
	
	 @RequestMapping("/activatemail")
	 public String activatemail(HttpServletRequest request,Model model) throws IOException {
		//获取激活参数
	    String email = request.getParameter("email");
	    String code = request.getParameter("code");
	    
	    User user = userService.findByemail(email);
	  //如果能找到并且状态为未激活且code相等则激活
	    if(user!=null&&user.getState()==0&&code.equals(user.getCode())) { 
	    	userService.updateUser(email); //调用service层的方法将对应email用户state设为1
	    }
	    model.addAttribute("messageError","已经成功激活，现在登录吧");//激活成功就跳到登录界面
		return "login";
	 }
}
