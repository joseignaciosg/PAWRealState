package ar.edu.itba.it.paw.db.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import ar.edu.itba.it.paw.db.ConnectionManager;

/**
 * Provides a JDBC connection to a PgSQL server.
 * 
 * @author cris
 */
public class PostgreConnectionManager implements ConnectionManager {

	private String connectionString;

	private static ConnectionManager instance = null;

	private Connection conn;

	private Driver driver = new org.postgresql.Driver();

	private String username;
	private String password;

	public PostgreConnectionManager(final File propertiesFile) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Heroku support
		if (System.getenv("DATABASE_URL") != null) {
			URI dbUri;
			try {
				dbUri = new URI(System.getenv("DATABASE_URL"));
				final String username = dbUri.getUserInfo().split(":")[0];
				final String password = dbUri.getUserInfo().split(":")[1];
				final String dbUrl = "jdbc:postgresql://" + dbUri.getHost()
						+ dbUri.getPath();
				this.connectionString = dbUrl;

				this.username = username;
				this.password = password;
			} catch (final URISyntaxException e) {
				e.printStackTrace();
			}

		}

		boolean error = !propertiesFile.exists();

		final Properties props = new Properties();
		try {
			props.load(new FileReader(propertiesFile));
			this.username = props.getProperty("db.username");
			this.password = props.getProperty("db.password");
			this.connectionString = props.getProperty("db.connectionString");
		} catch (final FileNotFoundException e) {
			error = true;
		} catch (final IOException e) {
			error = true;
		}

		if (error) {
			System.err.println("Error loading the db from file");
			this.username = "postgres";
			this.password = "postgres";
			this.connectionString = "jdbc:postgresql://localhost:5432/Chinuprop";
		}
	}

	public Connection getConnection() throws SQLException {
		if (this.conn == null) {
			final Properties props = new Properties();
			props.setProperty("user", this.username);
			props.setProperty("password", this.password);
			this.conn = this.driver.connect(this.connectionString, props);
		}

		return this.conn;

	}
}
