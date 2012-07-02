package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.*;

import org.hibernate.*;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

@Component
public class HibernateAdRepository extends AbstractHibernateRepository
		implements AdRepository {

	public HibernateAdRepository() {
		super(null);
	}

	@Autowired
	public HibernateAdRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Ad> getRandomAds(final int maxResults) {
		final Session session = this.getSession();

		final Query query = session.createQuery(
				"from Ad order by weight * random()").setMaxResults(maxResults);

		return query.list();
	}

}
