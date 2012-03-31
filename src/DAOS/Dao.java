package DAOS;

import java.util.List;

import model.Entity;

public interface Dao<T extends Entity> {

	public List<T> getAll();

	public T getById(String id);

	public boolean delete(T obj);

	public boolean saveOrUpdate(T obj);

}
