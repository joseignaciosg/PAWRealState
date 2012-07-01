package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.*;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.exceptions.*;
import ar.edu.itba.it.paw.domain.repositories.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;

@Component
public class HibernatePropertyRepository extends AbstractHibernateRepository
		implements PropertyRepository {

	private SessionFactory sessionFactory;

	public HibernatePropertyRepository() {
		super(null);
	}

	@Autowired
	public HibernatePropertyRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	public List<Property> getAll() {
		return this.find("from Property");
	}

	@SuppressWarnings("unchecked")
	public List<Property> getAll(final PropertySearch search) {

		final Criteria q = this.sessionFactory.getCurrentSession()
				.createCriteria(Property.class, "property");

		if (search.getUser() != null) {
			q.add(Restrictions.eq("owner", search.getUser()));
		}

		if (search.getOperation() != null) {
			q.add(Restrictions.eq("operation", search.getOperation()));
		}

		if (search.getType() != null) {
			q.add(Restrictions.eq("type", search.getType()));
		}

		if (search.getPriceHigh() != null) {
			q.add(Restrictions.le("price", search.getPriceHigh()));
		}

		if (search.getPriceLow() != null) {
			q.add(Restrictions.ge("price", search.getPriceLow()));
		}

		if (search.getVisibility() != null) {
			q.add(Restrictions.eq("visible", search.getVisibility()));
		}

		if (search.getRooms() != null && search.getRooms().size() > 0) {
			// AND
			final Conjunction bigAnd = Restrictions.conjunction();
			// WHERE EXISTS()
			for (final RoomSearch roomSearch : search.getRooms()) {
				final DetachedCriteria subQuery = DetachedCriteria.forClass(
						Room.class, "room").setProjection(
						Projections.property("property.id"));
				final Conjunction and = Restrictions.conjunction();
				boolean valid = false;

				if (roomSearch.getMaxSize() != null) {
					valid = true;
					and.add(Restrictions.le("room.size",
							roomSearch.getMaxSize()));
				}

				if (roomSearch.getMinSize() != null) {
					valid = true;
					and.add(Restrictions.ge("room.size",
							roomSearch.getMinSize()));
				}

				if (valid && roomSearch.getType() != null) {
					and.add(Restrictions.eq("room.type", roomSearch.getType()));
					subQuery.add(and);
					bigAnd.add(Subqueries.exists(subQuery));
				}
			}

			q.add(bigAnd);
		}

		if (search.getServices() != null && search.getServices().size() > 0) {

			final Query query = this.sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Property p2 where exists(select 1 from Property p left join "
									+ "p.services as service where service IN (:services) and p = p2 group by p.id "
									+ "having count(service) = :servicesCount)");

			final Long size = (long) search.getServices().size();
			final List<Property> properties = query
					.setParameterList("services", search.getServices())
					.setParameter("servicesCount", size).list();

			final List<Integer> propertiesInt = new ArrayList<Integer>();

			for (final Property property : properties) {
				propertiesInt.add(property.getId());
			}

			if (propertiesInt.size() == 0) {
				return new ArrayList<Property>();
			}
			q.add(Restrictions.in("id", propertiesInt));

		}

		if (search.getPage() != null) {
			q.setFirstResult(search.getPage() * search.getQuant());
			q.setMaxResults(search.getQuant());
		}

		// Order
		if (search.getOrder() == null || search.getOrder().equals(Order.ASC)) {
			q.addOrder(org.hibernate.criterion.Order.asc("price"));
		} else {
			q.addOrder(org.hibernate.criterion.Order.desc("price"));
		}

		// q.addOrder(org.hibernate.criterion.Order.desc("price"));
		if (search.getQuant() != null) {
			q.setMaxResults(search.getQuant());
		}

		q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return q.list();

	}

	public List<Property> getByUser(final User user) {
		return this.find("from Property p where p.user = ?", user);
	}

	public Photo getPhotoById(final Integer id) throws NoSuchEntityException {
		final Photo ans = this.get(Photo.class, id);
		if (ans == null) {
			throw new NoSuchEntityException();
		}
		return ans;
	}

	public boolean sendContactRequest(final ContactRequest request) {
		// TODO: Email service
		return false;
		// return this.mailService.sendMail(request);
	}
}
