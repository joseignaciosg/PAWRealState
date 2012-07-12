package ar.edu.itba.it.paw.web.photos;

import org.apache.wicket.request.resource.*;

@SuppressWarnings("serial")
public class ImageResourceReference extends ResourceReference {

	public ImageResourceReference() {
		super(ImageResourceReference.class, "images");
	}

	@Override
	public IResource getResource() {
		return new DynamicPhotoResource();
	}

	@Override
	public boolean canBeRegistered() {
		return true;
	}
}
