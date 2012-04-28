package ar.edu.itba.it.paw.web.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.services.PropertyService;

@Component
public class PropertyConverter implements Converter<Integer, Property> {

	PropertyService service;

	@Autowired
	public PropertyConverter(final PropertyService service) {
		this.service = service;
	}

	public Property convert(final Integer source) {
		final List<String> errors = new ArrayList<String>();
		return this.service.getPropertyByID(source, errors);
	}

}
