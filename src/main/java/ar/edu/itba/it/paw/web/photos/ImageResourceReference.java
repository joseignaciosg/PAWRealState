package ar.edu.itba.it.paw.web.photos;

import org.apache.wicket.request.resource.*;

import ar.edu.itba.it.paw.domain.repositories.api.*;

public class ImageResourceReference extends ResourceReference {

	PropertyRepository repo;

	public ImageResourceReference(final PropertyRepository repo) {
		super(ImageResourceReference.class, "images");
		this.repo = repo;
	}

	@Override
	public IResource getResource() {
		return new ImageResource(this.repo);
	}

}
