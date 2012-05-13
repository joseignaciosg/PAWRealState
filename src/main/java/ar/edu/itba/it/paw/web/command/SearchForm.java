package ar.edu.itba.it.paw.web.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.domain.repositories.api.RoomSearch;

@Component
public class SearchForm implements BuilderForm<PropertySearch> {

	private Operation operation;

	private Type type;
	private Order order;
	private Integer pricelow;
	private Integer pricehigh;
	private Integer quant;
	private Integer page;
	private User user;
	private Service[] services = new Service[] {};
	private RoomSearch[] rooms = new RoomSearch[] {};

	public SearchForm() {
	}

	public SearchForm(final Operation operation, final Type type,
			final Order order, final Integer pricelow, final Integer pricehigh,
			final Integer quant, final Integer page,
			final List<Service> services, final List<RoomSearch> rooms,
			final User u) {
		this.operation = operation;
		this.type = type;
		this.order = order;
		this.pricelow = pricelow;
		this.pricehigh = pricehigh;
		this.quant = quant;
		this.page = page;
		this.user = u;
		this.services = services.toArray(new Service[] {});

		this.filterRooms(rooms);
	}

	private void filterRooms(final List<RoomSearch> rooms) {
		final List<RoomSearch> roomList = new ArrayList<RoomSearch>();
		for (final RoomSearch roomSearch : rooms) {
			if (roomSearch.getType() != null) {
				roomList.add(roomSearch);
			}
		}

		this.rooms = roomList.toArray(new RoomSearch[] {});
	}

	public PropertySearch build() {
		this.filterRooms(Arrays.asList(this.rooms));

		return new PropertySearch(this.operation, this.type, this.pricelow,
				this.pricehigh, this.page, this.quant, this.order,
				Arrays.asList(this.services), Arrays.asList(this.rooms), true,
				this.getUser());
	}

	public Operation getOperation() {
		return this.operation;
	}

	public Order getOrder() {
		return this.order;
	}

	public Integer getPage() {
		return this.page;
	}

	public Integer getPricehigh() {
		return this.pricehigh;
	}

	public Integer getPricelow() {
		return this.pricelow;
	}

	public Integer getQuant() {
		return this.quant;
	}

	public RoomSearch[] getRooms() {
		return this.rooms;
	}

	public Service[] getServices() {
		return this.services;
	}

	public Type getType() {
		return this.type;
	}

	public User getUser() {
		return this.user;
	}

	public void setOperation(final Operation op) {
		this.operation = op;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}

	public void setPage(final Integer page) {
		this.page = page;
	}

	public void setPricehigh(final Integer pricehigh) {
		this.pricehigh = pricehigh;
	}

	public void setPricelow(final Integer pricelow) {
		this.pricelow = pricelow;
	}

	public void setQuant(final Integer quant) {
		this.quant = quant;
	}

	public void setRooms(final RoomSearch[] rooms) {
		this.rooms = rooms;
	}

	public void setServices(final Service[] services) {
		this.services = services;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SearchForm [operation=" + this.operation + ", type="
				+ this.type + ", order=" + this.order + ", pricelow="
				+ this.pricelow + ", pricehigh=" + this.pricehigh + ", quant="
				+ this.quant + ", page=" + this.page + ", user=" + this.user
				+ ", services=" + this.services + ", rooms=" + this.rooms + "]";
	}
}
