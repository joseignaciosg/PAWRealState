package ar.edu.itba.it.paw.model.services;

import java.util.List;

import javax.mail.Message;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.utils.MessageSender;
import ar.edu.itba.it.paw.model.services.utils.NotificationMimeMessageFactory;
import ar.edu.itba.it.paw.model.services.utils.ServiceUtils;

public class EmailService {

	private MessageSender messageSender;

	public EmailService(final MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public boolean sendMail(final Message message, final List<String> errors) {
		ServiceUtils.validateNotNull(message,
				"El mensaje del email recibido es nulo", errors);

		if (errors.size() > 0) {
			return false;
		}

		return this.messageSender.send(message);
	}

	public boolean sendMail(final User user, final Property property,
			final List<String> errors) {

		final Message m = (Message) new NotificationMimeMessageFactory(user,
				property).create();

		return this.sendMail(m, errors);

		//
		//
		//
		// final String username = "chinusRealState@gmail.com";
		// final String password = "chino4ever";
		//
		// final Properties props = new Properties();
		// try {
		// FileInputStream inputStream = null;
		//
		// inputStream = new FileInputStream(
		// "src/main/java/ar/edu/itba/it/paw/services/emailservice.properties");
		// props.load(inputStream);
		// } catch (final FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		//
		// } catch (final IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// final Session session = Session.getInstance(props,
		// new javax.mail.Authenticator() {
		// @Override
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication(username, password);
		// }
		// });
		//
		// try {
		// final Message message = new MimeMessage(session);
		//
		// message.setFrom(new InternetAddress(username));
		// message.setRecipients(Message.RecipientType.TO,
		// InternetAddress.parse(user.getMail()));
		// message.setSubject("Notificacion de ChinuProp para "
		// + user.getUsername());
		// message.setText("Usted tiene una nueva solicitud de contacto para su propiedad en "
		// + property.getAddress()
		// + ", "
		// + property.getNeighborhood()
		// +
		// ". Por favor ingrese a ChinuProp para responder a esta solicitud.");
		//
		// Transport.send(message);
		//
		// } catch (final MessagingException e) {
		// throw new RuntimeException(e);
		// }
	}
}
