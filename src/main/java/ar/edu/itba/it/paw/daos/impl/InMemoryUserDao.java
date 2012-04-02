package ar.edu.itba.it.paw.daos.impl;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.User;

public class InMemoryUserDao implements UserDao {

	List<User> users = null;

	public InMemoryUserDao(final List<User> data) {
		this.users = data;
	}

	public User getById(final Integer id) {
		for (final User u : this.users) {
			if (u.getID().equals(id)) {
				return u;
			}
		}
		return null;
	}

	public boolean delete(final User obj) {
		return this.users.remove(obj);
	}

	public boolean saveOrUpdate(final User obj) {
		if (!this.users.contains(obj)) {
			obj.setDirty(false);
			return this.users.add(obj);
		} else {
			if (obj.isDirty()) {
				this.users.remove(obj);
				obj.setDirty(false);
				return this.users.add(obj);
			} else {
				return false;
			}
		}

	}

	public List<User> getAll() {
		return this.users;
	}

	public User getUser(final String username) {
		for (final User u : this.users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

}
