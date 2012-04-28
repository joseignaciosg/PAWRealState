package ar.edu.itba.it.paw.daos.api;

import ar.edu.itba.it.paw.model.entities.Entity;

public interface Dao<T extends Entity> {

	public T getById(Integer id);

	public boolean delete(T obj);

	public boolean saveOrUpdate(T obj);

}
