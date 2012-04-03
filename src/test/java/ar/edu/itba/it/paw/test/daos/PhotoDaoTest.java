package ar.edu.itba.it.paw.test.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.test.TransactionalTest;

public abstract class PhotoDaoTest extends TransactionalTest {

	private PhotoDao photoDao;
	private PropertyDao propDao;

	public abstract PhotoDao getPhotoDao();

	public abstract PropertyDao getProperyDao();

	public PhotoDaoTest() {
		this.photoDao = this.getPhotoDao();
		this.propDao = this.getProperyDao();
	}

	private void preparedaos() throws IOException {

		// uploading the properties
		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip1");
		final Property prop2 = new Property(Integer.valueOf(2), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(500), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2");
		this.propDao.saveOrUpdate(prop1);
		this.propDao.saveOrUpdate(prop2);

		// uploading the photos
		final File file = new File("src/test/photos/prop1.jpg");
		final InputStream is = new FileInputStream(file);
		final StringBuilder builder = new StringBuilder();
		int b = 0;
		while ((b = is.read()) != -1) {
			builder.append((char) b);
		}
		final byte[] byteData1 = builder.toString().getBytes();
		final Photo photo1 = new Photo(1, byteData1, "jpg");
		final Photo photo2 = new Photo(2, byteData1, "jpg");
		final Photo photo3 = new Photo(3, byteData1, "jpg");
		photo1.setPropertyid(1);
		photo2.setPropertyid(1);
		photo3.setPropertyid(2);
		this.photoDao.saveOrUpdate(photo1);
		this.photoDao.saveOrUpdate(photo2);
		this.photoDao.saveOrUpdate(photo3);

	}

	@Test
	public void getByPropertyIdTest() throws Exception {
		this.preparedaos();
		// Assert.assertNull(this.photoDao.getByPropertyId(4));
		List<Integer> list = this.photoDao.getByPropertyId(3);
		Assert.assertTrue(list.size() == 0);
		list = this.photoDao.getByPropertyId(1);
		Assert.assertTrue(list.size() == 2);

		System.out.println(list);

	}

	@Test
	public void deleteTest() throws Exception {
		this.preparedaos();
		final List<Integer> list = this.photoDao.getByPropertyId(1);
		Assert.assertTrue(list.size() == 2);

		// final byte[] byte1 = null;
		//
		// final Photo photoTrue = new Photo(1, byte1, "jpeg");
		// final Photo photoFalse = new Photo(4, byte1, "jpeg");
		//
		// Assert.assertEquals(true, this.data.delete(photoTrue));
		// Assert.assertEquals(false, this.data.delete(photoFalse));

	}

	@Test
	public void saveOrUpdateTest() {
		// final Photo photoTrue = this.data.getById(1);
		// photoTrue.setType("XLS");
		// final Photo photoFalse = this.data.getById(2);
		// Assert.assertEquals(true, this.data.saveOrUpdate(photoTrue));
		// Assert.assertEquals(false, this.data.saveOrUpdate(photoFalse));
		// Assert.assertEquals(false, this.data.saveOrUpdate(photoTrue));

	}

}
