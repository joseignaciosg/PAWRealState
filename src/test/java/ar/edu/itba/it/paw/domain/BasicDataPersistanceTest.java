package ar.edu.itba.it.paw.domain;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.User;
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
public class BasicDataPersistanceTest {
	@Autowired
	HibernateUserRepository userRepository;

	@Autowired
	HibernatePropertyRepository propertyRepository;

	@Autowired
	SessionFactory factory;

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
				23, 12, null, "", u);

		this.propertyRepository.save(property);

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

		property.getServices().add(Service.A);

		this.propertyRepository.save(property);

		session.flush();

		session.refresh(property);

		Assert.assertTrue(property.getServices().contains(Service.A));
	}

	@Test
	public void photoListTest() {
		this.propertiesListTest();

		final Session session = this.factory.getCurrentSession();

		final Property property = this.propertyRepository
				.get(Property.class, 1);

		final Photo photo = new Photo(new byte[] {}, "jpeg", property);

		property.getPhotos().add(photo);

		this.propertyRepository.save(photo);

		session.refresh(property);

		Assert.assertTrue(property.getPhotos().contains(photo));
		Assert.assertEquals(photo.getProperty(), property);

	}
}
