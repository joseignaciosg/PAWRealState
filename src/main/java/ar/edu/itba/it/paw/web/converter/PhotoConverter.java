package ar.edu.itba.it.paw.web.converter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.services.PhotoService;

@Component
public class PhotoConverter implements Converter<String, Photo> {

	private PhotoService service;

	@Autowired
	public PhotoConverter(final PhotoService service) {
		this.service = service;
	}

	public Photo convert(final String source) {
		try {
			return this.service.getPhotoById(Integer.valueOf(source),
					new ArrayList<String>());
		} catch (final Exception e) {
			return null;
		}
	}
}
