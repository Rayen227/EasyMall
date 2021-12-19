package easymall.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/login")
	public String toLogin(HttpSession session) {
		User user=(User)session.getAttribute("user");
		if(session.getAttribute("user")!=null&&user.getUsertype()==0)
			return "/admin/manage";
		else
			return "admin/adminlogin";
	}
}
