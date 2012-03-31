package ar.edu.itba.it.paw.daos;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryContactRequestDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryPhotoDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryPropertyDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryUserDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.OPERATION;
import ar.edu.itba.it.paw.model.entities.Property.TYPE;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;

public class DaoProvider {

	private static PropertyDao propertyDao;
	private static ContactRequestDao contactrequestDao;
	private static PhotoDao photoDao;
	private static UserDao userDao;

	private DaoProvider() {

	}

	private static void setup() {
		final List<Property> properties = new ArrayList<Property>();
		final List<ContactRequest> resquests = new ArrayList<ContactRequest>();
		final List<User> users = new ArrayList<User>();
		final List<Photo> photos = new ArrayList<Photo>();

		final Services ser1 = new Services(true, true, true, true, true, true);
		final Services ser2 = new Services(false, false, true, true, true,
				false);
		final Services ser3 = new Services(false, true, false, true, false,
				true);
		properties.add(new Property(1, TYPE.HOUSE, OPERATION.RENT, "Soho",
				"Soho St. 120", 10000, 4, 1000, 2000, 15, ser1, "Really good"));
		properties.add(new Property(2, TYPE.APARTMENT, OPERATION.SELL,
				"Caballito", "Novia St.1900", 10000, 4, 1000, 2000, 15, ser2,
				"Really bad"));
		properties.add(new Property(3, TYPE.HOUSE, OPERATION.SELL, "BAlbanera",
				"BAlbanera st. 2736", 10000, 4, 1000, 2000, 15, ser3,
				"Excellect"));

		resquests.add(new ContactRequest(1, "Bin Laden", "binladen@gmail.com",
				"76873627", properties.get(0)));
		resquests.add(new ContactRequest(2, "Fredy Mercury", "yanni@gmail.com",
				"126738", properties.get(1)));
		resquests.add(new ContactRequest(3, "Paco de Lucia", "paco@gmail.com",
				"67384", properties.get(2)));

		users.add(new User(1, "J.P.", "Sartre", "jpsartre@gmail.com",
				"172839127"));
		users.add(new User(1, "Thomas", "Mann", "thomas@gmail.com", "3647823"));
		users.add(new User(1, "Baruch", "Spinoza", "spinoza@gmail.com",
				"74687364"));

		photos.add(new Photo(1, null, "jpg"));
		photos.add(new Photo(2, null, "jpg"));

		propertyDao = new InMemoryPropertyDao(properties);
		contactrequestDao = new InMemoryContactRequestDao(resquests);
		photoDao = new InMemoryPhotoDao(photos);
		userDao = new InMemoryUserDao(users);

	}

	public static UserDao getUserDao() {
		if (userDao == null) {
			setup();
		}
		return userDao;
	}

	public static ContactRequestDao getContactRequestDao() {
		if (contactrequestDao == null) {
			setup();
		}
		return contactrequestDao;
	}

	public static PropertyDao getPropertyDao() {
		if (propertyDao == null) {
			setup();
		}
		return propertyDao;
	}

	public static PhotoDao getPhotoDao() {
		if (photoDao == null) {
			setup();
		}
		return photoDao;
	}
}
