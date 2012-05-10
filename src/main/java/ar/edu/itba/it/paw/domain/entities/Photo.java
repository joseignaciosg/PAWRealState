package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo extends PersistentEntity {

	private byte[] data = null;
	private String type;

	@ManyToOne
	@JoinColumn(name = "property_id")
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
