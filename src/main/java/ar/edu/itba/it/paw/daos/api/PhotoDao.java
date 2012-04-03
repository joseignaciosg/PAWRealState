package ar.edu.itba.it.paw.daos.api;

import java.util.List;

import ar.edu.itba.it.paw.model.entities.Photo;

public interface PhotoDao extends Dao<Photo> {

	public List<Integer> getByPropertyId(Integer id) throws Exception;
}
