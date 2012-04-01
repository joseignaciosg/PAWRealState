package ar.edu.itba.it.paw.model.entities;

import java.util.ArrayList;
import java.util.List;

public class User implements Entity {

	private Integer ID;
	private String name;
	private String surname;
	private String mail;
	private String telephone;
	private String username;
	private String password;
	private boolean dirty;
	private List<Property> properties;

	public User(final String name, final String surname, final String mail,
			final String telephone, final String username, final String password) {
		super();
		this.properties = new ArrayList<Property>();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.telephone = telephone;
		this.username = username;
		this.password = password;
		this.setDirty(false);
	}

	public List<Property> getProperties() {
		return this.properties;
	}

	public void addProperty(final Property property) {
		this.properties.add(property);
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
		this.setDirty(true);
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	public Integer getID() {
		return this.ID;
	}

	public void setID(final Integer iD) {
		this.ID = iD;
		this.setDirty(true);
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
		this.setDirty(true);
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
		this.setDirty(true);
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
		this.setDirty(true);
	}

	public boolean isDirty() {
		return (this.dirty == false) ? false : true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.ID == null) ? 0 : this.ID.hashCode());
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
		if (this.ID == null) {
			if (other.ID != null) {
				return false;
			}
		} else if (!this.ID.equals(other.ID)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [ID=" + this.ID + ", name=" + this.name + ", surname="
				+ this.surname + ", mail=" + this.mail + ", telephone="
				+ this.telephone + ", username=" + this.username
				+ ", password=" + this.password + ", dirty=" + this.dirty + "]";
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

}
