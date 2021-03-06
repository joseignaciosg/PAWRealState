package ar.edu.itba.it.paw.domain.repositories.api;

import java.io.Serializable;
import java.util.List;

import ar.edu.itba.it.paw.domain.entities.Property.Currency;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.User;

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
	private Boolean sold;
	private transient User user;
	private Currency currency;

	public PropertySearch(final Operation operation, final Type type,
			final Integer priceLow, final Integer priceHigh,
			final Integer page, final Integer quant, final Order order,
			final List<Service> services, final List<RoomSearch> roomSearch,
			final boolean visibility, final Currency currency, final User user,
			final Boolean sold) {
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
		this.currency = currency;
		this.sold = sold;
	}

	public PropertySearch(final Operation operation, final Type type,
			final Integer priceLow, final Integer priceHigh,
			final Integer page, final Integer quant, final Order order,
			final List<Service> services, final List<RoomSearch> roomSearch,
			final boolean visibility, final Currency currency, final User user) {
		this(operation, type, priceLow, priceHigh, page, quant, order,
				services, roomSearch, visibility, currency, user, false);
	}

	public PropertySearch(final Operation o) {
		this(o, null, null, null, 0, 2, Order.DESC, null, null, true, null,
				null, false);
	}

	public PropertySearch() {
		this(null, null, null, null, 0, 2, Order.DESC, null, null, true, null,
				null, false);
	}

	public PropertySearch(final User owner) {
		this(null, null, null, null, 0, 2, Order.DESC, null, null, true, null,
				owner, false);
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

	public void setOperation(final Operation operation) {
		this.operation = operation;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}

	public void setCurrency(final Currency currency) {
		this.currency = currency;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public void setPriceHigh(final Integer priceHigh) {
		this.priceHigh = priceHigh;
	}

	public void setPriceLow(final Integer priceLow) {
		this.priceLow = priceLow;
	}

	public Boolean getSold() {
		return this.sold;
	}

	public void setSold(final Boolean sold) {
		this.sold = sold;
	}

}
