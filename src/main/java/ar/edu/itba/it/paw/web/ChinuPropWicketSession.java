package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.api.UserRepository;

public class ChinuPropWicketSession extends WebSession {

	private String username;

	public static ChinuPropWicketSession get() {
		return (ChinuPropWicketSession) Session.get();
	}

	public ChinuPropWicketSession(final Request request) {
		super(request);
	}

	public String getUsername() {
		return this.username;
	}

	public boolean signIn(final String username, final String password,
			final UserRepository repository) {
		final User user = repository.getByName(username);
		if (user != null && user.getPassword().equals(password)) {
			this.username = username;
			return true;
		}
		return false;
	}

	public boolean isSignedIn() {
		return this.username != null;
	}

	public void signOut() {
		this.invalidate();
		this.clear();
	}
}
