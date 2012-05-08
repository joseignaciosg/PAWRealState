package ar.edu.itba.it.paw.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.entities.Property.Type;

@Component
public class TypeConverter implements Converter<String, Type> {

	public Type convert(final String type) {
		if (type.toLowerCase().equals("house")) {
			return Type.HOUSE;
		} else if (type.toLowerCase().equals("apartment")) {
			return Type.APARTMENT;
		} else {
			return null;
		}
	}

}
