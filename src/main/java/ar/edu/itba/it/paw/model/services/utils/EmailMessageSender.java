package ar.edu.itba.it.paw.model.services.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailMessageSender implements MessageSender {

	// TODO: Remove with log4j
	private boolean verbose = false;

	public boolean send(final Message m) {
		try {
			if (this.verbose) {
				System.out.println("Trying to send email");
			}
			final Message message = new MimeMessage(this.getSession());
			message.setFrom(InternetAddress.parse("chinosrealstate@gmail.com")[0]);
			message.setRecipients(RecipientType.TO,
					m.getRecipients(RecipientType.TO));
			message.setRecipients(RecipientType.CC,
					m.getRecipients(RecipientType.CC));
			message.setRecipients(RecipientType.BCC,
					m.getRecipients(RecipientType.BCC));
			message.setText((String) m.getContent());
			message.setSubject(m.getSubject());
			Transport.send(message);
			if (this.verbose) {
				System.out.println("Email sent");
			}
			return true;
		} catch (final MessagingException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Session getSession() throws IOException {
		final String username = "chinusRealState@gmail.com";
		final String password = "chino4ever";

		final Properties props = new Properties();
		FileInputStream inputStream = null;

		// TODO: Resolver cómo se apunta desde el container
		try {
			inputStream = new FileInputStream(
					"src/main/webapp/WEB-INF/email.properties");
			props.load(inputStream);
		} catch (final Exception excp) {
			try {
				inputStream = new FileInputStream("WEB-INF/email.properties");
				props.load(inputStream);
			} catch (final Exception e) {
				excp.printStackTrace();
				e.printStackTrace();
			}
		}

		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}
}
