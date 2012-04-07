package ar.edu.itba.it.paw.test.daos.base;

import ar.edu.itba.it.paw.daos.DaoProvider;
import ar.edu.itba.it.paw.test.TransactionalTest;
import ar.edu.itba.it.paw.test.daos.helper.DaoTestHelper;

/**
 * Abstract class for testing a Dao
 * 
 * @author cris
 */
public abstract class DaoTest extends TransactionalTest {
	/**
	 * All Dao tests must provide their given provider. Currently there are
	 * InMemory and SQL providers.
	 */
	public abstract DaoProvider getDaoProvider();

	private DaoTestHelper helper;

	protected DaoTestHelper getHelper() {
		if (this.helper == null) {
			this.setHelper(new DaoTestHelper(this.getDaoProvider()));
		}
		return this.helper;
	}

	protected void setHelper(final DaoTestHelper helper) {
		this.helper = helper;
	}

}
