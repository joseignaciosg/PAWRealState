package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property.Type;

@Component
public class TypeConverter implements IConverter<Type> {

	public Type convertToObject(final String type, final Locale locale) {
		if (type.toLowerCase().equals("house")) {
			return Type.HOUSE;
		} else if (type.toLowerCase().equals("apartment")) {
			return Type.APARTMENT;
		} else {
			return null;
		}
	}

	public String convertToString(final Type value, final Locale locale) {
		return value.toString();
	}

}
