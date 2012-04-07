package ar.edu.itba.it.paw.test.daos.sql;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.test.daos.base.ContactRequestTest;

public class SQLContactRequestDaoTest extends ContactRequestTest {

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
