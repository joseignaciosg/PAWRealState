package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.exceptions.*;
import ar.edu.itba.it.paw.domain.repositories.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

@Repository
public class HibernateUserRepository extends AbstractHibernateRepository
		implements UserRepository {

	public HibernateUserRepository() {
		super(null);
	}

	@Autowired
	public HibernateUserRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("rawtypes")
	public User getByNameAndPassword(final String username,
			final String password) throws InvalidLoginException {

		final List found = this.find(
				"from User u where u.username like ? and u.password like ?",
				username, password);
		if (found.size() == 0) {
			throw new InvalidLoginException();
		}

		return (User) found.get(0);
	}

	@SuppressWarnings("rawtypes")
	public User getByName(final String username) {
		final List found = this.find("from User u where u.username like ?",
				username);
		if (found.isEmpty()) {
			return null;
		}
		return (User) found.get(0);
	}

	public List<RealStateAgency> getAllAgencies() {
		return this.find("from RealStateAgency");
	}
}
