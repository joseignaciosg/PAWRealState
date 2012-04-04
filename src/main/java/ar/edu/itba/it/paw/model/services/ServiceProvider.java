package ar.edu.itba.it.paw.model.services;

import ar.edu.itba.it.paw.daos.DaoProvider;

public class ServiceProvider {

	private static UserService userService;
	private static PropertyService propertyService;
	private static ContactRequestService contactRequest;

	private ServiceProvider() {
	}

	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService(DaoProvider.getUserDao());
		}

		return userService;
	}

	public static PropertyService getPropertyService() {
		if (propertyService == null) {
			propertyService = new PropertyService(DaoProvider.getPropertyDao(),
					DaoProvider.getUserDao());
		}

		return propertyService;
	}

	public static ContactRequestService getContactRequestService() {
		if (contactRequest == null) {
			contactRequest = new ContactRequestService(
					DaoProvider.getPropertyDao(),
					DaoProvider.getContactRequestDao());
		}

		return contactRequest;
	}

}
