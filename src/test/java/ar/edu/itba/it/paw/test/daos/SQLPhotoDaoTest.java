package ar.edu.itba.it.paw.test.daos;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.impl.SQLPhotoDao;
import ar.edu.itba.it.paw.daos.impl.SQLPropertyDao;

public class SQLPhotoDaoTest extends PhotoDaoTest {

	private PhotoDao photodao;
	private PropertyDao propdao;

	@Override
	public PhotoDao getPhotoDao() {
		if (this.photodao == null) {
			this.photodao = new SQLPhotoDao(this.getProvider());
		}

		return this.photodao;
	}

	@Override
	public PropertyDao getProperyDao() {
		if (this.propdao == null) {
			this.propdao = new SQLPropertyDao(this.getProvider());
		}

		return this.propdao;
	}

}
