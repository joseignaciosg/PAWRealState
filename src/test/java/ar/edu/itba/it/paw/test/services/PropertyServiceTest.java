package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.test.TransactionalTest;
import ar.edu.itba.it.paw.test.daos.helper.DaoTestHelper;

/*
 * Todos los usuarios, incluyendo usuarios anónimos, deben poder consultar el listado
 * de avisos en venta/alquiler a través de una búsqueda avanzada por: tipo de operación, 
 * tipo de inmueble y rango de precio. Todos estos campos son opcionales, incluyendo los
 * extremos del rango de precios (por ejemplo, se puede ingresar un límite inferior pero 
 * dejar en blanco el superior). Para cada propiedad se debe mostrar el tipo de operación, 
 * el tipo de inmueble, la dirección, el barrio, el precio y un hipervínculo que le permita
 * al usuario llegar a otra página que muestre toda la información de la propiedad.
 * El listado debe poder ordenarse ascendente o descendentemente por precio.
 * 
 * */

public class PropertyServiceTest extends TransactionalTest {

	private User owner;
	private PropertyService service;

	private DaoTestHelper helper;

	@Before
	public void initService() {

		final DaoProvider provider = DaoProvider.getProviderForConnection(this
				.getConnectionProvider());

		final Services service = new Services(true, true, true, true, false,
				true);

		this.helper = new DaoTestHelper(provider);

		final PropertyDao dao = provider.getPropertyDao();
		final UserDao userDao = provider.getUserDao();

		this.owner = new User("cris", "apellido", "Email", "telefono", "cris",
				"asd");

		userDao.saveOrUpdate(this.owner);

		final Property prop1 = new Property(Type.APARTMENT, Operation.RENT,
				"Palermo", "Lavalle 660", Integer.valueOf(1000),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip1",
				this.helper.defaultUser());

		final Property prop2 = new Property(Type.HOUSE, Operation.RENT,
				"BarrioNorte", "Junca 460", Integer.valueOf(501),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip2",
				this.helper.defaultUser());

		final Property prop3 = new Property(Type.HOUSE, Operation.SELL,
				"Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3",
				this.helper.defaultUser());

		final Property prop4 = new Property(Type.APARTMENT, Operation.RENT,
				"Caballito", "Taring 660", Integer.valueOf(5020),
				Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(2000), Integer.valueOf(5), service, "Descrip1",
				this.helper.defaultUser());

		final Property prop5 = new Property(Type.HOUSE, Operation.SELL,
				"BarrioNorte", "Junca 460", Integer.valueOf(5005),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip2",
				this.helper.defaultUser());

		final Property prop6 = new Property(Type.HOUSE, Operation.RENT,
				"Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3",
				this.helper.defaultUser());

		final Property prop7 = new Property(Type.APARTMENT, Operation.RENT,
				"Palermo", "Lavalle 660", Integer.valueOf(50040),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip1",
				this.helper.defaultUser());

		final Property prop8 = new Property(Type.HOUSE, Operation.SELL,
				"BarrioNorte", "Junca 460", Integer.valueOf(5002),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip2",
				this.helper.defaultUser());

		final Property prop9 = new Property(Type.HOUSE, Operation.SELL,
				"Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3",
				this.helper.defaultUser());

		dao.saveOrUpdate(prop1);
		dao.saveOrUpdate(prop2);
		dao.saveOrUpdate(prop3);
		dao.saveOrUpdate(prop4);
		dao.saveOrUpdate(prop5);
		dao.saveOrUpdate(prop6);
		dao.saveOrUpdate(prop7);
		dao.saveOrUpdate(prop8);
		dao.saveOrUpdate(prop9);

		this.service = new PropertyService(dao, userDao);
	}

	@Test
	public void advancedSearchTest() {
		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1", null);

		// I want the houses which cost is 1000 - only one
		List<Property> props = this.service.advancedSearch(null, null, 1000,
				1000, 0, 3, Order.ASC);
		Assert.assertTrue(props.size() == 1);
		Assert.assertEquals(props.get(0), prop1);

		props = this.service.advancedSearch(null, Type.HOUSE, -1, -1, 0, 3,
				Order.ASC);
		Assert.assertTrue(props.size() == 3);

		List<Property> props2 = this.service.advancedSearch(null, Type.HOUSE,
				-1, -1, 0, 3, Order.ASC);
		Assert.assertEquals(props, props2);

		props = this.service.advancedSearch(null, Type.HOUSE, -1, -1, 1, 3,
				Order.ASC);
		props2 = this.service.advancedSearch(null, Type.HOUSE, -1, -1, 1, 3,
				Order.ASC);
		Assert.assertEquals(props, props2);

		props = this.service.advancedSearch(null, Type.APARTMENT, -1, -1, 0,
				10, Order.ASC);
		Assert.assertTrue(props.size() == 3);

		props = this.service.advancedSearch(Operation.RENT, null, -1, -1, 0,
				10, Order.ASC);
		Assert.assertTrue(props.size() == 5);

		props = this.service.advancedSearch(Operation.RENT, null, -1, -1, 1,
				10, Order.ASC);
		Assert.assertTrue(props.size() == 0);

		props = this.service.advancedSearch(Operation.RENT, Type.APARTMENT, -1,
				-1, 0, 10, Order.ASC);
		Assert.assertTrue(props.size() == 3);

		// asc
		props = this.service.advancedSearch(null, null, 0, 100000, 0, 10,
				Order.ASC);
		Assert.assertTrue(props.size() == 9);

		// desc
		props = this.service.advancedSearch(null, null, 0, 100000, 0, 10,
				Order.DESC);
		Assert.assertTrue(props.size() == 9);

		props = this.service.advancedSearch(Operation.SELL, null, 0, 100000, 0,
				10, Order.ASC);

		Assert.assertTrue(props.size() == 4);

	}

	@Test
	public void getPropertyByIDTest() {

		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1", this.helper.defaultUser());

		final List<String> errors = new ArrayList<String>();

		// Property that doesn't exist.
		Assert.assertNull(this.service.getPropertyByID(10, errors));

		// errors has to be with one error message
		Assert.assertEquals(1, errors.size());

		// remove the element from the list
		errors.remove(0);

		// Property that exist
		final Property newProp = this.service.getPropertyByID(1, errors);
		Assert.assertNotNull(newProp);

		// newProp equals prop1 has to be true
		Assert.assertEquals(true, newProp.equals(prop1));

		// newProp2 has to be not null
		final Property newProp2 = this.service.getPropertyByID(2, errors);
		Assert.assertNotNull(newProp2);

		// newProp2 equals prop1 has to be false
		Assert.assertEquals(false, newProp2.equals(prop1));

		// The errors list should be empty
		Assert.assertEquals(0, errors.size());
	}

	public void savePropertyTest() {
		final List<String> errors = new ArrayList<String>();
		Assert.assertTrue(this.service.saveProperty("SELL", "APARTMENT",
				"Flores", "La casa del chino 123", 123400, 5, 40, 3, 30,
				new Services(), "", errors, this.owner, null));
		Assert.assertTrue(this.owner.getProperties().size() == 1);
		Assert.assertTrue(errors.size() == 0);
	}

}
