package ar.edu.itba.it.paw.web;

import org.apache.wicket.*;
import org.apache.wicket.protocol.http.*;
import org.apache.wicket.request.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

public class RealStateSession extends WebSession {

	private String username;

	public static RealStateSession get() {
		return (RealStateSession) Session.get();
	}

	public RealStateSession(final Request request) {
		super(request);
	}

	public String getUsername() {
		return this.username;
	}

	public boolean signIn(final String username, final String password,
			final UserRepository users) {
		final User user = users.getByName(username);
		if (user != null && user.checkPassword(password)) {
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
