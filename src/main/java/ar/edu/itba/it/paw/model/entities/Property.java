package ar.edu.itba.it.paw.model.entities;

import java.util.ArrayList;
import java.util.Collection;

import ar.edu.itba.it.paw.utils.collections.CollectionWithMemory;

public class Property implements Entity {

	public enum Type {
		APARTMENT, HOUSE;

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
	private Collection<Photo> photos;

	public Collection<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(final Collection<Photo> photos) {
		this.photos = photos;
	}

	private User owner;
	private boolean visible;
	private boolean isnew;

	public Property(final Type type, final Operation operation,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service,
			final String description, final User owner) {
		this(null, type, operation, neighborhood, address, price, spaces,
				coveredArea, freeArea, age, service, description, owner);
		this.setDirty(false);
		this.setVisible(true);
	}

	public Property(final Integer id, final Type type,
			final Operation operation, final String neighborhood,
			final String address, final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service,
			final String description, final User owner) {
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
		this.photos = new CollectionWithMemory<Photo>(new ArrayList<Photo>());
		this.setOwner(owner);
		this.setVisible(true);
		this.setDirty(false);
		this.isnew = true;
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
		result = prime * result
				+ ((this.address == null) ? 0 : this.address.hashCode());
		result = prime * result
				+ ((this.age == null) ? 0 : this.age.hashCode());
		result = prime
				* result
				+ ((this.coveredArea == null) ? 0 : this.coveredArea.hashCode());
		result = prime
				* result
				+ ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result
				+ ((this.freeArea == null) ? 0 : this.freeArea.hashCode());
		result = prime
				* result
				+ ((this.neighborhood == null) ? 0 : this.neighborhood
						.hashCode());
		result = prime * result
				+ ((this.operation == null) ? 0 : this.operation.hashCode());
		result = prime * result
				+ ((this.price == null) ? 0 : this.price.hashCode());
		result = prime * result
				+ ((this.service == null) ? 0 : this.service.hashCode());
		result = prime * result
				+ ((this.spaces == null) ? 0 : this.spaces.hashCode());
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
		result = prime * result + (this.visible ? 1231 : 1237);
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
		if (this.address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!this.address.equals(other.address)) {
			return false;
		}
		if (this.age == null) {
			if (other.age != null) {
				return false;
			}
		} else if (!this.age.equals(other.age)) {
			return false;
		}
		if (this.coveredArea == null) {
			if (other.coveredArea != null) {
				return false;
			}
		} else if (!this.coveredArea.equals(other.coveredArea)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.freeArea == null) {
			if (other.freeArea != null) {
				return false;
			}
		} else if (!this.freeArea.equals(other.freeArea)) {
			return false;
		}
		if (this.neighborhood == null) {
			if (other.neighborhood != null) {
				return false;
			}
		} else if (!this.neighborhood.equals(other.neighborhood)) {
			return false;
		}
		if (this.operation != other.operation) {
			return false;
		}
		if (this.price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!this.price.equals(other.price)) {
			return false;
		}
		if (this.service == null) {
			if (other.service != null) {
				return false;
			}
		} else if (!this.service.equals(other.service)) {
			return false;
		}
		if (this.spaces == null) {
			if (other.spaces != null) {
				return false;
			}
		} else if (!this.spaces.equals(other.spaces)) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		if (this.visible != other.visible) {
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

	public boolean getVisible() {
		return this.visible;
	}

	public void setVisible(final boolean visible) {
		this.visible = visible;
		this.setDirty(true);
	}

	@Override
	public String toString() {
		return "Property [ID=" + this.id + ", type=" + this.type
				+ ", operation=" + this.operation + ", price=" + this.price
				+ "]";
	}

	public boolean isNew() {
		return this.isnew;
	}

	public void setNew(final boolean isnew) {
		this.isnew = isnew;
	}
}
