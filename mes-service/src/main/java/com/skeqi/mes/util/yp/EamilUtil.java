package com.skeqi.mes.util.yp;

import com.sun.mail.util.MailConnectException;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author yinp
 *
 */
public class EamilUtil {

	public static String SENDER_EAMIL = "15594284@qq.com";
	public static String THE_SERVER = "smtp.qq.com";
	public static String AUTHORIZATION_CODE = "vyjpsnisallebhbj";

	/**
	 * 发送邮件
	 *
	 * @param receiver 接收人
	 * @param title    邮件标题
	 * @param content  邮件内容
	 * @throws Exception
	 */
	public static void sendEamil(String receiver, String title, String content) throws Exception {

		try {
			// 创建一个配置文件并保存
			Properties properties = new Properties();

			properties.setProperty("mail.host", THE_SERVER);

			properties.setProperty("mail.transport.protocol", "smtp");

			properties.setProperty("mail.smtp.auth", "true");

			// QQ存在一个特性设置SSL加密
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);

			// 创建一个session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SENDER_EAMIL, AUTHORIZATION_CODE);
				}
			});

			// 开启debug模式
			session.setDebug(true);

			// 获取连接对象
			Transport transport = session.getTransport();

			// 连接服务器
			transport.connect(THE_SERVER, SENDER_EAMIL, AUTHORIZATION_CODE);

			// 创建邮件对象
			MimeMessage mimeMessage = new MimeMessage(session);

			// 邮件发送人
			mimeMessage.setFrom(new InternetAddress(SENDER_EAMIL));

			// 邮件接收人
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			// 邮件标题
			mimeMessage.setSubject(title);

			// 邮件内容
			mimeMessage.setContent("欢迎使用琦航系统：" + content, "text/html;charset=UTF-8");

			// 发送邮件
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

			// 关闭连接
			transport.close();
		} catch (AuthenticationFailedException e1) {
			e1.printStackTrace();
			throw new Exception("登录失败。授权码已过期");
		} catch (MailConnectException e2) {
			e2.printStackTrace();
			throw new Exception("无法连接到主机," + THE_SERVER);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("发送失败了");
		}
	}

	public static void main(String[] args) throws Exception {
		sendEamil("yinp0000@163.com", "	琦航云告警系统", "xxx发生操作异常");
	}

}
