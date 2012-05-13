package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;

@Repository
public class HibernateAgencyRepository extends AbstractHibernateRepository {

	@Autowired
	public HibernateAgencyRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("rawtypes")
	public List getAllWithProp() {
		final List found = this
				.find("from RealStateAgency u where exists elements(u.properties)");
		if (found.isEmpty()) {
			return null;
		}
		return found;
	}
}
