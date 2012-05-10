package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Photo extends PersistentEntity {

	private byte[] data = null;
	private String type;

	@ManyToOne
	private Property property;

	public Photo(final byte[] data, final String type, final Property property) {
		this.data = data;
		this.type = type;
		this.property = property;
	}

	public byte[] getData() {
		return this.data;
	}

	public String getType() {
		return this.type;
	}

	public Property getProperty() {
		return this.property;
	}

}
