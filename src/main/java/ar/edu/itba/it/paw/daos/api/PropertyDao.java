package ar.edu.itba.it.paw.daos.api;

import java.util.List;

import ar.edu.itba.it.paw.model.entities.Property;

public interface PropertyDao extends Dao<Property> {

	public List<Property> getAll();

}
