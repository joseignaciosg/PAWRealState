package ar.edu.itba.it.paw.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Property implements Entity {

	public enum TYPE {
		APARTMENT, HOUSE
	}

	public enum OPERATION {
		SELL, RENT
	}

	private Integer ID;
	private boolean dirty;
	private TYPE type;
	private OPERATION operation;
	private String neighborhood;
	private String address;
	private Integer price;
	private Integer spaces;
	private Integer coveredArea;
	private Integer freeArea;
	private Integer age;
	private Services service;
	private String description;
	private List<Photo> photos;
	private Integer userID;

	public Property(final Integer iD, final TYPE type,
			final OPERATION operation, final String neighborhood,
			final String address, final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service, final String description) {
		this.ID = iD;
		this.type = type;
		this.operation = operation;
		this.neighborhood = neighborhood;
		this.address = address;
		this.price = price;
		this.spaces = spaces;
		this.coveredArea = coveredArea;
		this.freeArea = freeArea;
		this.age = age;
		this.service = service;
		this.description = description;
		this.photos = new ArrayList<Photo>();
		this.setDirty(false);
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(final Integer userID) {
		this.userID = userID;
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
		final Property other = (Property) obj;
		if (this.ID == null) {
			if (other.ID != null) {
				return false;
			}
		} else if (!this.ID.equals(other.ID)) {
			return false;
		}
		return true;
	}

	public Integer getID() {
		return this.ID;
	}

	public void setID(final Integer iD) {
		this.ID = iD;
		this.setDirty(true);
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	public TYPE getType() {
		return this.type;
	}

	public void setType(final TYPE type) {
		this.type = type;
		this.setDirty(true);
	}

	public OPERATION getOperation() {
		return this.operation;

	}

	public void setOperation(final OPERATION operation) {
		this.operation = operation;
		this.setDirty(true);
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
		this.setDirty(true);
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
		this.setDirty(true);
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(final Integer price) {
		this.price = price;
		this.setDirty(true);
	}

	public Integer getSpaces() {
		return this.spaces;
	}

	public void setSpaces(final Integer spaces) {
		this.spaces = spaces;
		this.setDirty(true);
	}

	public Integer getCoveredArea() {
		return this.coveredArea;
	}

	public void setCoveredArea(final Integer coveredArea) {
		this.coveredArea = coveredArea;
		this.setDirty(true);
	}

	public Integer getFreeArea() {
		return this.freeArea;
	}

	public void setFreeArea(final Integer freeArea) {
		this.freeArea = freeArea;
		this.setDirty(true);
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(final Integer age) {
		this.age = age;
		this.setDirty(true);
	}

	public Services getService() {
		return this.service;
	}

	public void setService(final Services service) {
		this.service = service;
		this.setDirty(true);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
		this.setDirty(true);
	}

	public void addPhoto(final Photo photo) {
		this.photos.add(photo);
	}

	public void removePhoto(final Photo photo) {
		this.photos.remove(photo);
	}

	@Override
	public String toString() {
		return "Property [ID=" + this.ID + ", price=" + this.price + "]";
	}

}
