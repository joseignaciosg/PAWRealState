package ar.edu.itba.it.paw.test.daos;

import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.daos.impl.SQLUserDao;

public class SQLUserDaoTest extends UserDaoTest {

	private UserDao dao;

	@Override
	public UserDao getDao() {
		if (this.dao == null) {
			this.dao = new SQLUserDao(this.getProvider());
		}

		return this.dao;
	}
}
