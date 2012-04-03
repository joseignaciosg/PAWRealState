package ar.edu.itba.it.paw.model.entities;

public class Photo implements Entity {

	private Integer id;
	private byte[] data = null;
	private String type;
	private boolean dirty;
	private Integer propertyid;
	private boolean isnew;

	public Photo(final Integer id, final byte[] data, final String type) {
		this.id = id;
		this.data = data;
		this.type = type;
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

	public Integer getPropertyid() {
		return this.propertyid;
	}

	public void setPropertyid(final Integer propertyid) {
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
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
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

}
