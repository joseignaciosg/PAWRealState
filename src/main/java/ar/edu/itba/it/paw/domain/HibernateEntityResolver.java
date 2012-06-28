package ar.edu.itba.it.paw.domain;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import ar.edu.itba.it.paw.domain.entities.*;

@Component
@SuppressWarnings("unchecked")
public class HibernateEntityResolver implements EntityResolver {

	private final SessionFactory sessionFactory;

	@Autowired
	public HibernateEntityResolver(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public <T> T fetch(final Class<T> type, final Integer id) {
		try {
			return (T) this.getSession().get(type, id);
		} catch (final HibernateException ex) {
			throw new HibernateException("Problem while fetching ("
					+ type.getSimpleName() + ", " + id.toString() + ")", ex);
		}
	}

	public Integer getId(final Object object) {
		Assert.isInstanceOf(PersistentEntity.class, object,
				"This entity resolver only hanldes objects implementing PersistentEntity");
		try {
			this.getSession().flush();
			final Integer id = ((PersistentEntity) object).getId();
			if (id == null) {
				throw new TransientObjectException(
						"Object doesn't have an id associated!");
			}
			return id;
		} catch (final HibernateException ex) {
			throw new HibernateException("Problem while retrieving id for "
					+ object.toString(), ex);
		}
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
