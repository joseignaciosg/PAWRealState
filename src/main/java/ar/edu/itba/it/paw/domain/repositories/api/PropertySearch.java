package ar.edu.itba.it.paw.domain.repositories.api;

import java.io.*;
import java.util.*;

import ar.edu.itba.it.paw.domain.entities.Property.Currency;
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
	private Currency currency;

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

	public PropertySearch(final Operation o) {
		this(o, null, null, null, 0, 2, Order.DESC, null, null, true, null);
	}

	public PropertySearch() {
		this(null, null, null, null, 0, 2, Order.DESC, null, null, true, null);
	}

	public PropertySearch(final Integer priceLow, final Integer priceHigh,
			final Currency currency2, final Type type,
			final Operation operation, final Order order, final User user) {
		this(operation, type, priceLow, priceHigh, null, 2, order, null, null,
				true, user);
	}

	public PropertySearch(final User owner) {
		this(null, null, null, null, 0, 2, Order.DESC, null, null, true, owner);
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

	public Currency getCurrency() {
		return this.currency;
	}
}
