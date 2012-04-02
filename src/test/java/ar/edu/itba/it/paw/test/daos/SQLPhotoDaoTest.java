package ar.edu.itba.it.paw.test.daos;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.SQLPhotoDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.test.TransactionalTest;

public class SQLPhotoDaoTest extends TransactionalTest {

	SQLPhotoDao dao;

	@Before
	public void prepareData() throws Exception {
		final Connection conn = this.getDispatcher().getConnection();

		// inserting the property
		final String query = "insert into property  "
				+ "(type, transaction, address,neighborhood,"
				+ "price, rooms, csqm, usqm, age, has_cable, "
				+ "has_phone, has_swimmingpool, has_salon,"
				+ "has_paddle, has_quincho, description ) VALUES "
				+ "('HOUSE', 'SELL', 'Marcelo T. 1270', "
				+ "'Recoleta', 100000, 3, 200, 300, 15, 'true', "
				+ "'true', 'true', 'true', 'true', 'true', 'true');";
		final PreparedStatement st = conn.prepareStatement(query);
		st.execute();

		// inserting the photo
		final File file = new File("src/test/photos/prop1.jpg");
		final FileInputStream fis = new FileInputStream(file);
		final PreparedStatement ps = conn
				.prepareStatement("INSERT INTO photos (data, type, propertyid)"
						+ " VALUES (?,?,1)");
		ps.setBinaryStream(1, fis, file.length());
		ps.setString(2, file.getName());
		ps.executeUpdate();
		ps.close();
		fis.close();

	}

	@Test
	public void getByPropertyIdTest() {

		// gets all the photos of property with id 1
		List<Photo> photos = null;
		try {
			photos = this.dao.getByPropertyId(1);
			Assert.assertTrue(photos.size() == 1);
			System.out.println(photos);
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

}
