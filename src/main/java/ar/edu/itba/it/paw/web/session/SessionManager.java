package ar.edu.itba.it.paw.web.session;

import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

public class SessionManager implements UserManager {

	private HibernateUserRepository repository;
	private HttpSession session;

	public SessionManager(final HibernateUserRepository repository,
			final HttpSession session) {
		this.repository = repository;
		this.session = session;
	}

	public User getCurrentUser() {
		final Integer id = (Integer) this.session.getAttribute("user_id");

		if (id == null) {
			return null;
		} else {
			return this.repository.get(User.class, id);
		}
	}

	public void setCurrentUser(final User user) {
		this.session.setAttribute("user_id", user.getId());
	}

	public void logout() {
		this.session.invalidate();
	}

	public void forgetCurrentUser() {
		this.session.setAttribute("user_id", null);
	}
}
