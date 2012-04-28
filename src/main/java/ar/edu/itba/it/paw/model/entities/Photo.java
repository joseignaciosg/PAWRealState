package ar.edu.itba.it.paw.model.entities;

import java.util.Arrays;

public class Photo implements Entity {

	private Integer id;
	private byte[] data = null;
	private String type;
	private boolean dirty;
	private Integer propertyid;
	private boolean isnew;

	public Photo(final Integer id, final byte[] data, final String type,
			final int propertyid) {
		this.id = id;
		this.data = data;
		this.type = type;
		this.propertyid = propertyid;
		this.dirty = false;
		this.isnew = true;
	}

	public byte[] getData() {
		return this.data;
	}

	public void setData(final byte[] data) {
		this.setDirty(true);
		this.data = data;
	}

	public String getType() {
		return this.type;
	}

	public Integer getPropertyId() {
		return this.propertyid;
	}

	public void setPropertyId(final Integer propertyid) {
		this.setDirty(true);
		this.propertyid = propertyid;
	}

	public void setType(final String type) {
		this.setDirty(true);
		this.type = type;
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
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

	public boolean isNew() {
		return this.isnew;
	}

	public void setNew(final boolean isnew) {
		this.isnew = isnew;
	}

	@Override
	public String toString() {
		return "Photo [id=" + this.id + "]";
	}

}
