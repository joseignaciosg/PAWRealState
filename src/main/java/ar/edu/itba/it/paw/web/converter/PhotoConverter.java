package ar.edu.itba.it.paw.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.exceptions.NoSuchEntityException;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;

@Component
public class PhotoConverter implements Converter<String, Photo> {
	@Autowired
	PropertyRepository propertyRepository;

	public Photo convert(final String source) {
		try {
			return this.propertyRepository
					.getPhotoById(Integer.valueOf(source));
		} catch (final NumberFormatException e) {
			return null;
		} catch (final NoSuchEntityException e) {
			return null;
		}
	}
}
