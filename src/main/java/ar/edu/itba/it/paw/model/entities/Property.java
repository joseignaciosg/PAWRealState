package ar.edu.itba.it.paw.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Property implements Entity {

	public enum Type {
		APARTMENT, HOUSE;

		public static Type fromString(final String s) {
			if (s.equals("APARTMENT")) {
				return APARTMENT;
			} else if (s.equals("HOUSE")) {
				return HOUSE;
			}
			return null;
		}

		@Override
		public String toString() {
			switch (this) {
			case APARTMENT:
				return "APARTMENT";
			case HOUSE:
				return "HOUSE";
			default:
				return "";
			}
		}
	}

	public enum Operation {
		SELL, RENT;

		public static Operation fromString(final String s) {
			if (s.equals("RENT")) {
				return RENT;
			} else if (s.equals("SELL")) {
				return SELL;
			}
			return null;
		}

		@Override
		public String toString() {
			switch (this) {
			case SELL:
				return "SELL";
			case RENT:
				return "RENT";
			default:
				return "";
			}
		}
	}

	private Integer id;
	private boolean dirty;
	private Type type;
	private Operation operation;
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
	private User owner;

	public Property(final Type type, final Operation operation,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service, final String description) {
		this(null, type, operation, neighborhood, address, price, spaces,
				coveredArea, freeArea, age, service, description);
		this.setDirty(false);
	}

	public Property(final Integer id, final Type type,
			final Operation operation, final String neighborhood,
			final String address, final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service, final String description) {
		this.id = id;
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

	public String getPropertyType() {
		return this.type.toString();
	}

	public String getOperationType() {
		return this.operation.toString();
	}

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(final User user) {
		this.owner = user;
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
		final Property other = (Property) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
		this.setDirty(true);
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
		this.setDirty(true);
	}

	public Operation getOperation() {
		return this.operation;

	}

	public void setOperation(final Operation operation) {
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
		return "Property [ID=" + this.id + ", type=" + this.type
				+ ", operation=" + this.operation + ", price=" + this.price
				+ "]";
	}

}
