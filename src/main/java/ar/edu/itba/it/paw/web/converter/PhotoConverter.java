package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Photo;
import ar.edu.itba.it.paw.domain.exceptions.NoSuchEntityException;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;

@Component
public class PhotoConverter implements IConverter<Photo> {
	@Autowired
	PropertyRepository propertyRepository;

	public Photo convertToObject(final String id, final Locale locale) {
		try {
			return this.propertyRepository.getPhotoById(Integer.valueOf(id));
		} catch (final NumberFormatException e) {
			return null;
		} catch (final NoSuchEntityException e) {
			return null;
		}
	}

	public String convertToString(final Photo value, final Locale locale) {
		return String.valueOf(value.getId());
	}
}
