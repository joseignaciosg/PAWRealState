package ar.edu.itba.it.paw.daos.impl.inmem;

import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;

@Component
public class SearchFormParams {

	private Operation op;
	private Type type;
	private Order order;
	private Integer pricelow;
	private Integer pricehigh;
	private Integer quant;
	private Integer page;

	public SearchFormParams() {
		super();
	}

	public SearchFormParams(final Operation op, final Type type,
			final Order order, final Integer pricelow, final Integer pricehigh,
			final Integer quant, final Integer page) {
		super();
		this.op = op;
		this.type = type;
		this.order = order;
		this.pricelow = pricelow;
		this.pricehigh = pricehigh;
		this.quant = quant;
		this.page = page;
	}

	public Operation getOp() {
		return this.op;
	}

	public void setOp(final Operation op) {
		this.op = op;
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

	public void serOrder(final Order order) {
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
