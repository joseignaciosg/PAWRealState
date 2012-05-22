package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
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

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [firstname=" + this.firstname + ", lastname="
				+ this.lastname + ", email=" + this.email + ", phone="
				+ this.phone + ", username=" + this.username + ", password="
				+ this.password + ", properties=" + this.properties + "]";
	}

	public String getMail() {
		return this.email;
	}

	public String getName() {
		return this.firstname;
	}

	public String getPassword() {
		return this.password;
	}

	public List<Property> getProperties() {
		return this.properties;
	}

	public String getSurname() {
		return this.lastname;
	}

	public String getTelephone() {
		return this.phone;
	}

	public String getUsername() {
		return this.username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result
				+ ((this.firstname == null) ? 0 : this.firstname.hashCode());
		result = prime * result
				+ ((this.lastname == null) ? 0 : this.lastname.hashCode());
		result = prime * result
				+ ((this.password == null) ? 0 : this.password.hashCode());
		result = prime * result
				+ ((this.phone == null) ? 0 : this.phone.hashCode());
		result = prime * result
				+ ((this.properties == null) ? 0 : this.properties.hashCode());
		result = prime * result
				+ ((this.username == null) ? 0 : this.username.hashCode());
		return result;
	}

}
