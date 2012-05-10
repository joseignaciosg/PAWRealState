package ar.edu.itba.it.paw.domain.repositories.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.UserRepository;

@Repository
public class HibernateUserRepository extends AbstractHibernateRepository
		implements UserRepository {

	@Autowired
	public HibernateUserRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
