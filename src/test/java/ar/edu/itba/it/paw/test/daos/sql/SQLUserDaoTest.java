package ar.edu.itba.it.paw.test.daos.sql;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.test.daos.base.UserDaoTest;

public class SQLUserDaoTest extends UserDaoTest {

	private DaoProvider provider;

	@Override
	public DaoProvider getDaoProvider() {
		if (this.provider == null) {
			this.provider = DaoProvider.getProviderForConnection(this
					.getConnectionProvider());
		}

		return this.provider;
	}
}
