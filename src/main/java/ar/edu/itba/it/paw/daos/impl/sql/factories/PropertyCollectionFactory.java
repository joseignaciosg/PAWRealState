package ar.edu.itba.it.paw.daos.impl.sql.factories;

import java.util.Collection;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.utils.Factory;

public class PropertyCollectionFactory implements Factory<Collection<Property>> {

	private PropertyDao propertyDao;
	private User owner;

	public PropertyCollectionFactory(final PropertyDao dao, final User owner) {
		this.propertyDao = dao;
		this.owner = owner;
	}

	public Collection<Property> create() {
		return this.propertyDao.getByUserId(this.owner.getId());
	}

}
