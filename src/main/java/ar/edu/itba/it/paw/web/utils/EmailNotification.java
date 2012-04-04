package ar.edu.itba.it.paw.web.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mai

public class EmailNotification {

	public void sendEmail(final String email, final String body) {

		final String username = "dinuxPropiedades@gmail.com";
		final String password = "Paw42012";

		final Properties props = new Properties();
		FileInputStream MyInputStream = null;
		try {
			MyInputStream = new FileInputStream(
					"src/main/java/ar/edu/itba/it/paw/services/mail.properties");
		} catch (final FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			props.load(MyInputStream);
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Hay alguien interesado en su propiedad");
			message.setText(body);

			Transport.send(message);

		} catch (final MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
