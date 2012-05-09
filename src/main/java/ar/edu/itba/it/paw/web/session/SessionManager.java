package ar.edu.itba.it.paw.web.session;

import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.domain.entities.User;

public class SessionManager implements UserManager {

	private HttpSession session;

	public SessionManager(final HttpSession session) {
		this.session = session;
	}

	public User getCurrentUser() {
		return (User) this.session.getAttribute("user");
	}

	public void setCurrentUser(final User user) {
		this.session.setAttribute("user", user);
	}

	public void logout() {
		this.session.invalidate();
	}
}
