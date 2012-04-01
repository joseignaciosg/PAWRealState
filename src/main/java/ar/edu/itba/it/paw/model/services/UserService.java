package ar.edu.itba.it.paw.model.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.web.session.UserManager;

public class UserService {

	private UserDao dao;

	public UserService(final UserDao inMemoryUserDao) {
		this.dao = inMemoryUserDao;
	}

	public boolean login(final String username, final String password,
			final UserManager manager) {
		final User usr = this.dao.getUser(username);
		if (usr != null) {
			if (usr.getPassword().equals(password)) {
				manager.setCurrentUser(usr);
				return true;
			}
		}
		return false;
	}

	// TODO: Validate numeric telephone
	// TODO: Validate email format
	// Hint: Clase "Pattern"
	public boolean register(final String name, final String lastname,
			final String email, final String phone, final String username,
			final String password, final String repeatedPassword,
			final List<String> errors) {

		if (name == null) {
			errors.add("Su nombre es un campo requerido");
		}

		if (lastname == null) {
			errors.add("Su apellido es un campo requerido");
		}

		if (password == null || repeatedPassword == null) {
			errors.add("Su contraseña y su confirmación son campos requeridos");
		} else if (!repeatedPassword.equals(password)) {
			errors.add("Las contraseñas no coinciden");
		}

		if (email == null) {
			errors.add("Su email es un campo requerido");
		}

		if (phone == null) {
			errors.add("Su teléfono es un campo requerido");
		}

		if (username == null || this.dao.getUser(username) != null) {
			errors.add("Ya existe un usuario con el nombre dado");
		}

		if (errors.size() > 0) {
			return false;
		}

		final User usr = new User(name, lastname, email, phone, username,
				password);

		this.dao.saveOrUpdate(usr);

		return true;
	}

	public boolean logout() {
		// TODO cerrar sesion
		return true;
	}
}