package ar.edu.itba.it.paw.test.daos.base;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;

public abstract class PropertyDaoTest extends DaoTest {

	private PropertyDao propertyDao;

	private void preparedao() {

		this.propertyDao = this.getDaoProvider().getPropertyDao();

		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip1", this.getHelper()
						.defaultUser());

		final Property prop2 = new Property(Integer.valueOf(2), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(500), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2",
				this.getHelper().defaultUser());

		final Property prop3 = new Property(Integer.valueOf(3), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", this.getHelper()
						.defaultUser());

		this.propertyDao.saveOrUpdate(prop1);
		this.propertyDao.saveOrUpdate(prop2);
		this.propertyDao.saveOrUpdate(prop3);

	}

	public void prepareFilterDao() {

		this.propertyDao = this.getDaoProvider().getPropertyDao();

		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1", this.getHelper().defaultUser());

		final Property prop2 = new Property(Integer.valueOf(2), Type.HOUSE,
				Operation.RENT, "BarrioNorte", "Junca 460",
				Integer.valueOf(501), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2",
				this.getHelper().defaultUser());

		final Property prop3 = new Property(Integer.valueOf(3), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", this.getHelper()
						.defaultUser());

		final Property prop4 = new Property(Integer.valueOf(4), Type.APARTMENT,
				Operation.RENT, "Caballito", "Taring 660",
				Integer.valueOf(5020), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(2000),
				Integer.valueOf(5), service, "Descrip1", this.getHelper()
						.defaultUser());

		final Property prop5 = new Property(Integer.valueOf(5), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(5005), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip2", this.getHelper().defaultUser());

		final Property prop6 = new Property(Integer.valueOf(6), Type.HOUSE,
				Operation.RENT, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", this.getHelper()
						.defaultUser());

		final Property prop7 = new Property(Integer.valueOf(7), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(50040), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1", this.getHelper().defaultUser());

		final Property prop8 = new Property(Integer.valueOf(8), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(5002), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip2", this.getHelper().defaultUser());

		final Property prop9 = new Property(Integer.valueOf(9), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", this.getHelper()
						.defaultUser());

		this.propertyDao.saveOrUpdate(prop1);
		this.propertyDao.saveOrUpdate(prop2);
		this.propertyDao.saveOrUpdate(prop3);
		this.propertyDao.saveOrUpdate(prop4);
		this.propertyDao.saveOrUpdate(prop5);
		this.propertyDao.saveOrUpdate(prop6);
		this.propertyDao.saveOrUpdate(prop7);
		this.propertyDao.saveOrUpdate(prop8);
		this.propertyDao.saveOrUpdate(prop9);
	}

	@Test
	public void getByUserIdTest() {
		this.propertyDao = this.getDaoProvider().getPropertyDao();

		final User defaultUser = this.getHelper().defaultUser();

		Assert.assertEquals(0, this.propertyDao
				.getByUserId(defaultUser.getId()).size());

		this.getHelper().defaultProperty();

		Assert.assertEquals(1, this.propertyDao
				.getByUserId(defaultUser.getId()).size());

	}

	@Test
	public void getByIdTest() {
		this.preparedao();
		Assert.assertNull(this.propertyDao.getById(4));
		Assert.assertNotNull(this.propertyDao.getById(1));
	}

	@Test
	public void deleteTest() {
		this.preparedao();
		final Services service = new Services(true, true, true, true, false,
				true);
		final Property propTrue = new Property(Integer.valueOf(3),
				Type.APARTMENT, Operation.SELL, "Palermo", "Alem 110",
				Integer.valueOf(500), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip3",
				this.getHelper().defaultUser());

		final Property propFalse = new Property(Integer.valueOf(5), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3", this.getHelper()
						.defaultUser());

		Assert.assertEquals(true, this.propertyDao.delete(propTrue));
		Assert.assertEquals(false, this.propertyDao.delete(propFalse));

	}

	/**
	 * Tests that once you get a Property instance back the owner is the same.
	 */
	@Test
	public void ownershipRelationTest() {
		/**
		 * Once we have a property setup
		 */
		this.propertyDao = this.getDaoProvider().getPropertyDao();

		final Property defaultProp = this.propertyDao.getById(this.getHelper()
				.defaultProperty().getId());

		/**
		 * The dao should returnt he reference to the owner, which can or not be
		 * lazy.
		 */
		Assert.assertEquals(this.getHelper().defaultUser(),
				defaultProp.getOwner());
	}

	@Test
	public void photosRelationTest() {

		/**
		 * First we load the photo and property.
		 */

		this.propertyDao = this.getDaoProvider().getPropertyDao();

		this.getHelper().defaultPhoto();

		Property defaultProp = this.propertyDao.getById(this.getHelper()
				.defaultProperty().getId());

		/**
		 * It should have only one photo loaded
		 */
		Assert.assertTrue(defaultProp.getPhotos().size() == 1);

		/**
		 * Once we add a new photo, it must be resolver correctly by the dao
		 */
		final Photo photo2 = new Photo(2, new byte[] {}, "jpg", 1);

		defaultProp.getPhotos().add(photo2);

		this.propertyDao.saveOrUpdate(defaultProp);

		defaultProp = this.propertyDao.getById(this.getHelper()
				.defaultProperty().getId());

		/**
		 * Like this.
		 */
		Assert.assertTrue(defaultProp.getPhotos().size() == 2);

		/**
		 * Same happens with removal
		 */
		defaultProp.getPhotos().remove(photo2);

		this.propertyDao.saveOrUpdate(defaultProp);

		defaultProp = this.propertyDao.getById(this.getHelper()
				.defaultProperty().getId());

		/**
		 * Like this.
		 */
		Assert.assertTrue(defaultProp.getPhotos().size() == 1);
	}

	@Test
	public void saveOrUpdateTest() {
		this.preparedao();
		final Property propTrue = this.propertyDao.getById(1);
		propTrue.setAge(10);
		final Property propFalse = this.propertyDao.getById(2);
		Assert.assertEquals(true, this.propertyDao.saveOrUpdate(propTrue));
		Assert.assertEquals(false, this.propertyDao.saveOrUpdate(propFalse));
		Assert.assertEquals(false, this.propertyDao.saveOrUpdate(propTrue));
	}

	@Test
	public void getAllTest() {
		this.preparedao();
		Assert.assertEquals(3, this.propertyDao.getAll().size());
	}

	@Test
	public void getAllFilteredTest() {
		this.prepareFilterDao();
		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1", null);

		// I want the houses which cost is 1000 - only one
		List<Property> props = this.propertyDao.getAll(null, null, 1000, 1000,
				0, 3, Order.ASC, true);
		Assert.assertTrue(props.size() == 1);
		Assert.assertEquals(props.get(0), prop1);

		props = this.propertyDao.getAll(null, Type.HOUSE, -1, -1, 0, 3,
				Order.ASC, true);
		Assert.assertTrue(props.size() == 3);

		List<Property> props2 = this.propertyDao.getAll(null, Type.HOUSE, -1,
				-1, 0, 3, Order.ASC, true);
		Assert.assertEquals(props, props2);

		props = this.propertyDao.getAll(null, Type.HOUSE, -1, -1, 1, 3,
				Order.ASC, true);
		props2 = this.propertyDao.getAll(null, Type.HOUSE, -1, -1, 1, 3,
				Order.ASC, true);
		Assert.assertEquals(props, props2);

		props = this.propertyDao.getAll(null, Type.APARTMENT, -1, -1, 0, 10,
				Order.ASC, true);
		Assert.assertTrue(props.size() == 3);

		props = this.propertyDao.getAll(Operation.RENT, null, -1, -1, 0, 10,
				Order.ASC, true);
		Assert.assertTrue(props.size() == 5);

		props = this.propertyDao.getAll(Operation.RENT, null, -1, -1, 1, 10,
				Order.ASC, true);
		Assert.assertTrue(props.size() == 0);

		props = this.propertyDao.getAll(Operation.RENT, Type.APARTMENT, -1, -1,
				0, 10, Order.ASC, true);
		Assert.assertTrue(props.size() == 3);

		props = this.propertyDao.getAll(null, null, 0, 100000, 0, 10,
				Order.ASC, true);
		Assert.assertTrue(props.size() == 9);

		props = this.propertyDao.getAll(null, null, 0, 100000, 0, 10,
				Order.DESC, true);
		Assert.assertTrue(props.size() == 9);

		props = this.propertyDao.getAll(Operation.SELL, null, 0, 100000, 0, 10,
				Order.ASC, true);

		Assert.assertTrue(props.size() == 4);
	}
}
