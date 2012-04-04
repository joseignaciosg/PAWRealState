package ar.edu.itba.it.paw.db;

import java.sql.Connection;

import ar.edu.itba.it.paw.db.managers.InMemorySQLiteConnectionManager;
import ar.edu.itba.it.paw.db.managers.PostgreConnectionManager;

/**
 * Provides with a production and a test connection dispatcher
 * 
 * @author cris
 */
public class ConnectionProvider {

	private ConnectionManager manager;

	private ConnectionProvider() {

	}

	/**
	 * Get a connection dispatcher for our production environment
	 * 
	 * @return ConnectionDispatcher for production
	 */
	public static ConnectionProvider getProvider() {
		final ConnectionProvider myDispatcher = new ConnectionProvider();
		myDispatcher.manager = new PostgreConnectionManager();
		return myDispatcher;
	}

	/**
	 * Get a connection dispatcher for our test environment
	 * 
	 * @return ConnectionDispatcher for test
	 */
	public static ConnectionProvider getTestProvider() {
		final ConnectionProvider myDispatcher = new ConnectionProvider();
		myDispatcher.manager = InMemorySQLiteConnectionManager
				.getConnectionManager();

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