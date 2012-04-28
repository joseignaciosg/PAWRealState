package ar.edu.itba.it.paw.daos;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.db.ConnectionProvider;

@Component
public class DaoProvider {

	private static DaoProvider defaultProvider;

	protected PropertyDao propertyDao;
	protected ContactRequestDao contactrequestDao;
	protected PhotoDao photoDao;
	protected UserDao userDao;

	protected DaoProvider() {
	}

	public static DaoProvider getDefaultProvider() {
		if (defaultProvider == null) {
			defaultProvider = new SQLDaoProvider();
		}

		return defaultProvider;
	}

	public static DaoProvider getProviderForConnection(
			final ConnectionProvider provider) {

		return new SQLDaoProvider(provider);
	}

	public UserDao getUserDao() {
		return this.userDao;
	}

	public ContactRequestDao getContactRequestDao() {
		return this.contactrequestDao;
	}

	public PropertyDao getPropertyDao() {
		return this.propertyDao;
	}

	public PhotoDao getPhotoDao() {
		return this.photoDao;
	}

	public static UserDao getDefaultUserDao() {
		return getDefaultProvider().userDao;
	}

	public static ContactRequestDao getDefaultContactRequestDao() {
		return getDefaultProvider().contactrequestDao;
	}

	public static PropertyDao getDefaultPropertyDao() {
		return getDefaultProvider().propertyDao;
	}

	public static PhotoDao getDefaultPhotoDao() {
		return getDefaultProvider().photoDao;
	}
}
