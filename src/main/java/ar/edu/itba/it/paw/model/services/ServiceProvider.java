package ar.edu.itba.it.paw.model.services;

import ar.edu.itba.it.paw.daos.DaoProvider;

public class ServiceProvider {

	private static UserService userService;

	private ServiceProvider() {
	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService(DaoProvider.getUserDao());
		}

		return userService;
	}

}
