package ar.edu.itba.it.paw.web.photos;

import org.apache.wicket.request.resource.*;
import org.apache.wicket.util.string.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.exceptions.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

public class ImageResource extends DynamicImageResource {

	private PropertyRepository repo;

	public ImageResource(final PropertyRepository repo) {
		this.repo = repo;
	}

	@Override
	protected byte[] getImageData(final Attributes attributes) {
		Photo photo;
		try {
			photo = this.repo.getPhotoById(attributes.getParameters().get("id")
					.toInt());
			return photo.getData();
		} catch (final StringValueConversionException e) {
		} catch (final NoSuchEntityException e) {
		}

		return null;
	}
}