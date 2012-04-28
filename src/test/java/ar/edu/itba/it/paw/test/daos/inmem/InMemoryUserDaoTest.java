package ar.edu.itba.it.paw.test.daos.inmem;

import org.junit.After;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.daos.InMemoryDaoProvider;
import ar.edu.itba.it.paw.test.daos.base.UserDaoTest;

public class InMemoryUserDaoTest extends UserDaoTest {
	private DaoProvider provider;

	@Override
	public DaoProvider getDaoProvider() {
		if (this.provider == null) {
			this.provider = DaoProvider.getInMemoryProvider();
		}

		return this.provider;
	}

	/**
	 * Fix that
	 */
	@After
	public void afterTest() {
		((InMemoryDaoProvider) this.getDaoProvider()).reload();
	}
}
