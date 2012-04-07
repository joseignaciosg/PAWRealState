package ar.edu.itba.it.paw.daos;

import java.util.ArrayList;

import ar.edu.itba.it.paw.daos.impl.inmem.InMemoryContactRequestDao;
import ar.edu.itba.it.paw.daos.impl.inmem.InMemoryPhotoDao;
import ar.edu.itba.it.paw.daos.impl.inmem.InMemoryPropertyDao;
import ar.edu.itba.it.paw.daos.impl.inmem.InMemoryUserDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;

public class InMemoryDaoProvider extends DaoProvider {

	public InMemoryDaoProvider() {
		this.reload();
	}

	public void reload() {
		this.propertyDao = new InMemoryPropertyDao(new ArrayList<Property>());
		this.contactrequestDao = new InMemoryContactRequestDao(
				new ArrayList<ContactRequest>());
		this.photoDao = new InMemoryPhotoDao(new ArrayList<Photo>(),
				this.propertyDao);

		((InMemoryPropertyDao) this.propertyDao).setPhotoDao(this.photoDao);
		this.userDao = new InMemoryUserDao(new ArrayList<User>());
	}
}
