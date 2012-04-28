package ar.edu.itba.it.paw.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides a JDBC connection to a database
 * 
 * @author cris
 */
public interface ConnectionManager {

	Connection getConnection() throws SQLException;
}
