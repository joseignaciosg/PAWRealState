package ar.edu.itba.it.paw.web;

import javax.servlet.http.*;

import org.apache.wicket.*;
import org.apache.wicket.protocol.http.*;
import org.apache.wicket.request.*;
import org.apache.wicket.request.http.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

@SuppressWarnings("serial")
public class RealStateSession extends WebSession {

	private final int MONTH_IN_SECONDS = 2678400;

	private String username;
	private transient Response response;
	private transient WebRequest request;

	public static RealStateSession get() {
		return (RealStateSession) Session.get();
	}

	public RealStateSession(final Request request, final Response response) {
		super(request);
		this.response = response;
		this.request = ((WebRequest) request);
	}

	public String getUsername() {

		if (this.getRememberKey().equals("USERNAME")) {
			return this.request.getCookie("remember_name").getValue();
		}

		return this.username;
	}

	public String getRememberKey() {
		if (this.request.getCookie("remember_key") != null) {
			return this.request.getCookie("remember_key").getValue();
		} else {
			return "";
		}
	}

	public boolean signIn(final String username, final String password,
			final UserRepository users, final String rememberKey) {
		final User user = users.getByName(username);
		if (user != null && user.checkPassword(password)) {
			this.username = username;

			this.createCookie("remember_key", rememberKey);

			if (rememberKey.equals("SESSION")) {
				this.createCookie("remember_session", username);
				this.deleteCookie("remember_name", "");
			} else if (rememberKey.equals("USERNAME")) {
				this.deleteCookie("remember_session", "");
				this.createCookie("remember_name", username);
			}

			return true;
		}
		return false;
	}

	private void createCookie(final String key, final String value) {
		this.modifyCookie(key, value, this.MONTH_IN_SECONDS);
	}

	private void deleteCookie(final String key, final String value) {
		this.modifyCookie(key, value, 0);
	}

	private void modifyCookie(final String key, final String value,
			final int maxAge) {
		final Cookie rememberCookie = new Cookie(key, value);
		rememberCookie.setMaxAge(maxAge);
		((HttpServletResponse) ((WebResponse) this.response)
				.getContainerResponse()).addCookie(rememberCookie);
	}

	public boolean isSignedIn() {

		if (this.getRememberKey().equals("SESSION")) {
			if (this.request.getCookie("remember_session") != null) {
				final String username = this.request.getCookie(
						"remember_session").getValue();
				this.username = username;
			}
		}

		return this.username != null;
	}

	public void signOut() {
		this.deleteCookie("remember_name", this.username);
		this.deleteCookie("remember_session", this.username);
		this.deleteCookie("remember_key", this.username);
		this.invalidate();
		this.clear();
	}
}
