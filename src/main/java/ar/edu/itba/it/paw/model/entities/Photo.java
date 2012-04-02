package ar.edu.itba.it.paw.model.entities;

public class Photo implements Entity {

	private Integer ID;
	private byte[] data = null;
	private String type;
	private boolean dirty;

	public Photo(final Integer ID, final byte[] data, final String type) {
		this.ID = ID;
		this.data = data;
		this.type = type;
		this.dirty = false;
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

	public Integer getID() {
		return this.ID;
	}

	public void setID(final Integer ID) {
		this.ID = ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.ID == null) ? 0 : this.ID.hashCode());
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
		if (this.ID == null) {
			if (other.ID != null) {
				return false;
			}
		} else if (!this.ID.equals(other.ID)) {
			return false;
		}
		return true;
	}

}
