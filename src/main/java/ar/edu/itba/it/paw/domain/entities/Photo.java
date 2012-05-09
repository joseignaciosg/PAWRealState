package ar.edu.itba.it.paw.domain.entities;

import java.util.Arrays;

public class Photo extends BaseEntity {

	private byte[] data = null;
	private String type;
	private Integer propertyid;

	public Photo(final byte[] data, final String type, final int propertyid) {
		this(null, data, type, propertyid);
	}

	public Photo(final Integer id, final byte[] data, final String type,
			final int propertyid) {
		super(id);
		this.data = data;
		this.type = type;
		this.propertyid = propertyid;
	}

	public byte[] getData() {
		return this.data;
	}

	public String getType() {
		return this.type;
	}

	public Integer getPropertyId() {
		return this.propertyid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.data);
		result = prime * result
				+ ((this.propertyid == null) ? 0 : this.propertyid.hashCode());
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Photo other = (Photo) obj;
		if (!Arrays.equals(this.data, other.data)) {
			return false;
		}
		if (this.propertyid == null) {
			if (other.propertyid != null) {
				return false;
			}
		} else if (!this.propertyid.equals(other.propertyid)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Photo [id=" + this.getId() + "]";
	}

}
