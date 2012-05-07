package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.UserService;

@Component
public class UserConverter implements Converter<Integer, User> {

	private UserService service;

	@Autowired
	public UserConverter(final UserService service) {
		this.service = service;
	}

	public User convert(final Integer source) {
		return this.service.getById(source);
	}

}
