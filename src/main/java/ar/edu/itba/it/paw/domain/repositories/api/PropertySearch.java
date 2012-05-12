package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.List;

import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.Room;

public final class PropertySearch {

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
	private List<Room> rooms;

	public PropertySearch(final Operation operation, final Type type,
			final Integer priceLow, final Integer priceHigh,
			final Integer page, final Integer quant, final Order order,
			final List<Service> services, final List<Room> rooms) {
		this.operation = operation;
		this.type = type;
		this.priceLow = priceLow;
		this.priceHigh = priceHigh;
		this.page = page;
		this.quant = quant;
		this.order = order;
		this.services = services;
		this.rooms = rooms;
	}

	public PropertySearch(final Operation rent) {
		this(rent, null, null, null, null, null, Order.DESC, null, null);
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

	public List<Service> getServices() {
		return this.services;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

}
