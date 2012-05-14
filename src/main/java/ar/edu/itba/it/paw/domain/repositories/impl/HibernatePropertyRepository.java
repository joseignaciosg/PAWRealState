package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Room;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.NoSuchEntityException;
import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.domain.repositories.api.RoomSearch;
import ar.edu.itba.it.paw.domain.services.MailService;

@Repository
public class HibernatePropertyRepository extends AbstractHibernateRepository
		implements PropertyRepository {

	@Autowired
	private MailService mailService;
	private SessionFactory sessionFactory;

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

		if (search.getVisibility() != null) {
			q.add(Restrictions.eq("visible", search.getVisibility()));
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

		if (search.getUser() != null) {
			q.add(Restrictions.ge("owner", search.getUser()));
		}

		if (search.getOrder() != null) {
			if (search.getOrder().equals(Order.ASC)) {
				q.addOrder(org.hibernate.criterion.Order.asc("price"));
			}
			if (search.getOrder().equals(Order.DESC)) {
				q.addOrder(org.hibernate.criterion.Order.desc("price"));
			}
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
			System.out.println(propertiesInt);
			q.add(Restrictions.in("id", propertiesInt));
		}

		// q.addOrder(org.hibernate.criterion.Order.desc("price"));
		List<Property> list;
		if (search.getQuant() != null) {
			list = q.list().subList(0, search.getQuant());
		} else {
			list = q.list();
		}
		q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return list;

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
		return this.mailService.sendMail(request);
	}
}
