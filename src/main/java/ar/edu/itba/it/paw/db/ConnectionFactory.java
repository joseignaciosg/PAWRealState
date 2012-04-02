package ar.edu.itba.it.paw.db;

import java.sql.Connection;

import ar.edu.itba.it.paw.db.managers.PostgreConnectionManager;

/**
 * Provides with a production and a test connection dispatcher
 * 
 * @author cris
 */
public class ConnectionFactory {

	private ConnectionManager manager;

	private ConnectionFactory() {

	}

	/**
	 * Get a connection dispatcher for our production environment
	 * 
	 * @return ConnectionDispatcher for production
	 */
	public static ConnectionFactory getDispatcher() {
		final ConnectionFactory myDispatcher = new ConnectionFactory();
		myDispatcher.manager = new PostgreConnectionManager();
		return myDispatcher;
	}

	/**
	 * Get a connection dispatcher for our test environment
	 * 
	 * @return ConnectionDispatcher for test
	 */
	public static ConnectionFactory getTestDispatcher() {
		final ConnectionFactory myDispatcher = new ConnectionFactory();
		myDispatcher.manager = PostgreConnectionManager.getConnectionManager();

		return myDispatcher;
	}

	/**
	 * Generic getConnection
	 * 
	 * @return The actual connection provided by a ConnectionManager
	 */
	public Connection getConnection() throws Exception {

		return this.manager.getConnection();
	}
}
