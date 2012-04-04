package ar.edu.itba.it.paw.test.daos;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.impl.SQLContactRequestDao;

public class SQLContactRequestDaoTest extends ContactRequestTest {

	private ContactRequestDao dao;

	@Override
	public ContactRequestDao getDao() {

		if (this.dao == null) {
			this.dao = new SQLContactRequestDao(this.getProvider());
		}

		return this.dao;
	}
}
