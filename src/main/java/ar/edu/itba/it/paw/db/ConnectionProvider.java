package ar.edu.itba.it.paw.db;

import java.io.File;
import java.sql.Connection;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.db.managers.InMemorySQLiteConnectionManager;
import ar.edu.itba.it.paw.db.managers.PostgreConnectionManager;

/**
 * Provides with a production and a test connection dispatcher
 * 
 * @author cris
 */
@Component
public class ConnectionProvider {

	private ConnectionManager manager;

	private static String applicationPath;

	public static void setApplicationPath(final String applicationPath) {
		ConnectionProvider.applicationPath = applicationPath;
	}

	public ConnectionProvider() {
		File configFile = new File(applicationPath
				+ "WEB-INF/database.properties");

		if (!configFile.exists()) {
			configFile = new File("WEB-INF/database.properties");
		}

		this.manager = new PostgreConnectionManager(configFile);
	}

	/**
	 * Get a connection dispatcher for our production environment
	 * 
	 * @return ConnectionDispatcher for production
	 */
	public static ConnectionProvider getProvider() {
		final ConnectionProvider myDispatcher = new ConnectionProvider();

		File configFile = new File(applicationPath
				+ "WEB-INF/database.properties");

		if (!configFile.exists()) {
			configFile = new File("WEB-INF/database.properties");
		}

		myDispatcher.manager = new PostgreConnectionManager(configFile);
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
