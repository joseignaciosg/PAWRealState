package ar.edu.itba.it.paw.domain.repositories.api;

import java.io.*;
import java.util.*;

import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.*;

public final class PropertySearch implements Serializable {

	public enum Order {
		ASC, DESC
	}

	private Operation operation;
	private Type type;
	private Integer priceLow;
	private Integer priceHigh;
	private Integer page;
	private Integer quant;
	private Order order;
	private List<Service> services;
	private List<RoomSearch> rooms;
	private Boolean visibility;
	private User user;

	public PropertySearch(final Operation operation, final Type type,
			final Integer priceLow, final Integer priceHigh,
			final Integer page, final Integer quant, final Order order,
			final List<Service> services, final List<RoomSearch> roomSearch,
			final boolean visibility, final User user) {
		this.operation = operation;
		this.type = type;
		this.priceLow = priceLow;
		this.priceHigh = priceHigh;
		this.page = page;
		this.quant = quant;
		this.order = order;
		this.services = services;
		this.rooms = roomSearch;
		this.visibility = visibility;
		this.user = user;

	}

	public PropertySearch(final Operation rent) {
		this(rent, null, null, null, null, 2, Order.DESC, null, null, true,
				null);
	}

	public Operation getOperation() {
		return this.operation;
	}

	public Type getType() {
		return this.type;
	}

	public Integer getPriceLow() {
		return this.priceLow;
	}

	public Integer getPriceHigh() {
		return this.priceHigh;
	}

	public Integer getPage() {
		return this.page;
	}

	public Integer getQuant() {
		return this.quant;
	}

	public Order getOrder() {
		return this.order;
	}

	public Boolean getVisibility() {
		return this.visibility;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public List<RoomSearch> getRooms() {
		return this.rooms;
	}

	public User getUser() {
		return this.user;
	}

}
