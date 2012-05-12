package ar.edu.itba.it.paw.domain;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.BaseTest;
import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Room;
import ar.edu.itba.it.paw.domain.entities.Room.RoomType;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.NoSuchEntityException;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

/**
 * Checks the consistency of the ORM mappings
 * 
 * @author cris
 * 
 */

public class BasicDataPersistanceTest extends BaseTest {
	@Autowired
	HibernateUserRepository userRepository;

	@Autowired
	HibernatePropertyRepository propertyRepository;

	@Autowired
	SessionFactory factory;

	@Test
	public void basicTest() {
		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);
		final User u2 = this.userRepository.get(User.class, u.getId());
		Assert.assertEquals(u, u2);
	}

	@Test
	public void propertiesListTest() {
		final Session session = this.factory.getCurrentSession();

		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		final Property property = new Property(Property.Type.APARTMENT,
				Property.Operation.RENT, "Flores", "Pedernera 35", 1233, 1, 23,
				23, 12, null, null, "", u);

		this.propertyRepository.save(property);

		session.flush();

		session.refresh(u);

		Assert.assertTrue(u.getProperties().contains(property));
		Assert.assertEquals(u, property.getOwner());
	}

	@Test
	public void serviceListTest() {
		this.propertiesListTest();

		final Session session = this.factory.getCurrentSession();

		final Property property = this.propertyRepository
				.get(Property.class, 1);

		property.getServices().add(Service.CABLE);

		property.getServices().add(Service.PHONE);

		session.flush();
		session.refresh(property);

		Assert.assertTrue(property.getServices().contains(Service.CABLE));
		Assert.assertTrue(property.getServices().contains(Service.PHONE));
		Assert.assertTrue(!property.getServices().contains(Service.SALON));

		property.getServices().remove(Service.CABLE);

		session.flush();
		session.refresh(property);

		Assert.assertTrue(!property.getServices().contains(Service.CABLE));
	}

	@Test
	public void roomListTest() {
		this.propertiesListTest();
		final Session session = this.factory.getCurrentSession();

		final Property property = this.propertyRepository
				.get(Property.class, 1);

		final Room room = new Room(RoomType.A, 10, property);

		this.propertyRepository.save(room);

		session.flush();

		session.refresh(property);

		Assert.assertTrue(property.getRooms().contains(
				new Room(RoomType.A, 10, property)));
		Assert.assertTrue(!property.getRooms().contains(
				new Room(RoomType.A, 11, property)));
		Assert.assertTrue(!property.getRooms().contains(
				new Room(RoomType.B, 11, property)));

	}

	@Test
	public void photoListTest() {
		this.propertiesListTest();

		final Session session = this.factory.getCurrentSession();

		final Property property = this.propertyRepository
				.get(Property.class, 1);

		final Photo photo = new Photo(new byte[] {}, "jpeg", property);

		property.addPhoto(photo);

		session.flush();

		Assert.assertTrue(property.getPhotos().contains(photo));
		Assert.assertEquals(photo.getProperty(), property);

		property.removePhoto(photo);

		session.flush();

		Assert.assertFalse(property.getPhotos().contains(photo));

	}

	@Test
	public void photoByIdTest() {
		this.propertiesListTest();

		final Session session = this.factory.getCurrentSession();

		final Property property = this.propertyRepository
				.get(Property.class, 1);

		final Photo photo = new Photo(new byte[] {}, "jpeg", property);
		property.addPhoto(photo);
		session.flush();
		final int id = photo.getId();

		boolean thrown = false;
		Photo photo2 = null;
		try {
			photo2 = this.propertyRepository.getPhotoById(id);
		} catch (final NoSuchEntityException e) {
			thrown = true;
		}

		Assert.assertFalse(thrown);

		Assert.assertTrue(photo.equals(photo2));

		thrown = false;

		try {
			final Photo photo3 = this.propertyRepository.getPhotoById(100);
		} catch (final NoSuchEntityException e) {
			thrown = true;
		}

		Assert.assertTrue(thrown);

	}
}
