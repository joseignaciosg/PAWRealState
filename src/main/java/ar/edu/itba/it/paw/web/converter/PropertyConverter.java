package ar.edu.itba.it.paw.web.converter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.services.PropertyService;

@Component
public class PropertyConverter implements Converter<String, Property> {

	PropertyService service;

	@Autowired
	public PropertyConverter(final PropertyService service) {
		this.service = service;
	}

	public Property convert(final String source) {
		try {
			return this.service.getPropertyByID(Integer.valueOf(source),
					new ArrayList<String>());
		} catch (final Exception e) {
			return null;
		}
	}

}
