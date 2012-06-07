package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.repositories.impl.HibernatePropertyRepository;

@Component
public class PropertyConverter implements IConverter<Property> {

	@Autowired
	HibernatePropertyRepository repository;

	public Property convertToObject(final String id, final Locale locale) {
		return this.repository.get(Property.class, Integer.valueOf(id));
	}

	public String convertToString(final Property value, final Locale locale) {
		return String.valueOf(value.getId());
	}
}
