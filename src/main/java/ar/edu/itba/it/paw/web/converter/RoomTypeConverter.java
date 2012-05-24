package ar.edu.itba.it.paw.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

@Component
public class RoomTypeConverter implements Converter<String, RoomType> {
	public RoomType convert(final String value) {
		return RoomType.valueOf(value);
	}
}
