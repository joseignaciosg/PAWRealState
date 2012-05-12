package ar.edu.itba.it.paw.web.command;

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

	@Override
	public String toString() {
		return "SearchForm [operation=" + this.operation + ", type="
				+ this.type + ", order=" + this.order + ", pricelow="
				+ this.pricelow + ", pricehigh=" + this.pricehigh + ", quant="
				+ this.quant + ", page=" + this.page + "]";
	}

	private Operation operation;
	private Type type;
	private Order order;
	private Integer pricelow;
	private Integer pricehigh;
	private Integer quant;
	private Integer page;
	private User user;
	private List<Service> services;
	private List<RoomSearch> rooms;

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
		this.setServices(services);
		this.setRooms(rooms);
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(final Operation op) {
		this.operation = op;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(final Order order) {
		this.order = order;
	}

	public Integer getPricelow() {
		return this.pricelow;
	}

	public void setPricelow(final Integer pricelow) {
		this.pricelow = pricelow;
	}

	public Integer getPricehigh() {
		return this.pricehigh;
	}

	public void setPricehigh(final Integer pricehigh) {
		this.pricehigh = pricehigh;
	}

	public Integer getQuant() {
		return this.quant;
	}

	public void setQuant(final Integer quant) {
		this.quant = quant;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage(final Integer page) {
		this.page = page;
	}

	public PropertySearch build() {
		return new PropertySearch(this.operation, this.type, this.pricelow,
				this.pricehigh, this.page, this.quant, this.order, null, null,
				true, this.getUser());
	}

	public List<Service> getServices() {
		return this.services;
	}

	public void setServices(final List<Service> services) {
		this.services = services;
	}

	public List<RoomSearch> getRooms() {
		return this.rooms;
	}

	public void setRooms(final List<RoomSearch> rooms) {
		this.rooms = rooms;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
>>>>>>> d3c16e945c928a22af36d8e5324517f226deda7f
	}
}
