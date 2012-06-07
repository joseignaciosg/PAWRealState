package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;

@Component
public class OrderConverter implements IConverter<Order> {

	public Order convertToObject(final String order, final Locale locale) {
		if (order.toLowerCase().equals("asc")) {
			return Order.ASC;
		} else if (order.toLowerCase().equals("desc")) {
			return Order.DESC;
		} else {
			return null;
		}
	}

	public String convertToString(final Order value, final Locale locale) {
		return value.toString();
	}

}
