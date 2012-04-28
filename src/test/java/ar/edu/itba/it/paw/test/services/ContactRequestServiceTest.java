package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.ContactRequestService;
import ar.edu.itba.it.paw.test.TransactionalTest;

public class ContactRequestServiceTest extends TransactionalTest {

	ContactRequestService contact;

	@Before
	public void initTest() {

		final DaoProvider provider = DaoProvider.getProviderForConnection(this
				.getConnectionProvider());

		final ContactRequestDao contactDao = provider.getContactRequestDao();
		final PropertyDao dao = provider.getPropertyDao();

		final Services service = new Services(true, true, true, true, false,
				true);

		final User user = new User(1, "cris", "", "", "", "", "");

		final Property prop9 = new Property(Integer.valueOf(1), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", user);

		final ContactRequest contact1 = new ContactRequest("Nicolas",
				"asd@asd.com", "44450322", "descrip1", prop9);

		final ContactRequest contact2 = new ContactRequest("Valles",
				"lalala@asd.com", "44420322", "descrip2", prop9);

		contactDao.saveOrUpdate(contact1);
		contactDao.saveOrUpdate(contact2);

		dao.saveOrUpdate(prop9);

		this.contact = new ContactRequestService(contactDao);

	}

	@Test
	public void saveContactRequestTest() {

		final List<String> errors = new ArrayList<String>();

		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop9 = new Property(Type.HOUSE, Operation.SELL,
				"Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", null);

		final Property prop10 = new Property(Type.HOUSE, Operation.SELL,
				"Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", null);

		Assert.assertEquals(true, this.contact.saveContactRequest("Pedro",
				"Perez", "nico@hotmail.com", "4442-3232", "desc1", prop10,
				errors));

		Assert.assertEquals(true, this.contact.saveContactRequest("nombre",
				"apellido", "nico@hotmail.com", "4442-3232", "desc1", prop10,
				errors));

		Assert.assertEquals(false, this.contact.saveContactRequest("Pepe",
				"Word", null, "4442-3232", "desc1", prop10, errors));
		errors.remove(0);

		Assert.assertEquals(false, this.contact.saveContactRequest("pedrito",
				"lopez", "nico@hotmail.com", null, "desc1", prop10, errors));
		errors.remove(0);

		Assert.assertEquals(true, this.contact.saveContactRequest("Pedro",
				"Perez", "nico@hotmail.com", "4442-3232", "desc2", prop9,
				errors));

	}

	@Test
	public void getContactRequestByIDTest() {

		final List<String> errors = new ArrayList<String>();

		final ContactRequest contact = this.contact.getContactRequestByID(1,
				errors);
		Assert.assertEquals("Nicolas", contact.getName());
		Assert.assertEquals("asd@asd.com", contact.getEmail());
		Assert.assertEquals("44450322", contact.getTelephone());
		Assert.assertEquals("descrip1", contact.getDescription());

	}
}
