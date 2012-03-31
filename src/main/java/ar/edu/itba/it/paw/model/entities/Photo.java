package ar.edu.itba.it.paw.model.entities;

public class Photo implements Entity {

	private byte[] data = null;
	private String type;

	public Photo(final byte[] data, final String type) {
		this.data = data;
		this.type = type;
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
		// TODO Auto-generated method stub
		return false;
	}

	public Integer getID() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setID(final Integer ID) {
		// TODO Auto-generated method stub

	}

}
