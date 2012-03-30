package ar.edu.itba.it.paw.db.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.itba.it.paw.db.ConnectionManager;

/**
 * Connection Manager used for tests. Only returns a connection built on memory
 * which can be rollbacked
 * 
 * @author cris
 */
public class InMemorySQLiteConnectionManager implements ConnectionManager {

	private Connection conn = null;

	private static ConnectionManager instance = null;

	public static ConnectionManager getConnectionManager() {
		if (instance == null) {
			instance = new InMemorySQLiteConnectionManager();
		}
		return instance;
	}

	private InMemorySQLiteConnectionManager() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (final ClassNotFoundException e) {

		}
	}

	/**
	 * Returns the memory connection, loaded with the file 0.tables.sql in the
	 * test/resources folder
	 */
	public Connection getConnection() throws SQLException {
		if (this.conn == null) {
			this.conn = DriverManager.getConnection("jdbc:sqlite::memory:");

			this.conn.setAutoCommit(false);
			try {
				final File f = new File("src/test/resources/0.tables.sql");
				final BufferedReader reader = new BufferedReader(
						new FileReader(f));
				StringBuilder command = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					command.append(line).append("\n");
					if (line.contains(";")) {
						this.conn.createStatement().execute(command.toString());
						command = new StringBuilder();
					}
				}
				if (command.toString().endsWith(";")) {
					this.conn.createStatement().execute(command.toString());
				}

				this.conn.commit();
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			} catch (final IOException e) {
				e.printStackTrace();
			} catch (final SQLException e) {

				e.printStackTrace();
			}

		}

		return this.conn;
	}
}
