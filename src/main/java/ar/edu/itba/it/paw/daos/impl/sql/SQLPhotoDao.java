package ar.edu.itba.it.paw.daos.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.db.ConnectionProvider;
import ar.edu.itba.it.paw.model.entities.Photo;

public class SQLPhotoDao implements PhotoDao {

	private ConnectionProvider provider;

	public SQLPhotoDao(final ConnectionProvider provider) {
		this.provider = provider;
	}

	public Photo getById(final Integer id) {
		Photo photo = null;

		Connection conn;
		try {
			conn = this.provider.getConnection();
			final String query = "SELECT * FROM PHOTOS WHERE id=?";
			final PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();

			final ResultSet cursor = ps.getResultSet();
			while (cursor.next()) {
				photo = new Photo(Integer.valueOf(cursor.getString("id")),
						cursor.getString("data").getBytes("UTF-16"),
						cursor.getString("type"), Integer.valueOf(cursor
								.getString("property_id")));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return photo;
	}

	public boolean delete(final Photo obj) {
		Connection conn;
		try {
			conn = this.provider.getConnection();
			final String query = "DELETE FROM PHOTOS WHERE id=?";
			final PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, String.valueOf(obj.getId()));
			final int rows = ps.executeUpdate();
			return rows == 1;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveOrUpdate(final Photo obj) {
		try {
			final Connection conn = this.provider.getConnection();
			final PreparedStatement ps;
			if (obj.isNew()) {
				ps = conn.prepareStatement(
						"INSERT INTO PHOTOS (data, type, property_id)"
								+ " VALUES (?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setBytes(1, obj.getData());
				ps.setString(2, obj.getType());
				ps.setInt(3, obj.getPropertyId());
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
						.prepareStatement("UPDATE PHOTOS SET data = ?, type = ?, property_id = ? WHERE id = ?");
				ps.setBytes(1, obj.getData());
				ps.setString(2, obj.getType());
				ps.setInt(3, obj.getPropertyId());
				ps.setInt(4, obj.getId());

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

	public List<Photo> getByPropertyId(final Integer id) {
		final List<Photo> photos = new ArrayList<Photo>();

		try {
			final Connection conn = this.provider.getConnection();
			final String query = "SELECT * FROM PHOTOS WHERE property_id=?";
			final PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, String.valueOf(id));
			ps.execute();
			final ResultSet cursor = ps.getResultSet();

			while (cursor.next()) {
				final Photo photo = new Photo(Integer.valueOf(cursor
						.getString("id")), null, null, Integer.valueOf(cursor
						.getString("property_id")));
				photos.add(photo);

			}
			return photos;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
