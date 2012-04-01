package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryUserDao;
import ar.edu.itba.it.paw.model.entities.User;

public class UserDaoTest {

	private InMemoryUserDao dao;

	@Before
	public void initDB() {
		final List<User> users = new ArrayList<User>();
		User usr = new User("Ben", "Stiller", "ben@gmail.com", "16748376",
				"BenSti", "B3nSt1");
		usr.setID(1);
		users.add(usr);
		usr = new User("Truman", "Capote", "capote@gmail.com", "3728376",
				"TruCa", "TruC4");
		usr.setID(2);
		users.add(usr);

		this.dao = new InMemoryUserDao(users);
	}

	@Test
	public void getByIdTest() {
		User u = this.dao.getById(1);
		Assert.assertNotNull(u);

		u = this.dao.getById(1123123);

		Assert.assertNull(u);
	}

	@Test
	public void deleteTest() {
		final User u1 = this.dao.getById(1);
		Assert.assertNotNull(u1);

		this.dao.delete(u1);
		final User u2 = this.dao.getById(1);
		Assert.assertNull(u2);

		this.dao.saveOrUpdate(u1);
	}

	@Test
	public void saveOrUpdateTest() {
		final User u1 = new User("Doris", "Day", "dorisgay@gmail.com",
				"16728376", "DorisDa", "D0r1D4");
		u1.setID(3);
		Assert.assertTrue(this.dao.saveOrUpdate(u1));
		Assert.assertFalse(this.dao.saveOrUpdate(u1));

		u1.setMail("hola@hotmail.com");
		Assert.assertTrue(this.dao.saveOrUpdate(u1));
		u1.setName("Dora");
		Assert.assertTrue(this.dao.saveOrUpdate(u1));
		u1.setSurname("Dia");
		Assert.assertTrue(this.dao.saveOrUpdate(u1));
		u1.setTelephone("637482634");
		Assert.assertTrue(this.dao.saveOrUpdate(u1));
	}

	@Test
	public void getAllTest() {
		Assert.assertTrue(this.dao.getAll().size() == 2);
	}

}
