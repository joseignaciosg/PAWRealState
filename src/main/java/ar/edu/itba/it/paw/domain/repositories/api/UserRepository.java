package ar.edu.itba.it.paw.domain.repositories.api;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.InvalidLoginException;

public interface UserRepository {
	public User getByNameAndPassword(final String username,
			final String password) throws InvalidLoginException;

	public User getByName(final String username);
}
