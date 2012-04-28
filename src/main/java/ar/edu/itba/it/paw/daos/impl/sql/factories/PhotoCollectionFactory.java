package ar.edu.itba.it.paw.daos.impl.sql.factories;

import java.util.Collection;
import java.util.Collections;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.utils.Factory;

/**
 * Generates all the photos of a property via it's dao and the given property.
 * Used for lazy collection implementations
 * 
 * @author cris
 */
public class PhotoCollectionFactory implements Factory<Collection<Photo>> {

	private PhotoDao photoDao;
	private Property property;

	public PhotoCollectionFactory(final PhotoDao photoDao,
			final Property property) {
		this.photoDao = photoDao;
		this.property = property;
	}

	public Collection<Photo> create() {
		try {
			return this.photoDao.getByPropertyId(this.property.getId());
		} catch (final Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
