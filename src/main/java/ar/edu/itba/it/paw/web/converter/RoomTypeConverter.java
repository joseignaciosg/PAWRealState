package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

@Component
public class RoomTypeConverter implements IConverter<RoomType> {

	public RoomType convertToObject(final String value, final Locale locale) {
		return RoomType.valueOf(value);
	}

	public String convertToString(final RoomType value, final Locale locale) {
		return value.toString();
	}

}
