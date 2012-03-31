package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryContactRequestDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.OPERATION;
import ar.edu.itba.it.paw.model.entities.Property.TYPE;
import ar.edu.itba.it.paw.model.entities.Services;

public class ContactRequestTest {

	private InMemoryContactRequestDao dao;

	@Before
	public void initDB() {
		final List<Property> properties = new ArrayList<Property>();
		final Services ser1 = new Services(true, true, true, true, true, true);
		final Services ser2 = new Services(false, false, true, true, true,
				false);
		properties.add(new Property(1, TYPE.HOUSE, OPERATION.RENT, "Soho",
				"Soho St. 120", 10000, 4, 1000, 2000, 15, ser1, "Really good"));
		properties.add(new Property(2, TYPE.APARTMENT, OPERATION.SELL,
				"Caballito", "Novia St.1900", 10000, 4, 1000, 2000, 15, ser2,
				"Really bad"));

		final List<ContactRequest> contacts = new ArrayList<ContactRequest>();
		contacts.add(new ContactRequest(1, "Spinoza", "spinoza@hotmail.com",
				"1267383", properties.get(0)));
		contacts.add(new ContactRequest(2, "Marx", "marx@hotmail.com",
				"1227383", properties.get(1)));

		this.dao = new InMemoryContactRequestDao(contacts);
	}

	@Test
	public void getByIdTest() {
		ContactRequest c = this.dao.getById(1);
		Assert.assertNotNull(c);

		c = this.dao.getById(5);
		Assert.assertNull(c);
	}

	@Test
	public void deleteTest() {
		final ContactRequest c1 = this.dao.getById(1);
		Assert.assertNotNull(c1);

		this.dao.delete(c1);
		final ContactRequest c2 = this.dao.getById(1);
		Assert.assertNull(c2);

		this.dao.saveOrUpdate(c1);
	}

	@Test
	public void saveOrUpdateTest() {
		final Services ser1 = new Services(true, true, true, true, true, true);
		final Property prop = new Property(1, TYPE.HOUSE, OPERATION.RENT,
				"Soho", "Soho St. 120", 10000, 4, 1000, 2000, 15, ser1,
				"Really good");
		final ContactRequest c1 = new ContactRequest(3, "Peron",
				"peron@hotmail.com", "3427383", prop);
		Assert.assertTrue(this.dao.saveOrUpdate(c1));
		Assert.assertFalse(this.dao.saveOrUpdate(c1));

		c1.setEmail("hola@hotmail.com");
		Assert.assertTrue(this.dao.saveOrUpdate(c1));
		c1.setName("Obama");
		Assert.assertTrue(this.dao.saveOrUpdate(c1));
		c1.setTelephone("7777777");
		Assert.assertTrue(this.dao.saveOrUpdate(c1));
	}

	@Test
	public void getAllTest() {
		Assert.assertTrue(this.dao.getAll().size() == 2);
	}

}
