package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
// TODO: use natural key
public class Room extends PersistentEntity {
	public enum RoomType {
		A, B, C;
	}

	@Enumerated(EnumType.STRING)
	private RoomType type;

	private int size;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	public Room(final RoomType type, final int size, final Property property) {
		this.type = type;
		this.size = size;
		this.setProperty(property);
	}

	public RoomType getType() {
		return this.type;
	}

	public int getSize() {
		return this.size;
	}

	@Override
	public String toString() {
		return "Room [type=" + this.type + ", size=" + this.size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.size;
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
		final Room other = (Room) obj;
		if (this.size != other.size) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		return true;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

}