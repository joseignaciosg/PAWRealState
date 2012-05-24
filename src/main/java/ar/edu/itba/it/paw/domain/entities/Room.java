package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room extends PersistentEntity {
	public enum RoomType {
		BATHROOM("Baño"), DORM("Dormitorio"), KITCHEN("Cocina"), LIVING(
				"Living"), PLAYROOM("Playroom");

		private String humanName;

		private RoomType(final String humanName) {
			this.humanName = humanName;
		}

		public String getHumanName() {
			return this.humanName;
		}

	}

	@Enumerated(EnumType.STRING)
	private RoomType type;

	private int size;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	// Spring requires it!
	public Room() {

	}

	public Room(final RoomType type, final int size) {
		this.type = type;
		this.size = size;
	}

	public Room(final RoomType type, final int size, final Property property) {
		this.type = type;
		this.size = size;
		this.setProperty(property);
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
		final Room other = (Room) obj;
		if (this.property == null) {
			if (other.property != null) {
				return false;
			}
		} else if (!this.property.equals(other.property)) {
			return false;
		}
		if (this.size != other.size) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

	// Spring requires it!
	public Property getProperty() {
		return this.property;
	}

	public int getSize() {
		return this.size;
	}

	public RoomType getType() {
		return this.type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.property == null) ? 0 : this.property.hashCode());
		result = prime * result + this.size;
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

	public void setSize(final int size) {
		this.size = size;
	}

	public void setType(final RoomType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Room [type=" + this.type + ", size=" + this.size + "]";
	}

}
