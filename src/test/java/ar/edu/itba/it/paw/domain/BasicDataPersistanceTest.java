package ar.edu.itba.it.paw.domain;

import junit.framework.Assert;

import org.hibernate.*;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Room.RoomType;
import ar.edu.itba.it.paw.domain.exceptions.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;

/**
 * Checks the consistency of the ORM mappings
 * 
 * @author cris
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:data-test.xml" })
@Transactional
public class BasicDataPersistanceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
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
				23, 12, null, null, "", u, null);

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

	@Ignore
	@Test
	public void roomListTest() {
		this.propertiesListTest();
		final Session session = this.factory.getCurrentSession();

		final Property property = this.propertyRepository
				.get(Property.class, 1);

		final Room room = new Room(RoomType.BATHROOM, 10, 1, property);

		this.propertyRepository.save(room);

		session.flush();

		session.refresh(property);

		Assert.assertTrue(property.getRooms().contains(
				new Room(RoomType.BATHROOM, 1, 1, property)));
		Assert.assertTrue(!property.getRooms().contains(
				new Room(RoomType.BATHROOM, 11, 1, property)));
		Assert.assertTrue(!property.getRooms().contains(
				new Room(RoomType.DORM, 11, 1, property)));

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
			this.propertyRepository.getPhotoById(100);
		} catch (final NoSuchEntityException e) {
			thrown = true;
		}

		Assert.assertTrue(thrown);

	}

	public void removeUsersTest() {
		this.propertiesListTest();

		final Session session = this.factory.getCurrentSession();

		final User oldUser = this.userRepository.getByName("username");

		final int oldSize = oldUser.getProperties().size();

		oldUser.getProperties().remove(0);

		session.flush();

		final User newUser = this.userRepository.getByName("username");
		final int newSize = newUser.getProperties().size();

		Assert.assertEquals(newSize, oldSize + 1);

	}

	public void userAndAgencyTest() throws InvalidLoginException {

		final Session session = this.factory.getCurrentSession();
		final User u = new User("name", "username", "bla", "bla", "bla", "bla");

		final Photo p = new Photo(new byte[] {}, "jpeg");

		final RealStateAgency u2 = new RealStateAgency("Cristian", "Pereyra",
				"criis.pereyra@algunlugar.com", "1233455", "kshmir", "login",
				"Kshmirs real state", p);

		this.userRepository.save(u);
		this.userRepository.save(u2);

		session.flush();
		session.clear();
		session.refresh(u2);

		Assert.assertEquals(
				this.userRepository.getByNameAndPassword("kshmir", "login")
						.getName(), u2.getName());
		Assert.assertEquals(2, this.userRepository.find("from User").size());
		Assert.assertEquals(u2.getName(), p.getAgency().getName());
		Assert.assertEquals(u2.getPhoto().getType(), p.getType());

	}

}
