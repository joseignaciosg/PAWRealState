package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.Collection;

import ar.edu.itba.it.paw.utils.collections.CollectionWithMemory;

public class User extends BaseEntity {

	private String name;
	private String surname;
	private String mail;
	private String telephone;
	private String username;
	private String password;
	private Collection<Property> properties;

	public void setProperties(final Collection<Property> properties) {
		this.properties = properties;
	}

	protected User() {
	}

	public User(final String name, final String surname, final String mail,
			final String telephone, final String username, final String password) {
		this(null, name, surname, mail, telephone, username, password);
	}

	public User(final Integer userId, final String name, final String surname,
			final String mail, final String telephone, final String username,
			final String password) {
		super(userId);
		this.properties = new CollectionWithMemory<Property>(
				new ArrayList<Property>());
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.telephone = telephone;
		this.username = username;
		this.password = password;
	}

	public Collection<Property> getProperties() {
		return this.properties;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
		this.setDirty();
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
		this.setDirty();
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
		this.setDirty();
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
		this.setDirty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.mail == null) ? 0 : this.mail.hashCode());
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result
				+ ((this.password == null) ? 0 : this.password.hashCode());
		result = prime * result
				+ ((this.properties == null) ? 0 : this.properties.hashCode());
		result = prime * result
				+ ((this.surname == null) ? 0 : this.surname.hashCode());
		result = prime * result
				+ ((this.telephone == null) ? 0 : this.telephone.hashCode());
		result = prime * result
				+ ((this.username == null) ? 0 : this.username.hashCode());
		return result;
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
		if (this.mail == null) {
			if (other.mail != null) {
				return false;
			}
		} else if (!this.mail.equals(other.mail)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!this.password.equals(other.password)) {
			return false;
		}
		if (this.properties == null) {
			if (other.properties != null) {
				return false;
			}
		} else if (!this.properties.equals(other.properties)) {
			return false;
		}
		if (this.surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!this.surname.equals(other.surname)) {
			return false;
		}
		if (this.telephone == null) {
			if (other.telephone != null) {
				return false;
			}
		} else if (!this.telephone.equals(other.telephone)) {
			return false;
		}
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
		return "User [ID=" + this.getId() + ", name=" + this.name
				+ ", surname=" + this.surname + ", mail=" + this.mail
				+ ", telephone=" + this.telephone + ", username="
				+ this.username + ", password=" + this.password + "]";
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
