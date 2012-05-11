package ar.edu.itba.it.paw.web.command;

import ar.edu.itba.it.paw.web.session.UserManager;

public class LoginForm {

	private String user_username;
	private String user_password;
	private String remember;
	private UserManager userManager;

	public LoginForm() {
	}

	public LoginForm(final String user_username, final String user_password,
			final String remember, final UserManager userManager) {
		this.user_username = user_username;
		this.user_password = user_password;
		this.remember = remember;
		this.userManager = userManager;
	}

	public String getUser_username() {
		return this.user_username;
	}

	public void setUser_username(final String user_username) {
		this.user_username = user_username;
	}

	public String getUser_password() {
		return this.user_password;
	}

	public void setUser_password(final String user_password) {
		this.user_password = user_password;
	}

	public String getRemember() {
		return this.remember;
	}

	public void setRemember(final String remember) {
		this.remember = remember;
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public void setUserManager(final UserManager userManager) {
		this.userManager = userManager;
	}

}
