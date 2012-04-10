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
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;

	public NotificationMimeMessageFactory(final User user,
			final Property property, final String firstName,
			final String lastName, final String email, final String phone,
			final String description) {
		this.user = user;
		this.property = property;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.description = description;
	}

	public Object create() {
		try {
			final Message message = new MimeMessage((Session) null);

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.user.getMail()));
			message.setSubject("Notificacion de ChinuProp para "
					+ this.user.getUsername());

			final StringBuilder sb = new StringBuilder();

			sb.append(
					"Usted tiene una nueva solicitud de contacto para su propiedad en "
							+ this.property.getAddress() + ", "
							+ this.property.getNeighborhood() + ".").append(
					"\n");

			sb.append("Datos del contacto:").append("\n");

			sb.append("Nombre:")
					.append(this.firstName + ", " + this.lastName).append("\n");

			if (this.email != null) {
				sb.append("Email:").append(this.email).append("\n");
			}

			if (this.phone != null) {
				sb.append("Teléfono:").append(this.phone).append("\n");
			}

			if (this.description != null) {
				sb.append("Descripción:").append(this.description)
						.append("\n");
			}

			message.setText(sb.toString());

			return message;
		} catch (final AddressException e) {
			e.printStackTrace();
		} catch (final MessagingException e) {
			e.printStackTrace();
		}
		throw new FunctorException("El email no se pudo enviar");
	}
}