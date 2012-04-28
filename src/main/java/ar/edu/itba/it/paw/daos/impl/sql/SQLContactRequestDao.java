package ar.edu.itba.it.paw.daos.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.db.ConnectionProvider;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Property;

@Repository
public class SQLContactRequestDao implements ContactRequestDao {

	private ConnectionProvider provider;
	private PropertyDao propertyDao;

	public SQLContactRequestDao() {
		super();
	}

	public SQLContactRequestDao(final ConnectionProvider provider,
			final PropertyDao propertyDao) {
		this.provider = provider;
		this.propertyDao = propertyDao;
	}

	public ContactRequest getById(final Integer id) {

		ContactRequest contact = null;

		try {
			final Connection con = this.provider.getConnection();
			final PreparedStatement statement = con
					.prepareStatement("SELECT * from contact_requests WHERE id = ?");

			statement.setInt(1, id);

			statement.execute();

			final ResultSet result = statement.getResultSet();
			while (result.next() && !result.isAfterLast()) {
				contact = this.readContactFromResultSet(result);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return contact;
	}

	public boolean delete(final ContactRequest obj) {
		try {

			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("DELETE FROM contact_requests WHERE id = ?");

			statement.setInt(1, obj.getId());

			final int rows = statement.executeUpdate();

			return rows == 1;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveOrUpdate(final ContactRequest obj) {

		try {
			final Connection conn = this.provider.getConnection();
			final PreparedStatement ps;
			if (obj.isNew()) {
				ps = conn.prepareStatement(
						"INSERT INTO contact_requests (username, email,phone,comment, prop_id)"
								+ " VALUES (?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, obj.getName());
				ps.setString(2, obj.getEmail());
				ps.setString(3, obj.getTelephone());
				ps.setString(4, obj.getDescription());
				ps.setInt(5, obj.getPropRefered().getId());

				ps.execute();

				final ResultSet set = ps.getGeneratedKeys();

				set.next();

				try {
					obj.setId(set.getInt("id"));
				} catch (final Exception e) {
					obj.setId(set.getInt(1));
				}

			} else if (obj.isDirty()) {
				ps = conn
						.prepareStatement("UPDATE contact_requests SET username = ?, email = ?, phone = ?, comment = ? ,prop_id = ? WHERE id = ?");
				ps.setString(1, obj.getName());
				ps.setString(2, obj.getEmail());
				ps.setString(3, obj.getTelephone());
				ps.setString(4, obj.getDescription());
				ps.setInt(5, obj.getPropRefered().getId());
				ps.setInt(6, obj.getId());

				ps.execute();
			} else {
				return false;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
		obj.setNew(false);
		obj.setDirty(false);
		return true;
	}

	public List<ContactRequest> getAll() {
		final List<ContactRequest> contact = new ArrayList<ContactRequest>();

		try {

			final Connection conn = this.provider.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM contact_requests");

			statement.execute();

			final ResultSet result = statement.getResultSet();

			while (result.next() && !result.isAfterLast()) {
				final ContactRequest cont = this
						.readContactFromResultSet(result);
				contact.add(cont);
			}

			return contact;
		} catch (final Exception e) {
			e.printStackTrace();
			return new ArrayList<ContactRequest>();
		}

	}

	private ContactRequest readContactFromResultSet(final ResultSet result)
			throws SQLException {

		ContactRequest contact;

		final Integer ID = result.getInt("id");
		final Integer propID = result.getInt("prop_id");
		final String name = result.getString("username");
		final String email = result.getString("email");
		final String telephone = result.getString("phone");
		final String description = result.getString("comment");
		final Property prop = this.propertyDao.getById(propID);

		contact = new ContactRequest(name, email, telephone, description, prop);

		contact.setId(ID);

		contact.setNew(false);

		return contact;
	}
}
