package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.entities.RealStateAgency;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.InvalidLoginException;
import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.UserRepository;

@Repository
public class HibernateUserRepository extends AbstractHibernateRepository
		implements UserRepository {

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
