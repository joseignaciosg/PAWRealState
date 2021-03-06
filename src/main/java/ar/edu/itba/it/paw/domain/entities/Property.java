package ar.edu.itba.it.paw.domain.entities;

import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.*;
import org.hibernate.annotations.*;

import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

@Entity
@Table(name = "properties")
public class Property extends PersistentEntity {

	public enum Currency {
		PESO("$"), DOLLAR("U$S");

		private String prefix;

		Currency(final String prefix) {
			this.prefix = prefix;
		}

		public String getPrefix() {
			return this.prefix;
		}

		@Override
		public String toString() {
			return this.getPrefix();
		}
	}

	public enum Type {
		APARTMENT, HOUSE;
	}

	public enum Operation {
		SELL, RENT;
	}

	@Table(name = "services")
	public enum Service {
		TENIS("Tenis"), SECURITY("Vigilancia Nocturna"), LAUNDRY(
				"Servicio de Laundry"), SOLARIUM("Solarium"), CABLE("Cable"), PHONE(
				"Telefono"), SWIMMING("Pileta de natacion"), SALON("Salon"), PADDLE(
				"Cancha de paddle"), QUINCHO("Quincho");

		private final String name;

		Service(final String name) {
			this.name = name;
		}

		public String getEnumName() {
			return this.name();
		}
	}

	@OneToMany(mappedBy = "property", cascade = { CascadeType.ALL,
			CascadeType.REMOVE })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<State> states = new ArrayList<State>();

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@CollectionOfElements
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	@JoinTable(name = "services", joinColumns = @JoinColumn(name = "property_id"))
	@Enumerated(EnumType.STRING)
	private List<Service> services = new ArrayList<Service>();

	@Enumerated(EnumType.STRING)
	private Type type;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction")
	private Operation operation;
	private String neighborhood;
	private String address;
	private Integer price;

	@Column(name = "rooms")
	private Integer spaces;

	@Column(name = "csqm")
	private Integer coveredArea;
	@Column(name = "usqm")
	private Integer freeArea;
	private Integer age;

	private String description;

	@OneToMany(mappedBy = "property", cascade = { CascadeType.ALL,
			CascadeType.REMOVE })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<Photo> photos = new ArrayList<Photo>();

	private boolean visible;
	@OneToMany(mappedBy = "property", cascade = { CascadeType.ALL,
			CascadeType.REMOVE })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<Room> rooms = new ArrayList<Room>();

	@OneToMany(mappedBy = "property", cascade = { CascadeType.ALL,
			CascadeType.REMOVE })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<ContactRequest> contactRequests = new ArrayList<ContactRequest>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;

	@Column(name = "visitcount")
	private Integer visitCount;

	@Column(name = "reserved", nullable = false)
	private boolean reserved;

	@Column(name = "sold")
	private boolean sold;

	Property() {
		this.visitCount = 0;
		this.visible = true;
	}

	public static List<Service> getAllServices() {
		return Arrays.asList(Service.values());
	}

	public static List<RoomType> getAllRoomTypes() {
		return Arrays.asList(RoomType.values());
	}

