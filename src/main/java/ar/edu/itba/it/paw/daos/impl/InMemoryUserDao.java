package ar.edu.itba.it.paw.daos.impl;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.db.ConnectionManager;
import ar.edu.itba.it.paw.model.entities.User;

public class InMemoryUserDao implements UserDao {

	public InMemoryUserDao(final ConnectionManager manager) {
	}

	public User getById(final String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(final User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveOrUpdate(final User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
