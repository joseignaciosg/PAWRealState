package ar.edu.itba.it.paw.web.command;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;

@Component
public class PropertyForm {

	@NotNull
	private Operation operation;

	@NotNull
	private Type type;

	@NotNull
	@Size(min = 10, max = 150)
	private String neighborhood;

	@NotNull
	@Size(min = 10, max = 150)
	private String address;

	@NotNull
	@Min(0)
	private Integer price;

	@NotNull
	@Min(0)
	private Integer spaces;

	@NotNull
	@Min(0)
	private Integer coveredArea;

	@NotNull
	@Min(0)
	private Integer freeArea;

	@NotNull
	@Min(0)
	private Integer age;

	private Services service;

	@Size(min = 10, max = 150)
	private String description;
	private Integer id;
	private User currentUser;

	public PropertyForm() {
	}

	public PropertyForm(final Operation operation, final Type type,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service,
			final String description, final Integer id, final User currentUser) {
		this.operation = operation;
		this.type = type;
		this.neighborhood = neighborhood;
		this.address = address;
		this.price = price;
		this.spaces = spaces;
		this.coveredArea = coveredArea;
		this.freeArea = freeArea;
		this.age = age;
		this.service = service;
		this.description = description;
		this.id = id;
		this.currentUser = currentUser;
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(final Operation operation) {
		this.operation = operation;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(final Integer price) {
		this.price = price;
	}

	public Integer getSpaces() {
		return this.spaces;
	}

	public void setSpaces(final Integer spaces) {
		this.spaces = spaces;
	}

	public Integer getCoveredArea() {
		return this.coveredArea;
	}

	public void setCoveredArea(final Integer coveredArea) {
		this.coveredArea = coveredArea;
	}

	public Integer getFreeArea() {
		return this.freeArea;
	}

	public void setFreeArea(final Integer freeArea) {
		this.freeArea = freeArea;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	public Services getService() {
		return this.service;
	}

	public void setService(final Services service) {
		this.service = service;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public User getCurrentUser() {
		return this.currentUser;
	}

	public void setCurrentUser(final User currentUser) {
		this.currentUser = currentUser;
	}

}
