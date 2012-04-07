package ar.edu.itba.it.paw.model.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;

public class EmailService {

	public void sendEmail(final User user, final Property property) {

		final String username = "chinusRealState@gmail.com";
		final String password = "chino4ever";

		final Properties props = new Properties();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(
					"src/main/java/ar/edu/itba/it/paw/services/emailservice.properties");
		} catch (final FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			props.load(inputStream);
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getMail()));
			message.setSubject("Notificacion de ChinuProp para "
					+ user.getUsername());
			message.setText("Usted tiene una nueva solicitud de contacto para su propiedad en "
					+ property.getAddress()
					+ ", "
					+ property.getNeighborhood()
					+ ". Por favor ingrese a ChinuProp para responder a esta solicitud.");

			Transport.send(message);

		} catch (final MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
