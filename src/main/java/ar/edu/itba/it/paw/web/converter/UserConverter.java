package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;

@Component
public class UserConverter implements IConverter<User> {

	@Autowired
	HibernateUserRepository repository;

	public User convertToObject(final String id, final Locale locale) {
		if (id.equals("any")) {
			return null;
		}
		if (id.equals("")) {
			return null;
		}
		return this.repository.get(User.class, Integer.valueOf(id));
	}

	public String convertToString(final User value, final Locale locale) {
		return String.valueOf(value.getId());
	}
}
