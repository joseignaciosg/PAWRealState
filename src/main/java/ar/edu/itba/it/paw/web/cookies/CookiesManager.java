package ar.edu.itba.it.paw.web.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesManager {

	private String name;
	private String pass;
	private HttpServletResponse response;

	private static final String COOKIE_NAME = "userAndPass";

	public CookiesManager(final HttpServletRequest request,
			final HttpServletResponse response) {
		this.response = response;

		final Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (final Cookie c : cookies) {
				if (c.getName().equals(COOKIE_NAME)) {
					final String[] values = c.getValue().split("&");
					this.name = values[0];
					this.pass = values[1];
					break;
				}
			}
			if (this.name == null) {
				this.name = request.getParameter("name");
				this.pass = request.getParameter("pass");

			}
		}
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.pass;
	}

	public void resetUser() {
		this.name = null;
		this.pass = null;
		final Cookie c = new Cookie(COOKIE_NAME, "");
		c.setMaxAge(0);
		this.response.addCookie(c);
	}

	public void setUser(final String name, final String pass) {
		this.name = name;
		this.pass = pass;
		final Cookie c = new Cookie(COOKIE_NAME, name + "&" + pass);
		this.response.addCookie(c);
	}

	public boolean existUser() {
		return this.name != null;
	}

}
