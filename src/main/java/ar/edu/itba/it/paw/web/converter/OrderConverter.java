package ar.edu.itba.it.paw.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.services.PropertyService.Order;

@Component
public class OrderConverter implements Converter<String, Order> {

	public Order convert(final String order) {
		if (order.equals("Asc")) {
			return Order.ASC;
		} else if (order.equals("Desc")) {
			return Order.DESC;
		} else {
			return null;
		}
	}

}
