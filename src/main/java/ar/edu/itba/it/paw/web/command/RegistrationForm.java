package ar.edu.itba.it.paw.web.command;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.RealStateAgency;
import ar.edu.itba.it.paw.domain.entities.User;

public class RegistrationForm {

	private String userName;
	private String password;
	private String repeatedPassword;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String type;
	private String agencyName;
	private CommonsMultipartFile photo;

	public RegistrationForm() {
	}

	public RegistrationForm(final String username, final String password,
			final String repeatedPassword, final String email,
			final String name, final String lastName, final String phone,
			final String agencyName, final String type,
			final CommonsMultipartFile photo) {
		this.userName = username;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
		this.email = email;
		this.firstName = name;
		this.lastName = lastName;
		this.phone = phone;
		this.setPhoto(photo);
		this.setType(type);
		this.setAgencyName(agencyName);
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

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getAgencyName() {
		return this.agencyName;
	}

	public void setAgencyName(final String agencyName) {
		this.agencyName = agencyName;
	}

	public User build() {
		if (this.type != null) {
			if (this.type.equals("RealStateAgency")) {
				return new RealStateAgency(this.firstName, this.lastName,
						this.email, this.phone, this.userName, this.password,
						this.agencyName, new Photo(this.photo.getBytes(),
								"jpeg"));
			} else {
				return new User(this.firstName, this.lastName, this.email,
						this.phone, this.userName, this.password);
			}

		}
		return null;
	}

	public CommonsMultipartFile getPhoto() {
		return this.photo;
	}

	public void setPhoto(final CommonsMultipartFile photo) {
		this.photo = photo;
	}
}
