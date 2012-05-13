package ar.edu.itba.it.paw.domain;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.RealStateAgency;
import ar.edu.itba.it.paw.domain.entities.Room;
import ar.edu.itba.it.paw.domain.entities.Room.RoomType;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.exceptions.InvalidLoginException;
import ar.edu.itba.it.paw.domain.exceptions.NoSuchEntityException;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

/**
 * Checks the consistency of the ORM mappings
 * 
 * @author cris
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:data-test.xml" })
@Transactional
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BasicDataPersistanceTest {
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
	public void propertiesSearch() {

		final Session session = this.factory.getCurrentSession();

		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		final Property property = new Property(Property.Type.APARTMENT,
				Property.Operation.RENT, "Flores", "Pedernera 35", 1233, 1, 23,
				23, 12, null, null, "", u);

		final PropertySearch propSearch = new PropertySearch(null, null, null,
				null, null, null, Order.ASC, null, null, true, null);

		final int oldVisibleCount = this.propertyRepository.getAll(propSearch)
				.size();

		this.propertyRepository.save(property);

		session.flush();

		int newVisibleCount = this.propertyRepository.getAll(propSearch).size();

		Assert.assertEquals(oldVisibleCount, newVisibleCount);

		this.propertyRepository.save(property);
		session.flush();

		newVisibleCount = this.propertyRepository.getAll().size();

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

		final Room room = new Room(RoomType.BATHROOM, 10, property);

		this.propertyRepository.save(room);

		session.flush();

		session.refresh(property);

		Assert.assertTrue(property.getRooms().contains(
				new Room(RoomType.BATHROOM, 10, property)));
		Assert.assertTrue(!property.getRooms().contains(
				new Room(RoomType.BATHROOM, 11, property)));
		Assert.assertTrue(!property.getRooms().contains(
				new Room(RoomType.DORM, 11, property)));

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
