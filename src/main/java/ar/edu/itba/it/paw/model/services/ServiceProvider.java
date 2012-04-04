package ar.edu.itba.it.paw.model.services;

import ar.edu.itba.it.paw.daos.DaoProvider;

public class ServiceProvider {

	private static UserService userService;
	private static PropertyService propertyService;
	private static ContactRequestService contactRequestService;
	private static PhotoService photoService;

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

	public static PhotoService getPhotoService() {
		if (photoService == null) {
			photoService = new PhotoService(DaoProvider.getPhotoDao());

		}

		return photoService;
	}

	public static ContactRequestService getContactRequestService() {
		if (contactRequestService == null) {
			contactRequestService = new ContactRequestService(
					DaoProvider.getContactRequestDao());
		}

		return contactRequestService;
	}

}
