package ar.edu.itba.it.paw.web.command;

import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.services.PropertyService.Order;

public class SearchForm {

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

	public SearchForm() {
	}

	public SearchForm(final Operation operation, final Type type,
			final Order order, final Integer pricelow, final Integer pricehigh,
			final Integer quant, final Integer page) {
		this.operation = operation;
		this.type = type;
		this.order = order;
		this.pricelow = pricelow;
		this.pricehigh = pricehigh;
		this.quant = quant;
		this.page = page;
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

}
