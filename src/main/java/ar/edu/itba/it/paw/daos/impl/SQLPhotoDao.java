package ar.edu.itba.it.paw.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.db.ConnectionFactory;
import ar.edu.itba.it.paw.model.entities.Photo;

public class SQLPhotoDao implements PhotoDao {

	public Photo getById(final Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean delete(final Photo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean saveOrUpdate(final Photo obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Photo> getByPropertyId(final Integer id) throws Exception {
		final List<Photo> photos = new ArrayList<Photo>();

		final ConnectionFactory factory = ConnectionFactory.getDispatcher();
		final Connection conn = factory.getConnection();
		final String query = "select * from photos where property id=?";
		final PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, String.valueOf(id));
		ps.execute();
		final ResultSet cursor = ps.getResultSet();
		System.out.println(cursor);
		while (cursor.next()) {
			final Photo ph = new Photo(Integer.valueOf(cursor.getString("id")),
					cursor.getString("data").getBytes("UTF-16"),
					cursor.getString("type"));
			System.out.println(ph);
			photos.add(ph);

		}
		return photos;
	}

}
