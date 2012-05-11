package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends PersistentEntity {

	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String username;
	private String password;

	@OneToMany(mappedBy = "owner")
	private List<Property> properties = new ArrayList<Property>();

	protected User() {
	}

	public User(final String name, final String surname, final String mail,
			final String telephone, final String username, final String password) {
		this.firstname = name;
		this.lastname = surname;
		this.email = mail;
		this.phone = telephone;
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getTelephone() {
		return this.phone;
	}

	public String getName() {
		return this.firstname;
	}

	public String getSurname() {
		return this.lastname;
	}

	public String getMail() {
		return this.email;
	}

	public String getUsername() {
		return this.username;
	}

	public List<Property> getProperties() {
		return this.properties;
	}

}