	public Property(final Type type, final Operation operation,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final List<Service> services,
			final List<Room> rooms, final String description, final User owner,
			final Currency currency) {
		Validate.notNull(type);
		Validate.notNull(operation);
		Validate.notNull(neighborhood);
		Validate.notNull(address);
		Validate.notNull(price);
		Validate.notNull(spaces);
		Validate.notNull(coveredArea);
		Validate.notNull(age);
		Validate.notNull(description);

		if (price < 0 || spaces < 0 || coveredArea < 0 || freeArea < 0
				|| age < 0) {
			throw new IllegalArgumentException();
		}

		this.type = type;
		this.operation = operation;
		this.neighborhood = neighborhood;
		this.address = address;
		this.price = price;
		this.spaces = spaces;
		this.coveredArea = coveredArea;
		this.freeArea = freeArea;
		this.age = age;
		if (services != null) {
			this.services = services;
		}
		this.description = description;
		this.owner = owner;
		if (rooms != null) {
			for (final Room room : rooms) {
				if (room == null) {
					rooms.remove(room);
				}
			}
			for (final Room room : rooms) {
				room.setProperty(this);
			}
			this.rooms = rooms;
		}
		this.visitCount = 0;
		this.reserved = false;
		this.visible = true;
		this.currency = (currency != null) ? currency : Currency.DOLLAR;
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

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public Integer getVisitCount() {
		return this.visitCount;
	}

	public boolean isReserved() {
		return this.reserved;
	}

	public void setReserved(final boolean reserved) {
		this.reserved = reserved;
	}

	public boolean isSold() {
		return this.sold;
	}

	public void setSold(final boolean sold) {
		this.sold = sold;
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
		result = prime * result
				+ ((this.currency == null) ? 0 : this.currency.hashCode());
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
				+ ((this.owner == null) ? 0 : this.owner.hashCode());
		result = prime * result
				+ ((this.price == null) ? 0 : this.price.hashCode());
		result = prime * result + (this.reserved ? 1231 : 1237);
		result = prime * result + (this.sold ? 1231 : 1237);
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
		result = prime * result + (this.visible ? 1231 : 1237);
		result = prime * result
				+ ((this.visitCount == null) ? 0 : this.visitCount.hashCode());
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
		if (this.currency != other.currency) {
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
		if (this.owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!this.owner.equals(other.owner)) {
			return false;
		}
		if (this.price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!this.price.equals(other.price)) {
			return false;
		}
		if (this.reserved != other.reserved) {
			return false;
		}
		if (this.sold != other.sold) {
			return false;
		}
		if (this.type != other.type) {
			return false;
		}
		if (this.visible != other.visible) {
			return false;
		}
		if (this.visitCount == null) {
			if (other.visitCount != null) {
				return false;
			}
		} else if (!this.visitCount.equals(other.visitCount)) {
			return false;
		}
		return true;
	}

	public void toggleSold() {
		if (this.sold == true) {
			this.addStates(new State(State.StateType.SOLD,
					State.StateType.ONSALE));
		} else {
			this.addStates(new State(State.StateType.ONSALE,
					State.StateType.SOLD));
		}
		this.sold = !this.sold;
	}

	public void setVisitCount(final int visitCount) {
		this.visitCount = visitCount;
	}

	public void addVisit() {
		this.visitCount++;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public void setOperation(final Operation operation) {
		this.operation = operation;
	}

	public void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setPrice(final Integer price) {
		this.price = price;
	}

	public void setSpaces(final Integer spaces) {
		this.spaces = spaces;
	}

	public void setCoveredArea(final Integer coveredArea) {
		this.coveredArea = coveredArea;
	}

	public void setFreeArea(final Integer freeArea) {
		this.freeArea = freeArea;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void toggleVisibility() {
		if (this.visible == true) {
			this.addStates(new State(State.StateType.VISIBLE,
					State.StateType.UNVISIBLE));
		} else {
			this.addStates(new State(State.StateType.UNVISIBLE,
					State.StateType.VISIBLE));
		}

		this.visible = !this.visible;
	}

	public void addPhoto(final Photo photo) {
		this.photos.add(photo);
	}

	public void removePhoto(final Photo photo) {
		this.photos.remove(photo);
	}

	public List<Service> getServices() {
		return this.services;
	}

	public List<State> getStates() {
		return this.states;
	}

	public void addStates(final State state) {
		this.states.add(state);
		state.setProperty(this);
	}

	public List<Room> getRooms() {
		return new ArrayList<Room>(this.rooms);
	}

	public void clearRooms() {
		this.rooms.clear();
	}

	public List<ContactRequest> getContactRequest() {
		return this.contactRequests;
	}

	public void addContactRequest(final ContactRequest request) {
		this.contactRequests.add(request);
	}

	public boolean removeContactRequest(final ContactRequest request) {

		if (this.contactRequests.contains(request)) {
			this.contactRequests.remove(request);
			return true;
		}
		return false;
	}

	public void updateVisitCount() {
		this.visitCount++;
	}

	public void toggleReserve() {
		if (this.reserved == true) {
			this.addStates(new State(State.StateType.RESERVED,
					State.StateType.UNRESERVED));
		} else {
			this.addStates(new State(State.StateType.UNRESERVED,
					State.StateType.RESERVED));
		}

		this.reserved = !this.reserved;
	}

	public void addRoom(final Room room) {
		this.rooms.add(room);
		room.setProperty(this);
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(final Currency currency) {
		this.currency = currency;
	}

	public int getSquareMeterPrice() {
		int dividing = this.coveredArea + this.freeArea;
		if (dividing == 0) {
			dividing = 1;
		}
		return this.price / (this.coveredArea + this.freeArea);
	}
}
