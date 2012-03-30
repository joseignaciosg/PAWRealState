package ar.edu.itba.it.paw.test;

import java.sql.SQLException;

import org.junit.After;

import ar.edu.itba.it.paw.db.ConnectionFactory;

/**
 * Kind of test that rollbacks the connection to the base state after each test
 */
public class TransactionalTest {

	private ConnectionFactory dispatcher;

	public TransactionalTest() {
		this.dispatcher = ConnectionFactory.getTestDispatcher();
	}

	/**
	 * Must be used as a dispatcher for the connections of each test
	 */
	public ConnectionFactory getDispatcher() {
		return this.dispatcher;
	}

	@After
	public void rollback() {
		try {
			this.dispatcher.getConnection().rollback();
		} catch (final SQLException e) {
		} catch (final Exception e) {
		}
	}
}
