package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CollectionOfElements;

import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

@Entity
@Table(name = "properties")
public class Property extends PersistentEntity {

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

		@Override
		public String toString() {
			return this.name;
		}
	}

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

	@Column(name = "reserved")
	private boolean reserved;

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
			final List<Room> rooms, final String description, final User owner) {
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
			this.rooms = rooms;
		}
		this.visitCount = 0;
		this.reserved = false;
		this.visible = true;
	}

	public void setServices(final List<Service> services) {
		this.services = services;
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

	public void setVisitCount(final int visitCount) {
		this.visitCount = visitCount;
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

	public void setPhotos(final List<Photo> photos) {
		this.photos = photos;
	}

	public void toggleVisibility() {
		this.visible = !this.visible;
	}

	public void setOwner(final User owner) {
		this.owner = owner;
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

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(final List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<ContactRequest> getContactRequest() {
		return this.contactRequests;
	}

	public void setContactRequest(final List<ContactRequest> contactRequests) {
		this.contactRequests = contactRequests;
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

	public void reserve() {
		this.reserved = true;
	}

	public void unreserve() {
		this.reserved = false;
	}
}
