package ar.edu.itba.it.paw.web.command;

import java.util.Arrays;
import java.util.List;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.Room;
import ar.edu.itba.it.paw.domain.entities.User;

public class PropertyForm implements BuilderForm<Property> {

	private Operation operation;
	private Type type;
	private String neighborhood;
	private String address;
	private Integer price;
	private Integer spaces;
	private Integer coveredArea;
	private Integer freeArea;
	private Integer age;
	private String description;
	private Service[] services;
	private Room[] rooms;

	private Property property;

	// TODO: Rename a owner, current user no necesariamente lo es
	private User owner;

	public PropertyForm() {
	}

	public PropertyForm(final Operation operation, final Type type,
			final String neighborhood, final String address,
			final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final String description,
			final Property property, final User currentUser,
			final List<Service> services, final List<Room> rooms) {
		this.operation = operation;
		this.type = type;
		this.neighborhood = neighborhood;
		this.address = address;
		this.price = price;
		this.spaces = spaces;
		this.coveredArea = coveredArea;
		this.freeArea = freeArea;
		this.age = age;
		this.description = description;
		this.property = property;
		this.owner = currentUser;
		if (services != null) {
			this.services = services.toArray(new Service[] {});
		}

		if (rooms != null) {
			this.setRooms(rooms.toArray(new Room[] {}));
		}

		if (currentUser == null && this.property != null
				&& this.property.getOwner() != null) {
			this.owner = this.property.getOwner();
		}
	}

	public PropertyForm(final Property property) {
		this(property.getOperation(), property.getType(), property
				.getNeighborhood(), property.getAddress(), property.getPrice(),
				property.getSpaces(), property.getCoveredArea(), property
						.getFreeArea(), property.getAge(), property
						.getDescription(), property, property.getOwner(),
				property.getServices(), property.getRooms());
	}

	public Operation getOperation() {
		return this.operation;
	}

	public Service[] getServices() {
		return this.services;
	}

	public void setServices(final Service[] services) {
		this.services = services;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(final User currentUser) {
		this.owner = currentUser;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

	public Property build() {
		final Property answer = new Property(this.type, this.operation,
				this.neighborhood, this.address, this.price, this.spaces,
				this.coveredArea, this.freeArea, this.age,
				Arrays.asList(this.services), Arrays.asList(this.rooms),
				this.description, this.owner);
		return answer;
	}

	public Property update() {
		final Property answer = this.property;
		answer.setAddress(this.address);
		answer.setDescription(this.description);
		answer.setAge(this.age);
		answer.setCoveredArea(this.coveredArea);
		answer.setFreeArea(this.freeArea);
		answer.setNeighborhood(this.neighborhood);
		answer.setOperation(this.operation);
		answer.setOwner(this.owner);
		answer.setPrice(this.price);
		answer.setSpaces(this.spaces);
		answer.setType(this.type);
		answer.setServices(Arrays.asList(this.services));

		answer.getRooms().clear();
		if (this.rooms != null) {

			for (final Room room : this.rooms) {
				if (room.getType() == null) {
					continue;
				}
				answer.addRoom(room);
			}
		}
		return answer;
	}

	public Room[] getRooms() {
		return this.rooms;
	}

	public void setRooms(final Room[] rooms) {
		this.rooms = rooms;
	}
}
