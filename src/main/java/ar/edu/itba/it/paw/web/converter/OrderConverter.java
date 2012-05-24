package ar.edu.itba.it.paw.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;

@Component
public class OrderConverter implements Converter<String, Order> {

	public Order convert(final String order) {
		if (order.toLowerCase().equals("asc")) {
			return Order.ASC;
		} else if (order.toLowerCase().equals("desc")) {
			return Order.DESC;
		} else {
			return null;
		}
	}

}
