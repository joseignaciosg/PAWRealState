package ar.edu.itba.it.paw.web.converter;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.entities.Property.Operation;

@Component
public class OperationConverter implements IConverter<Operation> {

	public Operation convertToObject(final String operation, final Locale locale) {
		if (operation.toLowerCase().equals("sell")) {
			return Operation.SELL;
		} else if (operation.toLowerCase().equals("rent")) {
			return Operation.RENT;
		} else {
			return null;
		}
	}

	public String convertToString(final Operation value, final Locale locale) {
		return value.toString();
	}

}
