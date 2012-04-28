package ar.edu.itba.it.paw.test.daos.base;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;

public abstract class UserDaoTest extends DaoTest {

	private UserDao userDao;

	@Before
	public void initDB() {
		this.userDao = this.getDaoProvider().getUserDao();
		User usr = new User("Ben", "Stiller", "ben@gmail.com", "16748376",
				"BenSti", "B3nSt1");
		usr.setId(1);

		this.userDao.saveOrUpdate(usr);

		usr = new User("Truman", "Capote", "capote@gmail.com", "3728376",
				"TruCa", "TruC4");
		usr.setId(2);
		this.userDao.saveOrUpdate(usr);
	}

	@Test
	public void getAllTest() {
		Assert.assertTrue(this.userDao.getAll().size() == 2);
	}

	@Test
	public void getByIdTest() {

		User u = this.userDao.getById(1);
		Assert.assertNotNull(u);

		u = this.userDao.getById(1123123);

		Assert.assertNull(u);
	}

	@Test
	public void propertiesRelationShipTest() {
		this.userDao = this.getDaoProvider().getUserDao();

		final Property defProp = this.getHelper().defaultProperty();
		User defUser = this.getHelper().defaultUser();

		defUser = this.userDao.getById(defUser.getId());
		Assert.assertTrue(defUser.getProperties().size() == 1);
		Assert.assertTrue(defUser.getProperties().iterator().next()
				.equals(defProp));

	}

	@Test
	public void deleteTest() {
		final User u1 = this.userDao.getById(1);
		Assert.assertNotNull(u1);

		this.userDao.delete(u1);
		final User u2 = this.userDao.getById(1);
		Assert.assertNull(u2);

		this.userDao.saveOrUpdate(u1);
	}

	@Test
	public void saveOrUpdateTest() {
		final User u1 = new User("Doris", "Day", "dorisgay@gmail.com",
				"16728376", "DorisDa", "D0r1D4");
		u1.setId(3);
		Assert.assertTrue(this.userDao.saveOrUpdate(u1));
		Assert.assertFalse(this.userDao.saveOrUpdate(u1));

		u1.setMail("hola@hotmail.com");
		Assert.assertTrue(this.userDao.saveOrUpdate(u1));
		u1.setName("Dora");
		Assert.assertTrue(this.userDao.saveOrUpdate(u1));
		u1.setSurname("Dia");
		Assert.assertTrue(this.userDao.saveOrUpdate(u1));
		u1.setTelephone("637482634");
		Assert.assertTrue(this.userDao.saveOrUpdate(u1));
	}

}
