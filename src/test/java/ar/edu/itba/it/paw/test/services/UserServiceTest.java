package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryUserDao;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.UserService;
import ar.edu.itba.it.paw.web.session.UserManager;

/**
 * Los usuarios deben poder registrarse en el sistema indicando su nombre,
 * apellido, email, teléfono, usuario y contraseña. Todos los campos son
 * obligatorios. La contraseña debe ingresarse dos veces para verificar que esté
 * correctamente escrita. No puede haber nombres de usuario repetidos.
 */

public class UserServiceTest {

	private UserService service;
	private UserManager userManager;

	@Before
	public void initService() {
		final List<User> users = new ArrayList<User>();
		final User usr = new User("Ben", "Stiller", "ben@gmail.com",
				"16748376", "BenSti", "B3nSt1");
		usr.setID(1);
		users.add(usr);
		this.service = new UserService(new InMemoryUserDao(users));
		this.userManager = new UserManager() {
			private User user;

			public User getCurrentUser() {
				return this.user;
			}

			public void setCurrentUser(final User user) {
				this.user = user;
			};
		};
	}

	@Test
	public void loginTest() {
		Assert.assertTrue(this.service.login("BenSti", "B3nSt1",
				this.userManager));
		Assert.assertNotNull(this.userManager.getCurrentUser());

		this.userManager.setCurrentUser(null);

		Assert.assertFalse(this.service.login("username", "password",
				this.userManager));

		Assert.assertNull(this.userManager.getCurrentUser());

	}

	@Test
	public void registerTest() {
		Assert.assertFalse(this.service.register("Ben", "Sti", "ben@gmail.com",
				"16748376", "BenSti", "B3nSt1", "B3nSt1",
				new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", "lastname", "email",
				"phone", "user", "passwd", "password", new ArrayList<String>()));
		Assert.assertFalse(this.service.register(null, "lastname", "email",
				"phone", "user", "passwd", "passwd", new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", null, "email",
				"phone", "user", "passwd", "passwd", new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", "lastname", null,
				"phone", "user", "passwd", "passwd", new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", "lastname", "email",
				null, "user", "passwd", "passwd", new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", "lastname", "email",
				"phone", null, "passwd", "passwd", new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", "lastname", "email",
				"phone", "user", null, "passwd", new ArrayList<String>()));
		Assert.assertFalse(this.service.register("name", "lastname", "email",
				"phone", "user", "passwd", null, new ArrayList<String>()));
		Assert.assertTrue(this.service.register("name", "lastname", "email",
				"phone", "user", "passwd", "passwd", new ArrayList<String>()));
	}

	@Test
	public void logoutTest() {
		Assert.assertTrue(this.service.logout());
	}
}
