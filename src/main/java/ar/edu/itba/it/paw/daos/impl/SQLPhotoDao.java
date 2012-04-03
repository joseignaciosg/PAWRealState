package ar.edu.itba.it.paw.daos.impl;

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
			ps.setString(1, String.valueOf(id));
			ps.execute();
			final ResultSet cursor = ps.getResultSet();
			System.out.println(cursor);
			cursor.next();
			photo = new Photo(Integer.valueOf(cursor.getString("id")), cursor
					.getString("data").getBytes("UTF-16"),
					cursor.getString("type"));
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
			ps.execute();
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean saveOrUpdate(final Photo obj) {
		try {
			final Connection conn = this.provider.getConnection();
			final PreparedStatement ps;
			if (obj.isNew()) {
				ps = conn
						.prepareStatement("INSERT INTO PHOTOS (data, type, property_id)"
								+ " VALUES (?,?,?)");
				ps.setBytes(1, obj.getData());
				ps.setString(2, obj.getType());
				ps.setInt(3, obj.getPropertyid());
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
				ps.setInt(3, obj.getPropertyid());
				ps.setInt(4, obj.getId());

				ps.execute();
			} else {
				return false;
			}
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}

		obj.setDirty(false);
		return false;
	}

	public List<Integer> getByPropertyId(final Integer id) throws Exception {
		final List<Integer> photos = new ArrayList<Integer>();

		final Connection conn = this.provider.getConnection();
		final String query = "select id from photos where property_id=?";
		final PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, String.valueOf(id));
		ps.execute();
		final ResultSet cursor = ps.getResultSet();
		System.out.println(cursor);
		while (cursor.next()) {
			final Integer ph = Integer.valueOf(cursor.getString("id"));
			photos.add(ph);

		}
		return photos;
	}
}
