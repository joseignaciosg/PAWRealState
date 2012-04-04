package ar.edu.itba.it.paw.test.daos;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.impl.SQLPropertyDao;

public class SQLPropertyDaoTest extends PropertyDaoTest {

	private PropertyDao dao;

	@Override
	public PropertyDao getDao() {
		if (this.dao == null) {
			this.dao = new SQLPropertyDao(this.getProvider());
		}

		return this.dao;
	}
}
