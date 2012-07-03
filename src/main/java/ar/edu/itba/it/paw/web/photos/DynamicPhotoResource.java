package ar.edu.itba.it.paw.web.photos;

import org.apache.wicket.request.resource.*;
import org.apache.wicket.util.string.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;

public class DynamicPhotoResource extends DynamicImageResource {

	@Override
	protected byte[] getImageData(final Attributes attributes) {
		Photo photo;
		try {
			photo = new EntityModel<Photo>(Photo.class, attributes
					.getParameters().get("id").toInt()).getObject();
			return photo.getData();
		} catch (final StringValueConversionException e) {
		}

		return null;
	}
}