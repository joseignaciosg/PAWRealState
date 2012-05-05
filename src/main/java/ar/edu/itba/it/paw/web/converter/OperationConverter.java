package ar.edu.itba.it.paw.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.model.entities.Property.Operation;

@Component
public class OperationConverter implements Converter<String, Operation> {

	public Operation convert(final String operation) {
		if (operation.equals("Sell")) {
			return Operation.SELL;
		} else if (operation.equals("Rent")) {
			return Operation.RENT;
		} else {
			return null;
		}
	}

}
