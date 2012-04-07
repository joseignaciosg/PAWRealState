package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.EmailService;
import ar.edu.itba.it.paw.model.services.utils.MessageSender;
import ar.edu.itba.it.paw.model.services.utils.NotificationMimeMessageFactory;

public class EmailServiceTest {

	private MessageSender messageSender;
	private User owner;
	private EmailService service;
	private Property property;

	public EmailServiceTest() {
		this.messageSender = new MessageSender() {
			public boolean send(final Message m) {
				return m == null;
			}
		};
	}

	@Before
	public void initService() {
		final Services service = new Services(true, true, true, true, false,
				true);

		this.owner = new User("cris", "apellido", "Email", "telefono", "cris",
				"asd");

		this.property = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1", this.owner);
		this.service = new EmailService(this.messageSender);
	}

	@Test
	public void sendMailTest() {

		final List<String> errors = new ArrayList<String>();

		final Message mess = (Message) new NotificationMimeMessageFactory(
				this.owner, this.property).create();

		this.service.sendMail(mess, errors);

		Assert.assertTrue(errors.size() == 0);

		this.service.sendMail(null, errors);

		Assert.assertTrue(errors.size() == 1);

	}

}
