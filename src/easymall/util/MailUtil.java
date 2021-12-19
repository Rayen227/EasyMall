package easymall.util;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import easymall.po.User;

public class MailUtil {
	//--------------参数---------------------
    public static final String FROM = "LiZhiWen_W@163.com";//发件人的email
    public static final String PWD = "Lizhiwen@2021";//发件人密码--邮箱密码
    public static final String URL = "http://localhost:8080/EasyMall_2";//项目主页
    public static final int TIMELIMIT = 1000*60*60*24; //激活邮件过期时间24小时
    public static final String TITLE = "iClass账户激活邮件";
    public static final String HOST = "smtp.163.com";
    public static final String SMTP = "smtp";
//---------------自定义函数-----------------
    public static User activateMail(User u) throws AddressException, MessagingException, NoSuchAlgorithmException {
        
        String to = u.getEmail();
        String code = u.getCode();
        
        //发送的邮箱内容
        String content = "<p>您好 O(∩_∩)O~~<br><br>欢迎加入Easymall!<br><br>帐户需要激活才能使用，赶紧激活成为Easymall!正式的一员吧:)<br><br>请在24小时内点击下面的链接立即激活帐户："
        +"<br><a href='"+URL+"/activatemail/?code="+code+"&email="+to+"'>"
        +URL+"/activatemail/?code="+code+"&email="+to+"</a></p>";
        //调用发送邮箱服务
        MailUtil.sendMail(to, TITLE, content);
        return u;
    }
//---------------发送邮件-------------------
    public static void sendMail(String to,String title,String content) throws AddressException, MessagingException {

    Properties props = new Properties(); //可以加载一个配置文件  
    // 使用smtp：简单邮件传输协议  
    props.put("mail.smtp.host", HOST);//存储发送邮件服务器的信息  
    props.put("mail.smtp.auth", "true");//同时通过验证  
//    Session session = Session.getInstance(props);//根据属性新建一个邮件会话  
 // 1.获取默认session对象
    Session session = Session.getDefaultInstance(props, new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(FROM, "AKITIKFCAMAFZKKI"); // 发件人邮箱账号、授权码
        }
    });
    //session.setDebug(true); //有他会打印一些调试信息。  
    MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象  
    message.setFrom(new InternetAddress(FROM));//设置发件人的地址  
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置收件人,并设置其接收类型为TO  
    message.setSubject(title);//设置标题  
    //设置信件内容  
    //message.setText(mailContent); //发送 纯文本 邮件 todo  
    message.setContent(content, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富  
    message.setSentDate(new Date());//设置发信时间  
    message.saveChanges();//存储邮件信息  
    //发送邮件  
    Transport transport = session.getTransport(SMTP);  
    //Transport transport = session.getTransport();  
    transport.connect(FROM, PWD);
    transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址  
    transport.close();  
}
}
