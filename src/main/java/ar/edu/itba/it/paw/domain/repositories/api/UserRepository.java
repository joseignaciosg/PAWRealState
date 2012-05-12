package ar.edu.itba.it.paw.domain.repositories.api;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.User;

public interface UserRepository {
	public User getByNameAndPassword(final String username,
			final String password);

	public boolean sendContactRequest(ContactRequest request, User user);
}
