package ar.edu.itba.it.paw.model.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.web.session.UserManager;

@Component
public class UserService {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private UserDao dao;
	private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
	private Pattern phonePattern = Pattern.compile("(\\+)?\\d+");

	public UserService() {
	}

	@Autowired
	public UserService(final UserDao inMemoryUserDao) {
		this.dao = inMemoryUserDao;
	}

	public User getById(final Integer id) {
		return this.dao.getById(id);
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

		if (email == null || !this.emailPattern.matcher(email).find()) {
			errors.add("Su email debe ser un email válido");
		}

		if (phone == null || !this.phonePattern.matcher(phone).find()) {
			errors.add("Su teléfono debe ser válido");
		}

		if (username == null || this.dao.getUser(username) != null) {
			errors.add("Ya existe un usuario con el nombre dado");
		}

		if (username != null && username.trim().equals("")) {
			errors.add("Debe proveer un nombre de usuario");
		}

		if (errors.size() > 0) {
			return false;
		}

		final User usr = new User(name, lastname, email, phone, username,
				password);

		this.dao.saveOrUpdate(usr);

		return true;
	}

	public boolean logout(final UserManager manager) {
		if (manager.getCurrentUser() == null) {
			return false;
		}
		manager.setCurrentUser(null);
		return true;
	}
}
