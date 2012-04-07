package ar.edu.itba.it.paw.model.services.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.FunctorException;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;

public class NotificationMimeMessageFactory implements Factory {

	private User user;
	private Property property;

	public NotificationMimeMessageFactory(final User user,
			final Property property) {
		this.user = user;
		this.property = property;
	}

	public Object create() {
		try {
			final Message message = new MimeMessage((Session) null);

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.user.getMail()));
			message.setSubject("Notificacion de ChinuProp para "
					+ this.user.getUsername());
			message.setText("Usted tiene una nueva solicitud de contacto para su propiedad en "
					+ this.property.getAddress()
					+ ", "
					+ this.property.getNeighborhood()
					+ ". Por favor ingrese a ChinuProp para responder a esta solicitud.");

			return message;
		} catch (final AddressException e) {
			e.printStackTrace();
		} catch (final MessagingException e) {
			e.printStackTrace();
		}
		throw new FunctorException("El email no se pudo enviar");
	}
}