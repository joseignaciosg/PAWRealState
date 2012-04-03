package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryPropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;

public class InMemoryPropertyDaoTest extends PropertyDaoTest {

	private PropertyDao dao;

	@Override
	public PropertyDao getDao() {
		if (this.dao == null) {
			this.dao = new InMemoryPropertyDao(new ArrayList<Property>());
		}

		return this.dao;
	}
}
