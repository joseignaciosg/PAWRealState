package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryPropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;

public class PropertyDaoTest {

	InMemoryPropertyDao data;

	@Before
	public void prepareData() {

		final List<Property> propertyList = new ArrayList<Property>();
		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip1");

		final Property prop2 = new Property(Integer.valueOf(2), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(500), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2");

		final Property prop3 = new Property(Integer.valueOf(3), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		propertyList.add(prop1);
		propertyList.add(prop2);
		propertyList.add(prop3);

		this.data = new InMemoryPropertyDao(propertyList);

	}

	@Test
	public void getByIdTest() {
		Assert.assertNull(this.data.getById(4));
		Assert.assertNotNull(this.data.getById(1));
	}

	@Test
	public void deleteTest() {

		final Services service = new Services(true, true, true, true, false,
				true);
		final Property propTrue = new Property(Integer.valueOf(3),
				Type.APARTMENT, Operation.SELL, "Palermo", "Alem 110",
				Integer.valueOf(500), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip3");

		final Property propFalse = new Property(Integer.valueOf(5), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		Assert.assertEquals(true, this.data.delete(propTrue));
		Assert.assertEquals(false, this.data.delete(propFalse));

	}

	@Test
	public void saveOrUpdateTest() {
		final Property propTrue = this.data.getById(1);
		propTrue.setAge(10);
		final Property propFalse = this.data.getById(2);
		Assert.assertEquals(true, this.data.saveOrUpdate(propTrue));
		Assert.assertEquals(false, this.data.saveOrUpdate(propFalse));
		Assert.assertEquals(false, this.data.saveOrUpdate(propTrue));

	}

	@Test
	public void getAllTest() {
		Assert.assertEquals(3, this.data.getAll().size());
	}
}
