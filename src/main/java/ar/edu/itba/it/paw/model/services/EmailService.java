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
	}
}
