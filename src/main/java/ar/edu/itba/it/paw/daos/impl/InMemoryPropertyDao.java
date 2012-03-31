package ar.edu.itba.it.paw.daos.impl;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.db.ConnectionManager;
import ar.edu.itba.it.paw.model.entities.Property;

public class InMemoryPropertyDao implements PropertyDao {

	public InMemoryPropertyDao(final ConnectionManager manager) {
	}

	public Property getById(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(final Property obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveOrUpdate(final Property obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Property> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
