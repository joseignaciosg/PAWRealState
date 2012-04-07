package ar.edu.itba.it.paw.daos.impl.sql.factories;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.utils.Factory;

public class UserFactory implements Factory<User> {

	private UserDao dao;
	private int id;

	public UserFactory(final UserDao dao, final int id) {
		this.dao = dao;
		this.id = id;
	}

	public User create() {
		return this.dao.getById(this.id);
	}

}
