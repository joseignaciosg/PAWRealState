package ar.edu.itba.it.paw.test.daos.helper;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;

/**
 * Class that concentrates all the default data generated for testing the DAOs
 * 
 * <pre>
 * Need a user for testing a property? Get a default user!
 * Need a property for testing a contact request? Get a default property!
 * And so on...
 * </pre>
 * 
 * @author cris
 */
public class DaoTestHelper {

	private PhotoDao photoDao;
	private ContactRequestDao contactRequestDao;
	private PropertyDao propertyDao;
	private UserDao userDao;

	User defaultUser = null;
	Property defaultProperty = null;

	public DaoTestHelper(final DaoProvider provider) {
		this.photoDao = provider.getPhotoDao();
		this.contactRequestDao = provider.getContactRequestDao();
		this.propertyDao = provider.getPropertyDao();
		this.userDao = provider.getUserDao();
	}

	/**
	 * Gets a default test property
	 */
	public Property defaultProperty() {
		if ((this.defaultProperty = this.propertyDao.getById(1)) == null) {
			final Services service = new Services(true, true, true, true,
					false, true);
			this.defaultProperty = new Property(Integer.valueOf(1),
					Type.APARTMENT, Operation.RENT, "Palermo", "Lavalle 660",
					Integer.valueOf(500), Integer.valueOf(3),
					Integer.valueOf(100), Integer.valueOf(200),
					Integer.valueOf(5), service, "Descrip1", this.defaultUser());

			this.propertyDao.saveOrUpdate(this.defaultProperty);
		}

		return this.defaultProperty;
	}

	/**
	 * Gets a default test user
	 */
	public User defaultUser() {
		if ((this.defaultUser = this.userDao.getById(1)) == null) {
			this.defaultUser = new User("Ben", "Stiller", "ben@gmail.com",
					"16748376", "BenSti", "B3nSt1");
			this.defaultUser.setId(1);

			this.userDao.saveOrUpdate(this.defaultUser);
		}

		return this.defaultUser;
	}

}
