package ar.edu.itba.it.paw.daos.api;

import java.util.List;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;

public interface PropertyDao extends Dao<Property> {

	public List<Property> getAll();

	public List<Property> getAll(final Operation op, final Type type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, Order order, Boolean visible);

}
