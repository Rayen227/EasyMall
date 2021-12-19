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
	//--------------����---------------------
    public static final String FROM = "LiZhiWen_W@163.com";//�����˵�email
    public static final String PWD = "Lizhiwen@2021";//����������--��������
    public static final String URL = "http://localhost:8080/EasyMall_2";//��Ŀ��ҳ
    public static final int TIMELIMIT = 1000*60*60*24; //�����ʼ�����ʱ��24Сʱ
    public static final String TITLE = "iClass�˻������ʼ�";
    public static final String HOST = "smtp.163.com";
    public static final String SMTP = "smtp";
//---------------�Զ��庯��-----------------
    public static User activateMail(User u) throws AddressException, MessagingException, NoSuchAlgorithmException {
        
        String to = u.getEmail();
        String code = u.getCode();
        
        //���͵���������
        String content = "<p>���� O(��_��)O~~<br><br>��ӭ����Easymall!<br><br>�ʻ���Ҫ�������ʹ�ã��Ͻ������ΪEasymall!��ʽ��һԱ��:)<br><br>����24Сʱ�ڵ��������������������ʻ���"
        +"<br><a href='"+URL+"/activatemail/?code="+code+"&email="+to+"'>"
        +URL+"/activatemail/?code="+code+"&email="+to+"</a></p>";
        //���÷����������
        MailUtil.sendMail(to, TITLE, content);
        return u;
    }
//---------------�����ʼ�-------------------
    public static void sendMail(String to,String title,String content) throws AddressException, MessagingException {

    Properties props = new Properties(); //���Լ���һ�������ļ�  
    // ʹ��smtp�����ʼ�����Э��  
    props.put("mail.smtp.host", HOST);//�洢�����ʼ�����������Ϣ  
    props.put("mail.smtp.auth", "true");//ͬʱͨ����֤  
//    Session session = Session.getInstance(props);//���������½�һ���ʼ��Ự  
 // 1.��ȡĬ��session����
    Session session = Session.getDefaultInstance(props, new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(FROM, "AKITIKFCAMAFZKKI"); // �����������˺š���Ȩ��
        }
    });
    //session.setDebug(true); //�������ӡһЩ������Ϣ��  
    MimeMessage message = new MimeMessage(session);//���ʼ��Ự�½�һ����Ϣ����  
    message.setFrom(new InternetAddress(FROM));//���÷����˵ĵ�ַ  
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//�����ռ���,���������������ΪTO  
    message.setSubject(title);//���ñ���  
    //�����ż�����  
    //message.setText(mailContent); //���� ���ı� �ʼ� todo  
    message.setContent(content, "text/html;charset=gbk"); //����HTML�ʼ���������ʽ�ȽϷḻ  
    message.setSentDate(new Date());//���÷���ʱ��  
    message.saveChanges();//�洢�ʼ���Ϣ  
    //�����ʼ�  
    Transport transport = session.getTransport(SMTP);  
    //Transport transport = session.getTransport();  
    transport.connect(FROM, PWD);
    transport.sendMessage(message, message.getAllRecipients());//�����ʼ�,���еڶ�����������������õ��ռ��˵�ַ  
    transport.close();  
}
}
