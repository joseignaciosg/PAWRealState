package ar.edu.itba.it.paw.web.converters;

import java.util.*;

import org.apache.wicket.util.convert.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;

@SuppressWarnings("serial")
public class PropertyConverter implements IConverter<Property> {

	private HibernatePropertyRepository repo;

	public PropertyConverter(final HibernatePropertyRepository repo) {
		this.repo = repo;
	}

	public Property convertToObject(final String value, final Locale locale) {
		return this.repo.get(Property.class, Integer.valueOf(value));
	}

	public String convertToString(final Property value, final Locale locale) {
		return value.getId().toString();
	}

}