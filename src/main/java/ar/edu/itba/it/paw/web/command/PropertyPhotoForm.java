package ar.edu.itba.it.paw.web.command;

import org.springframework.web.multipart.MultipartFile;

import ar.edu.itba.it.paw.domain.entities.Property;

public class PropertyPhotoForm {

	private MultipartFile file;
	private Property property;

	public PropertyPhotoForm() {
	}

	public PropertyPhotoForm(final MultipartFile file, final Property property) {
		this.file = file;
		this.property = property;
	}

	public MultipartFile getFile() {
		return this.file;
	}

	public void setFile(final MultipartFile file) {
		this.file = file;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

}
