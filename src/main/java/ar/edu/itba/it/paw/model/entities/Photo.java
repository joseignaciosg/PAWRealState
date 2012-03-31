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
		this.data = data;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
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

}
