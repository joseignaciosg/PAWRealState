package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

@Component
public class UserConverter implements Converter<String, User> {

	@Autowired
	HibernateUserRepository repository;

	public User convert(final String id) {
		if (id.equals("any")) {
			return null;
		}
		if (id.equals("")) {
			return null;
		}

		return this.repository.get(User.class, Integer.valueOf(id));
	}

}
