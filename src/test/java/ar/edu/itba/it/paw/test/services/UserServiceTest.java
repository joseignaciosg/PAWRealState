package ar.edu.itba.it.paw.test.services;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryUserDao;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.UserService;

/**
 * Los usuarios deben poder registrarse en el sistema indicando su nombre,
 * apellido, email, teléfono, usuario y contraseña. Todos los campos son
 * obligatorios. La contraseña debe ingresarse dos veces para verificar que esté
 * correctamente escrita. No puede haber nombres de usuario repetidos.
 */

public class UserServiceTest {

	private UserService service;

	@Before
	public void initService() {
		final List<User> users = new ArrayList<User>();
		final User usr = new User("Ben", "Stiller", "ben@gmail.com",
				"16748376", "BenSti", "B3nSt1");
		usr.setID(1);
		users.add(usr);
		this.service = new UserService(new InMemoryUserDao(users));
	}

	@Test
	public void loginTest() {
		Assert.assertTrue(this.service.login("BenSti", "B3nSt1"));
		Assert.assertFalse(this.service.login("username", "password"));
	}

	@Test
	public void registerTest() {
		Assert.assertFalse(this.service.register("Ben", "Sti", "ben@gmail.com",
				"16748376", "BenSti", "B3nSt1"));
		Assert.assertTrue(this.service.register("name", "lastname", "email",
				"phone", "user", "passwd"));
	}

	@Test
	public void logoutTest() {
		Assert.assertTrue(this.service.logout());
	}
}
