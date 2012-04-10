package ar.edu.itba.it.paw.test.daos.sql;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.test.daos.base.ContactRequestDaoTest;

public class SQLContactRequestDaoTest extends ContactRequestDaoTest {

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
