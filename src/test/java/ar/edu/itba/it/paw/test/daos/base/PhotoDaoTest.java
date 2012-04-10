package ar.edu.itba.it.paw.test.daos.base;

import java.io.ByteArrayOutputStream;
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

public abstract class PhotoDaoTest extends DaoTest {

	private PhotoDao photoDao;
	private PropertyDao propDao;
	private Photo photo1;
	private Photo photo2;
	private Photo photo3;
	private byte[] byteData1;

	public PhotoDaoTest() {
		this.photoDao = this.getDaoProvider().getPhotoDao();
		this.propDao = this.getDaoProvider().getPropertyDao();
	}

	private void preparedaos() throws IOException {

		final Services service = new Services(true, true, true, true, false,
				true);
		final Property prop1 = new Property(Integer.valueOf(1), Type.APARTMENT,
				Operation.RENT, "Palermo", "Lavalle 660", Integer.valueOf(500),
				Integer.valueOf(3), Integer.valueOf(100), Integer.valueOf(200),
				Integer.valueOf(5), service, "Descrip1",
				this.getHelper().defaultUser());
		final Property prop2 = new Property(Integer.valueOf(2), Type.HOUSE,
				Operation.SELL, "BarrioNorte", "Junca 460",
				Integer.valueOf(500), Integer.valueOf(3), Integer.valueOf(100),
				Integer.valueOf(200), Integer.valueOf(5), service, "Descrip2",
				this.getHelper().defaultUser());
		this.propDao.saveOrUpdate(prop1);
		this.propDao.saveOrUpdate(prop2);

		this.byteData1 = null;
		final File file1 = new File("src/test/photos/prop1.jpg");
		final FileInputStream fis = new FileInputStream(file1);
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		this.byteData1 = bos.toByteArray();

		this.photo1 = new Photo(1, this.byteData1, "jpg", 1);
		this.photo2 = new Photo(2, this.byteData1, "jpg", 1);
		this.photo3 = new Photo(3, this.byteData1, "jpg", 2);
		this.photoDao.saveOrUpdate(this.photo1);
		this.photoDao.saveOrUpdate(this.photo2);
		this.photoDao.saveOrUpdate(this.photo3);

	}

	@Test
	public void getByPropertyIdTest() throws Exception {
		this.preparedaos();
		List<Photo> list = this.photoDao.getByPropertyId(3);
		Assert.assertTrue(list.size() == 0);
		list = this.photoDao.getByPropertyId(1);
		Assert.assertTrue(list.size() == 2);

	}

	@Test
	public void deleteTest() throws Exception {
		this.preparedaos();
		List<Photo> list = this.photoDao.getByPropertyId(1);
		Assert.assertTrue(list.size() == 2);

		final File file = new File("src/test/photos/prop1.jpg");
		final InputStream is = new FileInputStream(file);
		final StringBuilder builder = new StringBuilder();
		int b = 0;
		while ((b = is.read()) != -1) {
			builder.append((char) b);
		}
		final byte[] byteData1 = builder.toString().getBytes();

		final Photo photoTrue = new Photo(1, byteData1, "jpg", 1);
		final Photo photoFalse = new Photo(4, byteData1, "jpg", 1);
		Assert.assertEquals(true, this.photoDao.delete(photoTrue));
		Assert.assertEquals(false, this.photoDao.delete(photoFalse));

		list = this.photoDao.getByPropertyId(1);
		Assert.assertTrue(list.size() == 1);

	}

	@Test
	public void saveOrUpdateTest() throws IOException {
		this.preparedaos();

		this.photo1 = new Photo(1, this.byteData1, "jpg", 1);
		this.photo2 = new Photo(2, this.byteData1, "jpg", 1);
		this.photo3 = new Photo(3, this.byteData1, "jpg", 2);

		this.photo1.setNew(false);
		this.photo2.setNew(false);
		this.photo3.setNew(false);

		Assert.assertEquals(false, this.photoDao.saveOrUpdate(this.photo1));
		Assert.assertEquals(false, this.photoDao.saveOrUpdate(this.photo2));
		Assert.assertEquals(false, this.photoDao.saveOrUpdate(this.photo3));

		this.photo3.setPropertyId(1);
		Assert.assertEquals(true, this.photoDao.saveOrUpdate(this.photo3));

	}

	@Test
	public void getByIdTest() throws IOException {
		Photo photo1 = this.photoDao.getById(1);
		Assert.assertNull(photo1);
		this.preparedaos();
		photo1 = this.photoDao.getById(1);
		Assert.assertTrue(photo1.getId() == 1);

	}
}