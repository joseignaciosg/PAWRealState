package ar.edu.itba.it.paw.daos.api;

import java.util.List;

import ar.edu.itba.it.paw.model.entities.ContactRequest;

public interface ContactRequestDao extends Dao<ContactRequest> {

	public List<ContactRequest> getAll();

}
