package ar.edu.itba.it.paw.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:data-test.xml" })
@TransactionConfiguration
@Transactional
public class UserRepositoryTest {
	@Autowired
	HibernateUserRepository repository;

	@Test
	public void basicTest() {
		final User u = new User("name", "username", "bla", "bla", "bla", "bla");
		this.repository.save(u);
		final User u2 = this.repository.get(User.class, u.getId());

		Assert.isTrue(u.equals(u2));
	}
}
