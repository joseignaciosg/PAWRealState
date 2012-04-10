package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.impl.inmem.InMemoryContactRequestDao;
import ar.edu.itba.it.paw.daos.impl.inmem.InMemoryPropertyDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.services.ContactRequestService;

public class ContactRequestServiceTest {

	ContactRequestService contact;

	@Before
	public void initTest() {

		final List<ContactRequest> data = new ArrayList<ContactRequest>();
		final List<Property> propertyList = new ArrayList<Property>();

		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop9 = new Property(Integer.valueOf(1), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", null);

		final ContactRequest contact1 = new ContactRequest(10, "Nicolas",
				"asd@asd.com", "44450322", "descrip1", prop9);

		final ContactRequest contact2 = new ContactRequest(2, "Valles",
				"lalala@asd.com", "44420322", "descrip2", prop9);

		data.add(contact1);
		data.add(contact2);
		propertyList.add(prop9);

		final ContactRequestDao contactDao = new InMemoryContactRequestDao(data);
		final PropertyDao dao = new InMemoryPropertyDao(propertyList);

		this.contact = new ContactRequestService(contactDao);

	}

	@Test
	public void saveContactRequestTest() {

		final List<String> errors = new ArrayList<String>();

		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop9 = new Property(Integer.valueOf(1), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", null);

		final Property prop10 = new Property(Integer.valueOf(10), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", null);

		Assert.assertEquals(true, this.contact.saveContactRequest(
				"Pedro Perez", "nico@hotmail.com", "4442-3232", "desc1",
				prop10, errors));

		Assert.assertEquals(true, this.contact.saveContactRequest("nombre",
				"nico@hotmail.com", "4442-3232", "desc1", prop10, errors));

		Assert.assertEquals(false, this.contact.saveContactRequest("Pepe",
				null, "4442-3232", "desc1", prop10, errors));
		errors.remove(0);

		Assert.assertEquals(false, this.contact.saveContactRequest("pedrito",
				"nico@hotmail.com", null, "desc1", prop10, errors));
		errors.remove(0);

		Assert.assertEquals(true, this.contact.saveContactRequest(
				"Pedro Perez", "nico@hotmail.com", "4442-3232", "desc2", prop9,
				errors));

	}

	@Test
	public void getContactRequestByIDTest() {

		final List<String> errors = new ArrayList<String>();

		final ContactRequest contact = this.contact.getContactRequestByID(10,
				errors);
		Assert.assertEquals("Nicolas", contact.getName());
		Assert.assertEquals("asd@asd.com", contact.getEmail());
		Assert.assertEquals("44450322", contact.getTelephone());
		Assert.assertEquals("descrip1", contact.getDescription());

	}
}
