package ar.edu.itba.it.paw.model.entities;

import java.util.Collection;

import ar.edu.itba.it.paw.daos.impl.sql.factories.UserFactory;

public class LazyUser extends User {

	private UserFactory userFactory;
	private User user;

	public LazyUser(final UserFactory userFactory, final int ID) {
		super.setId(ID);
		this.userFactory = userFactory;

	}

	@Override
	public String getMail() {
		return this.getUser().getMail();
	}

	@Override
	public String getName() {
		return this.getUser().getName();
	}

	@Override
	public String getPassword() {
		return this.getUser().getPassword();
	}

	@Override
	public void setProperties(final Collection<Property> properties) {
		this.getUser().getProperties();
	}

	@Override
	public Collection<Property> getProperties() {
		return this.getUser().getProperties();
	}

	@Override
	public String getSurname() {
		return this.getUser().getSurname();
	}

	@Override
	public String getTelephone() {
		return this.getUser().getTelephone();
	}

	@Override
	public String getUsername() {
		return this.getUser().getUsername();
	}

	@Override
	public boolean equals(final Object obj) {
		return this.getUser().equals(obj);
	}

	@Override
	public int hashCode() {
		return this.getUser().hashCode();
	}

	@Override
	public void setMail(final String mail) {
		this.getUser().setMail(mail);
	}

	@Override
	public void setName(final String name) {
		this.getUser().setName(name);
	}

	@Override
	public void setPassword(final String password) {
		this.getUser().setPassword(password);
	}

	@Override
	public void setSurname(final String surname) {
		this.getUser().setSurname(surname);
	}

	@Override
	public void setTelephone(final String telephone) {
		this.getUser().setTelephone(telephone);
	}

	@Override
	public void setUsername(final String username) {
		this.getUser().setUsername(username);
	}

	@Override
	public String toString() {
		return this.getUser().toString();
	}

	private User getUser() {
		if (this.user == null) {
			this.user = this.userFactory.create();
		}

		return this.user;
	}

}
