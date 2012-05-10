package ar.edu.itba.it.paw.domain.repositories.api;

import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;

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

	public PropertySearch(final Operation operation, final Type type,
			final Integer priceLow, final Integer priceHigh,
			final Integer page, final Integer quant, final Order order) {
		this.operation = operation;
		this.type = type;
		this.priceLow = priceLow;
		this.priceHigh = priceHigh;
		this.page = page;
		this.quant = quant;
		this.order = order;
	}

	public PropertySearch(final Operation rent) {
		this(rent, null, -1, -1, 0, 2, Order.DESC);
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

}
