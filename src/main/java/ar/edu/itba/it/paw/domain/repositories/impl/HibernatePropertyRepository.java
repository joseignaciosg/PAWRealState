package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.List;

import javax.persistence.OrderBy;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;

@Repository
public class HibernatePropertyRepository extends AbstractHibernateRepository
		implements PropertyRepository {

	@Autowired
	public HibernatePropertyRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Property> getAll() {
		return this.find("from Property");
	}

	public List<Property> getAll(final Operation op, final Type type,
			final Integer pricelow, final Integer pricehigh,
			final Integer page, final Integer quant, final OrderBy order,
			final Boolean visible) {
		return this.find("from Property");
	}

	public List<Property> getByUser(final User user) {
		return this.find("from Property p where p.user = ?", user);
	}
}
