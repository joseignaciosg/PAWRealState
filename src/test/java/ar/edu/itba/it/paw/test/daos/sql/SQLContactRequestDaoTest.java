package ar.edu.itba.it.paw.test.daos.sql;

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
import ar.edu.itba.it.paw.test.daos.base.DaoTest;

public class SQLContactRequestDaoTest extends DaoTest {
	private ContactRequestDao contactRequestDao;
	private PropertyDao propertyDao;

	private DaoProvider provider;

	@Override
	public DaoProvider getDaoProvider() {
		if (this.provider == null) {
			this.provider = DaoProvider.getProviderForConnection(this
					.getConnectionProvider());
		}

		return this.provider;
	}

	@Before
	public void initDB() {
		final Services ser1 = new Services(true, true, true, true, true, true);
		final Services ser2 = new Services(false, false, true, true, true,
				false);

		this.contactRequestDao = this.getDaoProvider().getContactRequestDao();
		this.propertyDao = this.getDaoProvider().getPropertyDao();

		final Property p1 = new Property(1, Type.HOUSE, Operation.RENT, "Soho",
				"Soho St. 120", 10000, 4, 1000, 2000, 15, ser1, "Really good",
				this.getHelper().defaultUser());
		final Property p2 = new Property(2, Type.APARTMENT, Operation.SELL,
				"Caballito", "Novia St.1900", 10000, 4, 1000, 2000, 15, ser2,
				"Really bad", this.getHelper().defaultUser());

		this.propertyDao.saveOrUpdate(p1);
		this.propertyDao.saveOrUpdate(p2);

		this.contactRequestDao.saveOrUpdate(new ContactRequest(1, "Spinoza",
				"spinoza@hotmail.com", "1267383", "Desc1", p1));
		this.contactRequestDao.saveOrUpdate(new ContactRequest(2, "Marx",
				"marx@hotmail.com", "1227383", "desc3", p2));

	}

	@Test
	public void getByIdTest() {
		ContactRequest c = this.contactRequestDao.getById(1);
		Assert.assertNotNull(c);

		c = this.contactRequestDao.getById(5);
		Assert.assertNull(c);
	}

	@Test
	public void deleteTest() {
		final ContactRequest c1 = this.contactRequestDao.getById(1);
		Assert.assertNotNull(c1);

		this.contactRequestDao.delete(c1);
		final ContactRequest c2 = this.contactRequestDao.getById(1);
		Assert.assertNull(c2);

		this.contactRequestDao.saveOrUpdate(c1);
	}

	@Test
	public void saveOrUpdateTest() {
		final Services ser1 = new Services(true, true, true, true, true, true);
		final Property prop = new Property(1, Type.HOUSE, Operation.RENT,
				"Soho", "Soho St. 120", 10000, 4, 1000, 2000, 15, ser1,
				"Really good", null);
		final ContactRequest c1 = new ContactRequest("Peron",
				"peron@hotmail.com", "3427383", "desc3", prop);
		Assert.assertTrue(this.contactRequestDao.saveOrUpdate(c1));
		Assert.assertFalse(this.contactRequestDao.saveOrUpdate(c1));

		c1.setEmail("hola@hotmail.com");
		Assert.assertTrue(this.contactRequestDao.saveOrUpdate(c1));
		c1.setName("Obama");
		Assert.assertTrue(this.contactRequestDao.saveOrUpdate(c1));
		c1.setTelephone("7777777");
		Assert.assertTrue(this.contactRequestDao.saveOrUpdate(c1));
	}

	@Test
	public void getAllTest() {
		Assert.assertTrue(this.contactRequestDao.getAll().size() == 2);
	}
}
