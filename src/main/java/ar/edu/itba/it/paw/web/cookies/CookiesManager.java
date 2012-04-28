package ar.edu.itba.it.paw.web.cookies;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesManager {

	private String name;
	private String pass;
	private String remember;
	private HttpServletResponse response;

	private static final String COOKIE_NAME = "userAndPass";

	public CookiesManager(final ServletRequest request,
			final ServletResponse response) {
		this.response = (HttpServletResponse) response;

		final Cookie[] cookies = ((HttpServletRequest) request).getCookies();
		if (cookies != null) {
			for (final Cookie c : cookies) {
				if (c.getName().equals(COOKIE_NAME)) {
					try {
						final String[] values = c.getValue().split("&");
						this.name = values[0];
						this.pass = values[1];
						this.remember = values[2];
					} catch (final Exception e) {

					}
					break;
				}
			}
			if (this.name == null) {
				this.name = request.getParameter("name");
				this.pass = request.getParameter("pass");
				this.remember = request.getParameter("remember");
			}
		}
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.pass;
	}

	public String getRemember() {
		return this.remember;
	}

	public void resetUser() {
		this.name = null;
		this.pass = null;
		this.remember = null;
		final Cookie c = new Cookie(COOKIE_NAME, "");
		c.setMaxAge(0);
		this.response.addCookie(c);
	}

	public void setUser(final String name, final String pass,
			final String remember) {
		this.name = name;
		this.pass = pass;
		this.remember = remember;
		final Cookie c = new Cookie(COOKIE_NAME, name + "&" + pass + "&"
				+ remember);
		this.response.addCookie(c);
	}

	public boolean existUser() {
		return this.name != null;
	}

}
