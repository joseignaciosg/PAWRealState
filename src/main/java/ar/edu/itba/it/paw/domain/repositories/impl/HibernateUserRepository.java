package ar.edu.itba.it.paw.domain.repositories.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.AbstractHibernateRepository;
import ar.edu.itba.it.paw.domain.repositories.api.UserRepository;
import ar.edu.itba.it.paw.domain.services.MailService;

@Repository
public class HibernateUserRepository extends AbstractHibernateRepository
		implements UserRepository {

	@Autowired
	private MailService mailService;

	@Autowired
	public HibernateUserRepository(final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public User getByNameAndPassword(final String username,
			final String password) {
		return (User) this.find(
				"from User u where u.username like ? and u.password like ?",
				username, password).get(0);
	}

	public boolean sendContactRequest(final ContactRequest request,
			final User user) {
		return this.mailService.sendMail(request.getName(), user.getMail());
	}
}
