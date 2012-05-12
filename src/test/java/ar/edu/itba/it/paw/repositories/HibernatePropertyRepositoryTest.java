package ar.edu.itba.it.paw.repositories;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.BaseTest;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

public class HibernatePropertyRepositoryTest extends BaseTest {
	@Autowired
	HibernateUserRepository userRepository;

	@Autowired
	HibernatePropertyRepository propertyRepository;

	@Autowired
	SessionFactory factory;

	@Test
	public void searchTest() {
		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.userRepository.save(u);

		final Property property = new Property(Property.Type.APARTMENT,
				Property.Operation.RENT, "Flores", "Pedernera 35", 1233, 1, 23,
				23, 12, null, null, u);

	}
}
