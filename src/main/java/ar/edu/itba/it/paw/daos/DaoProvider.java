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
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
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
		properties.add(new Property(1, Type.HOUSE, Operation.RENT, "Soho",
				"Soho St. 120", 10000, 4, 1000, 2000, 15, ser1, "Really good"));
		properties.add(new Property(2, Type.APARTMENT, Operation.SELL,
				"Caballito", "Novia St.1900", 10000, 4, 1000, 2000, 15, ser2,
				"Really bad"));
		properties.add(new Property(3, Type.HOUSE, Operation.SELL, "BAlbanera",
				"BAlbanera st. 2736", 10000, 4, 1000, 2000, 15, ser3,
				"Excellect"));

		resquests.add(new ContactRequest(1, "Bin Laden", "binladen@gmail.com",
				"76873627", properties.get(0)));
		resquests.add(new ContactRequest(2, "Fredy Mercury", "yanni@gmail.com",
				"126738", properties.get(1)));
		resquests.add(new ContactRequest(3, "Paco de Lucia", "paco@gmail.com",
				"67384", properties.get(2)));

		User tmp = new User("J.P.", "Sartre", "jpsartre@gmail.com",
				"172839127", "jpsartre", "jojo");

		final List<Property> propertyList = new ArrayList<Property>();
		final Services service = new Services(true, true, true, true, false,
				true);

		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(1000), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1");

		final Property prop2 = new Property(Integer.valueOf(2), Type.HOUSE,
				Operation.RENT, "BarrioNorte", "Junca 460",
				Integer.valueOf(501), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2");

		final Property prop3 = new Property(Integer.valueOf(3), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		final Property prop4 = new Property(Integer.valueOf(4), Type.APARTMENT,
				Operation.RENT, "Caballito", "Taring 660",
				Integer.valueOf(5020), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(2000),
				Integer.valueOf(5), service, "Descrip1");

		final Property prop5 = new Property(Integer.valueOf(5), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(5005), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip2");

		final Property prop6 = new Property(Integer.valueOf(6), Type.HOUSE,
				Operation.RENT, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		final Property prop7 = new Property(Integer.valueOf(7), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660",
				Integer.valueOf(50040), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip1");

		final Property prop8 = new Property(Integer.valueOf(8), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(5002), Integer.valueOf(3),
				Integer.valueOf(100), Integer.valueOf(200), Integer.valueOf(5),
				service, "Descrip2");

		final Property prop9 = new Property(Integer.valueOf(9), Type.HOUSE,
				Operation.SELL, "Palermo", "Alem 110", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip3");

		propertyList.add(prop1);
		propertyList.add(prop2);
		propertyList.add(prop3);
		propertyList.add(prop4);
		propertyList.add(prop5);
		propertyList.add(prop6);
		propertyList.add(prop7);
		propertyList.add(prop8);
		propertyList.add(prop9);

		properties.addAll(propertyList);

		tmp.setID(1);
		tmp.getProperties().add(properties.get(1));
		tmp.getProperties().add(properties.get(2));
		properties.get(1).setOwner(tmp);
		properties.get(2).setOwner(tmp);

		tmp.getProperties().add(properties.get(0));
		tmp.getProperties().add(properties.get(1));
		properties.get(0).setOwner(tmp);
		properties.get(1).setOwner(tmp);

		users.add(tmp);

		tmp = new User("Thomas", "Mann", "thomas@gmail.com", "3647823",
				"thomas", "abcd");

		tmp.setID(2);
		tmp.getProperties().add(properties.get(2));
		properties.get(2).setOwner(tmp);

		tmp.getProperties().add(properties.get(2));
		properties.get(2).setOwner(tmp);

		users.add(tmp);

		tmp = new User("Baruch", "Spinoza", "spinoza@gmail.com", "74687364",
				"spinoza", "akjasd");

		tmp.setID(3);

		users.add(tmp);

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
