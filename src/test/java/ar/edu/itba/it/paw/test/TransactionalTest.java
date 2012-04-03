package ar.edu.itba.it.paw.test;

import org.junit.After;

import ar.edu.itba.it.paw.db.ConnectionProvider;

/**
 * Kind of test that rollbacks the connection to the base state after each test
 */
public class TransactionalTest {

	private ConnectionProvider provider;

	public TransactionalTest() {
		this.provider = ConnectionProvider.getTestProvider();
	}

	/**
	 * Must be used as a dispatcher for the connections of each test
	 */
	public ConnectionProvider getProvider() {
		return this.provider;
	}

	@After
	public void rollback() {
		try {
			this.provider.getConnection().close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
