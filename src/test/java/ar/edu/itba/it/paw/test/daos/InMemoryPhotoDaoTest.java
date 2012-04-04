package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryPhotoDao;
import ar.edu.itba.it.paw.daos.impl.InMemoryPropertyDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;

public class InMemoryPhotoDaoTest extends PhotoDaoTest {

	private PhotoDao photodao;
	private PropertyDao propdao;

	@Override
	public PhotoDao getPhotoDao() {
		if (this.photodao == null) {
			this.photodao = new InMemoryPhotoDao(new ArrayList<Photo>());
		}

		return this.photodao;
	}

	@Override
	public PropertyDao getProperyDao() {
		if (this.propdao == null) {
			this.propdao = new InMemoryPropertyDao(new ArrayList<Property>());
		}

		return this.propdao;
	}
}
