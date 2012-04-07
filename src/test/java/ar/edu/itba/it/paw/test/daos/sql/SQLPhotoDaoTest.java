package ar.edu.itba.it.paw.test.daos.sql;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.test.daos.base.PhotoDaoTest;

public class SQLPhotoDaoTest extends PhotoDaoTest {

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
