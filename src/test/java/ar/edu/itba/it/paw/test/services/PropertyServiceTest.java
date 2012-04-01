package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryPropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.OPERATION;
import ar.edu.itba.it.paw.model.entities.Property.TYPE;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.services.PropertyService;
import ar.edu.itba.it.paw.model.services.PropertyService.ORDER;

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

public class PropertyServiceTest {

	private PropertyService service;

	@Before
	public void initService() {
		final List<Property> propertyList = new ArrayList<Property>();
		final Services service = new Services(true, true, true, true, false,
				true);

		// new Property(iD, type, operation, neighborhood, address, price,
		// spaces, coveredArea, freeArea, age, service, description)

		final Property prop1 = new Property(Integer.valueOf(1), TYPE.APARTMENT,
				OPERATION.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1");

		final Property prop2 = new Property(Integer.valueOf(2), TYPE.HOUSE,
				OPERATION.RENT, "BarrioNorte", "Junca 460",
				Integer.valueOf(501), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2");

		final Property prop3 = new Property(Integer.valueOf(3), TYPE.HOUSE,
				OPERATION.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		final Property prop4 = new Property(Integer.valueOf(4), TYPE.APARTMENT,
				OPERATION.RENT, "Caballito", "Taring 660",
				Integer.valueOf(5020), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(2000),
				Integer.valueOf(5), service, "Descrip1");

		final Property prop5 = new Property(Integer.valueOf(5), TYPE.HOUSE,
				OPERATION.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(5005), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip2");

		final Property prop6 = new Property(Integer.valueOf(6), TYPE.HOUSE,
				OPERATION.RENT, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		final Property prop7 = new Property(Integer.valueOf(7), TYPE.APARTMENT,
				OPERATION.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(50040), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1");

		final Property prop8 = new Property(Integer.valueOf(8), TYPE.HOUSE,
				OPERATION.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(5002), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip2");

		final Property prop9 = new Property(Integer.valueOf(9), TYPE.HOUSE,
				OPERATION.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		propertyList.add(prop1);
		propertyList.add(prop2);
		propertyList.add(prop3);
		propertyList.add(prop4);
		propertyList.add(prop5);
		propertyList.add(prop6);
		propertyList.add(prop7);
		propertyList.add(prop8);
		propertyList.add(prop9);

		final PropertyDao dao = new InMemoryPropertyDao(propertyList);
		this.service = new PropertyService(dao);

	}

	@Test
	public void advancedSearchTest() {
		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), TYPE.APARTMENT,
				OPERATION.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1");

		// I want the houses which cost is 1000 - only one
		List<Property> props = this.service.advacedSearch(null, null, 1000,
				1000, 0, 3, ORDER.ASC);
		Assert.assertTrue(props.size() == 1);
		Assert.assertEquals(props.get(0), prop1);

		props = this.service.advacedSearch(null, TYPE.HOUSE, -1, -1, 0, 3,
				ORDER.ASC);
		Assert.assertTrue(props.size() == 3);

		List<Property> props2 = this.service.advacedSearch(null, TYPE.HOUSE,
				-1, -1, 0, 3, ORDER.ASC);
		Assert.assertEquals(props, props2);

		props = this.service.advacedSearch(null, TYPE.HOUSE, -1, -1, 1, 3,
				ORDER.ASC);
		props2 = this.service.advacedSearch(null, TYPE.HOUSE, -1, -1, 1, 3,
				ORDER.ASC);
		Assert.assertEquals(props, props2);

		props = this.service.advacedSearch(null, TYPE.APARTMENT, -1, -1, 0, 10,
				ORDER.ASC);
		Assert.assertTrue(props.size() == 3);
		System.out.println(props);

		props = this.service.advacedSearch(OPERATION.RENT, null, -1, -1, 0, 10,
				ORDER.ASC);
		Assert.assertTrue(props.size() == 5);
		System.out.println(props);

		props = this.service.advacedSearch(OPERATION.RENT, null, -1, -1, 1, 10,
				ORDER.ASC);
		Assert.assertTrue(props.size() == 0);

		props = this.service.advacedSearch(OPERATION.RENT, TYPE.APARTMENT, -1,
				-1, 0, 10, ORDER.ASC);
		Assert.assertTrue(props.size() == 3);

		// asc
		props = this.service.advacedSearch(null, null, 0, 100000, 0, 10,
				ORDER.ASC);
		Assert.assertTrue(props.size() == 9);
		System.out.println(props);

		// desc
		props = this.service.advacedSearch(null, null, 0, 100000, 0, 10,
				ORDER.DESC);
		Assert.assertTrue(props.size() == 9);
		System.out.println(props);

	}

	@Test
	public void getPropertyByIDTest() {

		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), TYPE.APARTMENT,
				OPERATION.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1");

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
}
