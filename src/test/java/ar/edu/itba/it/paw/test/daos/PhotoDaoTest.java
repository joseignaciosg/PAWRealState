package ar.edu.itba.it.paw.test.daos;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.itba.it.paw.daos.impl.InMemoryPhotoDao;
import ar.edu.itba.it.paw.model.entities.Photo;

public class PhotoDaoTest {

	InMemoryPhotoDao data;

	@Before
	public void prepareData() {

		final List<Photo> photoList = new ArrayList<Photo>();

		final byte[] byte1 = null;
		final byte[] byte2 = null;
		final byte[] byte3 = null;

		final Photo photo1 = new Photo(1, byte1, "jpeg");
		final Photo photo2 = new Photo(2, byte2, "jpeg");
		final Photo photo3 = new Photo(3, byte3, "png");

		photoList.add(photo1);
		photoList.add(photo2);
		photoList.add(photo3);

		this.data = new InMemoryPhotoDao(photoList);

	}

	@Test
	public void getByIdTest() {
		Assert.assertNull(this.data.getById(4));
		Assert.assertNotNull(this.data.getById(1));
	}

	@Test
	public void deleteTest() {

		final byte[] byte1 = null;

		final Photo photoTrue = new Photo(1, byte1, "jpeg");
		final Photo photoFalse = new Photo(4, byte1, "jpeg");

		Assert.assertEquals(true, this.data.delete(photoTrue));
		Assert.assertEquals(false, this.data.delete(photoFalse));

	}

	@Test
	public void saveOrUpdateTest() {
		final Photo photoTrue = this.data.getById(1);
		photoTrue.setType("XLS");
		final Photo photoFalse = this.data.getById(2);
		Assert.assertEquals(true, this.data.saveOrUpdate(photoTrue));
		Assert.assertEquals(false, this.data.saveOrUpdate(photoFalse));
		Assert.assertEquals(false, this.data.saveOrUpdate(photoTrue));

	}

	@Test
	public void getAllTest() {
		Assert.assertEquals(3, this.data.getAll().size());
	}
}
