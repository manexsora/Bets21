package mail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {


	public String sendEmail(String email) {

		String  d_email = "betszkt@gmail.com",
				d_password = "manexmanex",
				d_host = "smtp.gmail.com",
				d_port  = "465",
				m_to = email,
				m_subject = "Change password verification code",
				code = getAlphaNumericString(8),
				m_text = "Your Bets21 password change code: " + code;
		Properties props = new Properties();
		props.put("mail.smtp.user", d_email);
		props.put("mail.smtp.host", d_host);
		props.put("mail.smtp.port", d_port);
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", d_port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, null);
		session.setDebug(true);

		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setSubject(m_subject);
			msg.setFrom(new InternetAddress(d_email));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
			msg.setText(m_text);

			Transport transport = session.getTransport("smtps");
			transport.connect(d_host, Integer.valueOf(d_port), d_email, d_password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

		} catch (AddressException e) {
			e.printStackTrace();

		} catch (MessagingException e) {
			e.printStackTrace();

		}
		return code;

	}

	public static String getAlphaNumericString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
}
