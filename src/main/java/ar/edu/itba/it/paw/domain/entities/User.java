package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class User extends PersistentEntity {

	private String name;
	private String surname;
	private String mail;
	private String telephone;
	private String username;
	private String password;

	@OneToMany(mappedBy = "owner")
	private List<Property> properties = new ArrayList<Property>();

	protected User() {
	}

	public User(final String name, final String surname, final String mail,
			final String telephone, final String username, final String password) {
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.telephone = telephone;
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getMail() {
		return this.mail;
	}

	public String getUsername() {
		return this.username;
	}

	public List<Property> getProperties() {
		return this.properties;
	}

}
