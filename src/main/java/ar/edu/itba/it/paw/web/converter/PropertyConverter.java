package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;

@Component
public class PropertyConverter implements Converter<String, Property> {

	@Autowired
	HibernatePropertyRepository repository;

	public Property convert(final String id) {
		return this.repository.get(Property.class, Integer.valueOf(id));
	}
}
