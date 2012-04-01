package ar.edu.itba.it.paw.model.services;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.User;

public class UserService {

	private UserDao dao;

	public UserService(final UserDao inMemoryUserDao) {

		this.dao = inMemoryUserDao;
	}

	public boolean login(final String username, final String password) {

		final User usr = this.dao.getUser(username);
		if (usr != null) {
			if (usr.getPassword().equals(password)) {
				// TODO iniciar sesion
				return true;
			}
		}
		return false;
	}

	public boolean register(final String name, final String lastname,
			final String email, final String phone, final String username,
			final String password) {

		if (name == null || lastname == null || email == null || phone == null
				|| username == null || password == null) {
			return false;
		}

		if (this.dao.getUser(username) != null) {
			return false;
		}
		final User usr = new User(name, lastname, email, phone, username,
				password);

		return this.dao.saveOrUpdate(usr);
	}

	public boolean logout() {
		// TODO cerrar sesion
		return true;
	}
}