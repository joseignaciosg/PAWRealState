package ar.edu.itba.it.paw.daos.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.daos.impl.sql.factories.PhotoCollectionFactory;
import ar.edu.itba.it.paw.daos.impl.sql.factories.UserFactory;
import ar.edu.itba.it.paw.db.ConnectionProvider;
import ar.edu.itba.it.paw.model.entities.LazyUser;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.utils.collections.CollectionWithMemory;
import ar.edu.itba.it.paw.utils.collections.LazyCollection;

public class SQLPropertyDao implements PropertyDao {

	private ConnectionProvider provider;
	private UserDao userDao;
	private PhotoDao photoDao;

	public SQLPropertyDao(final ConnectionProvider provider,
			final UserDao userDao, final PhotoDao photoDao) {
		this.provider = provider;
		this.userDao = userDao;
		this.photoDao = photoDao;
	}

	public Property getById(final Integer id) {
		Property property = null;
		try {
			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM PROPERTIES WHERE id = ?");

			statement.setInt(1, id);

			statement.execute();
			final ResultSet result = statement.getResultSet();
			while (result.next() && !result.isAfterLast()) {
				property = this.readPropertyFromResultSet(result);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return property;
	}

	public List<Property> getByUserId(final int id) {
		final List<Property> properties = new ArrayList<Property>();
		try {

			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM PROPERTIES WHERE user_id = ?");

			statement.setInt(1, id);

			statement.execute();

			final ResultSet result = statement.getResultSet();

			while (result.next() && !result.isAfterLast()) {
				final Property property = this
						.readPropertyFromResultSet(result);

				properties.add(property);
			}

			return properties;
		} catch (final Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}

	}

	public boolean delete(final Property property) {
		try {

			for (final Photo photo : property.getPhotos()) {
				this.photoDao.delete(photo);
			}

			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("DELETE FROM PROPERTIES WHERE id = ?");

			statement.setInt(1, property.getId());

			final int rows = statement.executeUpdate();

			return rows == 1;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveOrUpdate(final Property property) {
		try {
			final Connection conn = this.provider.getConnection();
			PreparedStatement statement;

			boolean doneSomething = false;

			if (property.isNew()) {

				statement = conn
						.prepareStatement(
								"INSERT INTO PROPERTIES(\"type\", \"transaction\", "
										+ "address, neighborhood, price, rooms, csqm, usqm, age, "
										+ "has_cable, has_phone, has_swimmingpool, has_salon, "
										+ "has_paddle, has_quincho, description, visible, user_id) VALUES (?, ?, ?, "
										+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS);

				this.makePropertyStatement(property, statement);

				statement.execute();

				final ResultSet set = statement.getGeneratedKeys();

				set.next();

				try {
					property.setId(set.getInt("id"));
				} catch (final Exception e) {
					property.setId(set.getInt(1));
				}
				doneSomething = true;
			} else if (property.isDirty()) {
				statement = conn
						.prepareStatement("UPDATE PROPERTIES "
								+ "SET \"type\" = ?, \"transaction\" = ?, "
								+ "address = ?, neighborhood = ?, "
								+ "price = ?, rooms = ?, "
								+ "csqm = ?, usqm = ?, "
								+ "age = ?, has_cable = ?, "
								+ "has_phone = ?, has_swimmingpool = ?, "
								+ "has_salon = ?, has_paddle = ?, "
								+ "has_quincho = ?, description = ?, visible = ?, user_id = ? "
								+ "WHERE id = ?");

				this.makePropertyStatement(property, statement);

				statement.setInt(19, property.getId());

				statement.execute();

				doneSomething = true;
			}

			final CollectionWithMemory<Photo> photosWithDeletions = (CollectionWithMemory<Photo>) property
					.getPhotos();

			if (photosWithDeletions.isModified()) {
				for (final Photo photo : photosWithDeletions) {
					this.photoDao.saveOrUpdate(photo);
				}

				for (final Photo photo : photosWithDeletions.getDeletedItems()) {
					this.photoDao.delete(photo);
				}
				photosWithDeletions.getDeletedItems().clear();
				photosWithDeletions.setModified(false);
				doneSomething = true;
			}

			if (!doneSomething) {
				return false;
			}

		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}

		property.setDirty(false);
		return true;
	}

	public List<Property> getAll() {
		final List<Property> properties = new ArrayList<Property>();
		try {

			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM PROPERTIES");

			statement.execute();

			final ResultSet result = statement.getResultSet();

			while (result.next() && !result.isAfterLast()) {
				final Property property = this
						.readPropertyFromResultSet(result);

				properties.add(property);
			}

			return properties;
		} catch (final Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	public List<Property> getAll(final Operation op, final Type type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, final Order order, final Boolean visible) {
		final List<Property> properties = new ArrayList<Property>();

		try {
			final boolean searching = (pricehigh != -1 || pricelow != -1
					|| type != null || op != null || visible != null);
			final Connection conn = this.provider.getConnection();

			final StringBuilder query = new StringBuilder(
					"SELECT * FROM PROPERTIES");

			if (searching) {
				query.append(" WHERE ");
			}

			final List<String> commands = new ArrayList<String>();

			if (pricehigh != -1) {
				commands.add(" price <= ? ");
			}

			if (pricelow != -1) {
				commands.add(" price >= ? ");
			}

			if (op != null) {
				commands.add(" \"transaction\" LIKE ? ");
			}

			if (type != null) {
				commands.add(" \"type\" LIKE ? ");
			}

			if (visible != null) {
				commands.add(" \"visible\" = ? ");
			}

			query.append(StringUtils.join(commands, " AND "));

			if (order != null) {
				switch (order) {
				case DESC:
					query.append(" ORDER BY price DESC ");
					break;
				case ASC:
					query.append(" ORDER BY price ASC ");
					break;
				}
			}

			if (quant != -1) {
				query.append(" LIMIT ").append(quant).append(" ");
			}

			if (page != -1 && quant != -1) {
				query.append(" OFFSET ").append(quant * page).append(" ");
			}

			final PreparedStatement statement = conn.prepareStatement(query
					.toString());

			int paramIndex = 1;

			if (pricehigh != -1) {
				statement.setInt(paramIndex++, pricehigh);
			}

			if (pricelow != -1) {
				statement.setInt(paramIndex++, pricelow);
			}

			if (op != null) {
				statement.setString(paramIndex++, op.toString());
			}

			if (type != null) {
				statement.setString(paramIndex++, type.toString());
			}

			if (visible != null) {
				statement.setBoolean(paramIndex++, visible.booleanValue());
			}

			statement.execute();

			final ResultSet result = statement.getResultSet();

			while (result.next() && !result.isAfterLast()) {
				final Property property = this
						.readPropertyFromResultSet(result);
				properties.add(property);
			}

			return properties;
		} catch (final Exception e) {
			e.printStackTrace();
			return new ArrayList<Property>();
		}
	}

	private Property readPropertyFromResultSet(final ResultSet result)
			throws SQLException {
		Property property;
		final Integer propertyId = result.getInt("id");
		final String typeStr = result.getString("type");
		final String transactionStr = result.getString("transaction");
		final String address = result.getString("address");
		final String neighborhood = result.getString("neighborhood");
		final Integer price = result.getInt("price");
		final Integer rooms = result.getInt("rooms");
		final Integer coveredArea = result.getInt("csqm");
		final Integer uncoveredArea = result.getInt("usqm");
		final Integer age = result.getInt("age");
		final String description = result.getString("description");
		final Integer userId = result.getInt("user_id");
		final Boolean visible = result.getBoolean("visible");

		final Services service = new Services();

		service.setCable(result.getBoolean("has_cable"));
		service.setTelephone(result.getBoolean("has_phone"));
		service.setSwimmingpool(result.getBoolean("has_swimmingpool"));
		service.setLobby(result.getBoolean("has_salon"));
		service.setPaddle(result.getBoolean("has_paddle"));
		service.setQuincho(result.getBoolean("has_quincho"));

		property = new Property(propertyId, Type.fromString(typeStr),
				Operation.fromString(transactionStr), neighborhood, address,
				price, rooms, coveredArea, uncoveredArea, age, service,
				description, new LazyUser(
						new UserFactory(this.userDao, userId), userId));

		property.setVisible(visible);

		property.setPhotos(new CollectionWithMemory<Photo>(
				new LazyCollection<Photo>(new PhotoCollectionFactory(
						this.photoDao, property))));

		property.setNew(false);
		property.setDirty(false);
		return property;
	}

	private void makePropertyStatement(final Property property,
			final PreparedStatement statement) throws SQLException {
		statement.setString(1, property.getPropertyType());
		statement.setString(2, property.getOperationType());
		statement.setString(3, property.getAddress());
		statement.setString(4, property.getNeighborhood());
		statement.setInt(5, property.getPrice());
		statement.setInt(6, property.getSpaces());
		statement.setInt(7, property.getCoveredArea());
		statement.setInt(8, property.getFreeArea());
		statement.setInt(9, property.getAge());
		statement.setBoolean(10, property.getService().isCable());
		statement.setBoolean(11, property.getService().isTelephone());
		statement.setBoolean(12, property.getService().isSwimmingpool());
		statement.setBoolean(13, property.getService().isLobby());
		statement.setBoolean(14, property.getService().isPaddle());
		statement.setBoolean(15, property.getService().isQuincho());
		statement.setString(16, property.getDescription());
		statement.setBoolean(17, property.getVisible());
		statement.setInt(18, property.getOwner().getId());
	}

}
