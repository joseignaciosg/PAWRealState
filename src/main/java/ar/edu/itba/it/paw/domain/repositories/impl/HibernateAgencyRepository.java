package ar.edu.itba.it.paw.domain.repositories.impl;

import java.io.*;
import java.util.*;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.*;

@Repository
public class HibernateAgencyRepository extends AbstractHibernateRepository
		implements Serializable {

	public HibernateAgencyRepository() {
		super(null);
	}

	@Autowired
	public HibernateAgencyRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<RealStateView> getAllWithProp() {
		final List found = this
				.find("select new ar.edu.itba.it.paw.domain.entities.RealStateView(photo.id, a.agencyName, a.id, count(p)) from RealStateAgency a inner join a.properties p inner join a.photo photo where p.visible = true group by a.id, photo.id, a.username");
		if (found.isEmpty()) {
			return new ArrayList<RealStateView>();
		}
		return found;
	}
}
