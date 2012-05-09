package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
public class Property extends PersistentEntity {

	public enum Type {
		APARTMENT, HOUSE;
	}

	public enum Operation {
		SELL, RENT;
	}

	@Enumerated
	private Type type;

	@Enumerated
	private Operation operation;

	private String neighborhood;
	private String address;
	private Integer price;
	private Integer spaces;
	private Integer coveredArea;
	private Integer freeArea;
	private Integer age;
	// private List<String> services;
	private String description;

	private List<Photo> photos = new ArrayList<Photo>();
	private Boolean visible;

	private User owner;

	public Property() {

	}

	public Property(final Type type, final Operation operation,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final List<String> services,
			final String description, final User owner) {
		this.type = type;
		this.operation = operation;
		this.neighborhood = neighborhood;
		this.address = address;
		this.price = price;
		this.spaces = spaces;
		this.coveredArea = coveredArea;
		this.freeArea = freeArea;
		this.age = age;
		// this.services = services;
		this.description = description;
		this.owner = owner;
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

	public Type getType() {
		return this.type;
	}

	public Operation getOperation() {
		return this.operation;

	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public String getAddress() {
		return this.address;
	}

	public Integer getPrice() {
		return this.price;
	}

	public Integer getSpaces() {
		return this.spaces;
	}

	public Integer getCoveredArea() {
		return this.coveredArea;
	}

	public Integer getFreeArea() {
		return this.freeArea;
	}

	public Integer getAge() {
		return this.age;
	}

	public String getDescription() {
		return this.description;
	}

	public boolean getVisible() {
		return this.visible;
	}

	@Override
	public String toString() {
		return "Property [ID=" + this.getId() + ", type=" + this.type
				+ ", operation=" + this.operation + ", price=" + this.price
				+ "]";
	}

}
