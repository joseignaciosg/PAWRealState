package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryContactRequestDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;

public class ContactRequestTest {

	private InMemoryContactRequestDao dao;

	@Before
	public void initDB() {
		final List<ContactRequest> contacts = new ArrayList<ContactRequest>();
		contacts.add(new ContactRequest(1, "Spinoza", "spinoza@hotmail.com",
				"1267383"));
		contacts.add(new ContactRequest(2, "Marx", "marx@hotmail.com",
				"1227383"));

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
		final ContactRequest c1 = new ContactRequest(3, "Peron",
				"peron@hotmail.com", "3427383");
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
