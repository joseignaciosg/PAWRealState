package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.List;

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
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionOfElements;

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
		TENIS, SECURITY, LAUNDRY, SOLARIUM, CABLE, PHONE, SWIMMING, SALON, PADDLE, QUINCHO
	}

	@CollectionOfElements
	@Cascade(value = { CascadeType.ALL })
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

	@OneToMany(mappedBy = "property")
	private List<Photo> photos = new ArrayList<Photo>();

	@OneToMany(mappedBy = "property")
	private List<Room> rooms = new ArrayList<Room>();

	private Boolean visible;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;

	public Property() {

	}

	/**
	 * @deprecated Use {@link
	 *             #Property(Type,Operation,String,String,Integer,Integer,
	 *             Integer,Integer,Integer,List<String>,List,String,User)}
	 *             instead
	 */
	@Deprecated
	public Property(final Type type, final Operation operation,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final List<Service> services,
			final String description, final User owner) {
		this(type, operation, neighborhood, address, price, spaces,
				coveredArea, freeArea, age, services, null, description, owner);
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

	public void setServices(final List<Service> services) {
		this.services = services;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(final List<Room> rooms) {
		this.rooms = rooms;
	}

}
