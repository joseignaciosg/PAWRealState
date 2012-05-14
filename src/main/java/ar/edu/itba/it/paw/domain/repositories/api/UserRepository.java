package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.List;

import ar.edu.itba.it.paw.domain.entities.RealStateAgency;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.InvalidLoginException;

public interface UserRepository {
	public User getByNameAndPassword(final String username,
			final String password) throws InvalidLoginException;

	public List<RealStateAgency> getAllAgencies();
}
