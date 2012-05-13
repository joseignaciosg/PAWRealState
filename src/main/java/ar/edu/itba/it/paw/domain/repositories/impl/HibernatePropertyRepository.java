package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.NoSuchEntityException;
import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
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
				.createCriteria(Property.class);

		// Si es una busqueda de un usuario
		if (search.getUser() != null) {
			q.add(Restrictions.eq("owner", search.getUser()));
			final List<Property> list = q.list();
			return list;
		}

		// Si no es para un usuario sigo con la busqueda, Simple Elements
		if (search.getType() != null) {
			q.add(Restrictions.eq("type", search.getType()));
		}
		if (search.getOperation() != null) {
			q.add(Restrictions.eq("operation", search.getOperation()));
		}
		if (search.getPriceHigh() != null && search.getPriceLow() != null) {
			q.add(Restrictions.between("price", search.getPriceLow(),
					search.getPriceHigh()));
		} else if (search.getPriceHigh() != null) {
			q.add(Restrictions.le("price", search.getPriceHigh()));
		} else if (search.getPriceLow() != null) {
			q.add(Restrictions.ge("price", search.getPriceLow()));
		}

		q.add(Restrictions.eq("visible", Boolean.TRUE));

		// Rooms

		// Esto sirve para armar un OR de Restrictions c/ Disjuntion
		final Disjunction disjRooms = Restrictions.disjunction();

		final List<RoomSearch> roomList = search.getRooms();
		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				final RoomSearch actualRoom = roomList.get(i);
				if (actualRoom.getMinSize() > 0 && actualRoom.getMaxSize() != 0) {
					disjRooms.add(Expression.and(
							Restrictions.eq("type", actualRoom.getType()),
							Restrictions.between("size",
									actualRoom.getMinSize(),
									actualRoom.getMaxSize())));
				} else if (actualRoom.getMinSize() == 0) {
					disjRooms.add(Expression.and(
							Restrictions.eq("type", actualRoom.getType()),
							Restrictions.le("size", actualRoom.getMaxSize())));
				} else {
					disjRooms.add(Expression.and(
							Restrictions.eq("type", actualRoom.getType()),
							Restrictions.ge("size", actualRoom.getMinSize())));
				}

			}
			q.add(disjRooms);
		}
		//
		// // Services
		final Disjunction disjServices = Restrictions.disjunction();

		if (search.getServices() != null) {
			for (int i = 0; i < search.getServices().size(); i++) {
				final Service service = search.getServices().get(0);
				disjServices.add(Restrictions.eq("element", service.name()));
			}
			q.add(disjServices);
		}
		// Order
		if (search.getOrder() == null || search.getOrder().equals("ASC")) {
			q.addOrder(org.hibernate.criterion.Order.asc("price"));
		} else if (search.getOrder().equals("DESC")) {
			q.addOrder(org.hibernate.criterion.Order.desc("price"));
		}

		// q.addOrder(org.hibernate.criterion.Order.desc("price"));
		final List<Property> list = q.list();
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
