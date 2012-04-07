package ar.edu.itba.it.paw.model.entities;

import java.util.Collection;

import ar.edu.itba.it.paw.daos.impl.sql.factories.UserFactory;

public class LazyUser extends User {

	private UserFactory userFactory;
	private User user;

	public LazyUser(final UserFactory userFactory, final int ID) {
		this.userFactory = userFactory;
		super.setId(ID);
		super.setNew(false);
		super.setDirty(false);
	}

	@Override
	public Integer getId() {
		return super.getId();
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
	public boolean isDirty() {
		return this.getUser().isDirty();
	}

	@Override
	public boolean isNew() {
		return this.getUser().isNew();
	}

	@Override
	public void setDirty(final boolean dirty) {
		this.getUser().setDirty(dirty);
	}

	@Override
	public void setId(final Integer iD) {
		this.getUser().setId(iD);
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
	public void setNew(final boolean isnew) {
		this.getUser().setNew(isnew);
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
	public void setVisible(final boolean isnew) {
		this.getUser().setVisible(isnew);
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
