package easymall.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.User;
import easymall.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminUserController{
	@Autowired
	private UserService userService;
	@RequestMapping("/adminlogin")
	public String adminLogin(User user,HttpSession session,Model model){	
		User user1=userService.login(user.getUsername());
		if(user1==null){
			model.addAttribute("message","您输入的用户名错误");
			return "/admin/adminlogin";	
		}
		else if(user1.getUsertype()!=0){
			model.addAttribute("message","请输入后台管理账号");
			return "/admin/adminlogin";	
		}
		else if(user1.getPassword().equals(user.getPassword())){
			session.setAttribute("user",user1);
			return "/admin/manage";	
		}
		else{
			model.addAttribute("message","您输入的密码错误");
			return "/admin/adminlogin";	
		}	
	}
}

