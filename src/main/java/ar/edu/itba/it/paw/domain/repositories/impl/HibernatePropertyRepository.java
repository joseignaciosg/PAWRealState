package ar.edu.itba.it.paw.domain.repositories.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.services.MailService;

@Repository
public class HibernatePropertyRepository extends AbstractHibernateRepository
		implements PropertyRepository {

	@Autowired
	private MailService mailService;

	@Autowired
	public HibernatePropertyRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Property> getAll() {
		return this.find("from Property");
	}

	public List<Property> getAll(final PropertySearch search) {
		this.find("from Property p where p = ?", search);
		return this.find("from Property");
	}

	public List<Property> getByUser(final User user) {
		return this.find("from Property p where p.user = ?", user);
	}

	public Photo getPhotoById(final Integer id) {
		return (Photo) this.find("from Photo photo where photo.id = ?", id)
				.get(0);
	}

	public boolean sendContactRequest(final ContactRequest request) {
		return this.mailService.sendMail(request);
	}
}
