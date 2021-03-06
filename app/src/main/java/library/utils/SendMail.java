package library.utils;

import com.hnsi.oa.hnsi_oa.application.interfaces.IAsyncLoadListener;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public final class SendMail {//extends AsyncTask<Void, Void, Boolean>
	
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	// 你自己的邮箱，需要开启IMAP/SMTP服务
	private final static String DEFAULT_FROME_MAIL_ADDRESS = "360659876@qq.com";

	// 使用QQ邮箱时使用QQ邮箱授权码 neghaojqapvtcabd
	private final static String DEFAULT_FROM_MAIL_PASSWORD = "neghaojqapvtcabd";
	
	// 发送方昵称, 在对方邮箱中显示的名称
	private final static String DEFAULT_FROM_MAIL_NICK_NAME = "hnsi_oa";
	
	private static String DEFAULT_MAIL_TITLE = "这是来自Android客户端的反馈";
	
	private static String[] DEFAULT_TO_MAIL_ADDRESSES = new String[] {
		"360659876@qq.com"
	};
	
	public static void sendmail(String mMailMessage, final IAsyncLoadListener<Void> listener) {
		
		Properties props = new Properties();
		// 开启debug调试  
        props.setProperty("mail.debug", "true");  
        // 发送服务器需要身份验证  
        props.setProperty("mail.smtp.auth", "true");  
        // 设置邮件服务器主机名  
        props.setProperty("mail.host", "smtp.qq.com");  
        // 发送邮件协议名称  
        props.setProperty("mail.transport.protocol", "smtp");  
		
		// 设置SSL加密
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port",  "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
		
		Session session = Session.getInstance(props, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(DEFAULT_FROME_MAIL_ADDRESS, DEFAULT_FROM_MAIL_PASSWORD);
					}
				});
		session.setDebug(false);
		
		final Message msg = new MimeMessage(session);
		try {
			final String nickName = MimeUtility.encodeText(DEFAULT_FROM_MAIL_NICK_NAME);

			msg.setFrom(new InternetAddress(nickName + " <" + DEFAULT_FROME_MAIL_ADDRESS + ">"));
			msg.setSubject(DEFAULT_MAIL_TITLE);
			msg.setText(mMailMessage);
			
//			//支持Html
//			msg.setDataHandler(new DataHandler(new ByteArrayDataSource(mMailMessage, "text/html")));
			
			// 添加多个接收邮箱地址
			int len = DEFAULT_TO_MAIL_ADDRESSES.length;
			final InternetAddress addresses[] = new InternetAddress[len];
			for (int i = 0; i < len; i++) {
				addresses[i] = new InternetAddress(DEFAULT_TO_MAIL_ADDRESSES[i]);
			}
			msg.addRecipients(Message.RecipientType.TO, addresses);
			
			new Thread(){
				@Override
				public void run() {
					// 发送
					try {
						Transport.send(msg, addresses);
					} catch (MessagingException e) {
						e.printStackTrace();
						listener.onFailure(e.toString());
					}
					listener.onSuccess(null);
				}
			}.start();
			
		} catch (Exception e) {

		} 
	}

}
