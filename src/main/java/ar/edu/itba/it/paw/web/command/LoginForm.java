package ar.edu.itba.it.paw.web.command;


public class LoginForm {

	private String user_username;
	private String user_password;
	private String remember;

	public LoginForm() {
	}

	public LoginForm(final String user_username, final String user_password,
			final String remember) {
		this.user_username = user_username;
		this.user_password = user_password;
		this.remember = remember;
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

}
