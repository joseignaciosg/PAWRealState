package ar.edu.itba.it.paw.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy = "property")
	private List<Photo> photos = new ArrayList<Photo>();
	private Boolean visible;

	@ManyToOne
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

	public void setVisible(final Boolean visible) {
		this.visible = visible;
	}

	public void setOwner(final User owner) {
		this.owner = owner;
	}

}
