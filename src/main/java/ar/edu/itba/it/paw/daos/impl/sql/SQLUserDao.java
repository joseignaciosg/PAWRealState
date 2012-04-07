package ar.edu.itba.it.paw.daos.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.daos.impl.sql.factories.PropertyCollectionFactory;
import ar.edu.itba.it.paw.db.ConnectionProvider;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.utils.collections.CollectionWithMemory;
import ar.edu.itba.it.paw.utils.collections.LazyCollection;

public class SQLUserDao implements UserDao {

	private ConnectionProvider provider;
	private PropertyDao propertyDao;

	public SQLUserDao(final ConnectionProvider provider) {
		this.provider = provider;
	}

	public User getById(final Integer id) {
		User user = null;
		try {
			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM USERS WHERE id = ?");

			statement.setInt(1, id);

			statement.execute();
			final ResultSet result = statement.getResultSet();
			while (result.next() && !result.isAfterLast()) {
				user = this.readUserFromResultSet(result);
			}

		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}

		return user;
	}

	public boolean delete(final User obj) {
		try {

			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("DELETE FROM USERS WHERE id = ?");

			statement.setInt(1, obj.getId());

			final int rows = statement.executeUpdate();

			return rows == 1;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean saveOrUpdate(final User user) {
		try {
			final Connection conn = this.provider.getConnection();
			PreparedStatement statement;
			if (user.isNew()) {

				statement = conn
						.prepareStatement(
								"INSERT INTO USERS("
										+ "firstname, lastname, email, phone, username, password) VALUES (?, ?, ?, "
										+ "?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS);

				this.makeUserStatement(user, statement);

				statement.execute();

				final ResultSet set = statement.getGeneratedKeys();

				set.next();

				try {
					user.setId(set.getInt("id"));
				} catch (final Exception e) {
					user.setId(set.getInt(1));
				}

				user.setNew(false);

			} else if (user.isDirty()) {
				statement = conn.prepareStatement("UPDATE USERS "
						+ "SET firstname = ?, lastname = ?, "
						+ "email = ?, phone = ?, "
						+ "username = ?, password = ?" + "WHERE id = ?");

				this.makeUserStatement(user, statement);
				statement.setInt(7, user.getId());

				statement.execute();

				user.setDirty(false);

			} else {
				return false;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}

		user.setDirty(false);
		return true;

	}

	public List<User> getAll() {
		final List<User> users = new ArrayList<User>();
		try {
			final Connection conn = this.provider.getConnection();

			final StringBuilder query = new StringBuilder("SELECT * FROM USERS");

			final PreparedStatement statement = conn.prepareStatement(query
					.toString());

			statement.execute();

			final ResultSet result = statement.getResultSet();

			while (result.next() && !result.isAfterLast()) {
				final User user = this.readUserFromResultSet(result);
				users.add(user);
			}

			return users;
		} catch (final Exception e) {
			e.printStackTrace();
			return new ArrayList<User>();
		}
	}

	public User getUser(final String username) {
		User user = null;
		try {
			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM USERS WHERE username = ?");

			statement.setString(1, username);

			statement.execute();
			final ResultSet result = statement.getResultSet();
			while (result.next() && !result.isAfterLast()) {
				user = this.readUserFromResultSet(result);
			}

		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}

		return user;
	}

	private User readUserFromResultSet(final ResultSet result)
			throws SQLException {
		User user;
		final Integer userId = result.getInt("id");
		final String firstnameStr = result.getString("firstname");
		final String lastnameStr = result.getString("lastname");
		final String email = result.getString("email");
		final String phone = result.getString("phone");
		final String username = result.getString("username");
		final String password = result.getString("password");
		user = new User(userId, firstnameStr, lastnameStr, email, phone,
				username, password);

		user.setProperties(new CollectionWithMemory<Property>(
				new LazyCollection<Property>(new PropertyCollectionFactory(
						this.propertyDao, user))));
		user.setNew(false);
		return user;
	}

	private void makeUserStatement(final User user,
			final PreparedStatement statement) throws SQLException {
		statement.setString(1, user.getName());
		statement.setString(2, user.getSurname());
		statement.setString(3, user.getMail());
		statement.setString(4, user.getTelephone());
		statement.setString(5, user.getUsername());
		statement.setString(6, user.getPassword());
	}

	public void setPropertyDao(final PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

}
