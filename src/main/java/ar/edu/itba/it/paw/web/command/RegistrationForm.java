package ar.edu.itba.it.paw.web.command;

import ar.edu.itba.it.paw.domain.entities.User;

public class RegistrationForm implements BuilderForm<User> {

	private String userName;
	private String password;
	private String repeatedPassword;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;

	public RegistrationForm() {
	}

	public RegistrationForm(final String username, final String password,
			final String repeatedPassword, final String email,
			final String name, final String lastName, final String phone) {
		this.userName = username;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
		this.email = email;
		this.firstName = name;
		this.lastName = lastName;
		this.phone = phone;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String username) {
		this.userName = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return this.repeatedPassword;
	}

	public void setRepeatedPassword(final String repeatedPasword) {
		this.repeatedPassword = repeatedPasword;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public User build() {
		return new User(this.userName, this.lastName, this.email, this.phone,
				this.firstName, this.password);
	}
}
