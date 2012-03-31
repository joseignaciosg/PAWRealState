package ar.edu.itba.it.paw.model.entities;

public class User implements Entity {

	private Integer ID;
	private String name;
	private String surname;
	private String mail;
	private String telephone;

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	private boolean dirty;

	public Integer getID() {
		return this.ID;
	}

	public void setID(final Integer iD) {
		this.ID = iD;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public boolean isDirty() {
		return (this.dirty == false) ? false : true;
	}

}
