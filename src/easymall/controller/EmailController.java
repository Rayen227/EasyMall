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
		//��ȡ�������
	    String email = request.getParameter("email");
	    String code = request.getParameter("code");
	    
	    User user = userService.findByemail(email);
	  //������ҵ�����״̬Ϊδ������code����򼤻�
	    if(user!=null&&user.getState()==0&&code.equals(user.getCode())) { 
	    	userService.updateUser(email); //����service��ķ�������Ӧemail�û�state��Ϊ1
	    }
	    model.addAttribute("messageError","�Ѿ��ɹ�������ڵ�¼��");//����ɹ���������¼����
		return "login";
	 }
}
