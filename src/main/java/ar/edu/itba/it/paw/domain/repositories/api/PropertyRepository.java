package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.List;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.services.PropertyService.Order;

public interface PropertyRepository {

	public List<Property> getAll();

	public List<Property> getAll(final Operation op, final Type type,
			final Integer pricelow, final Integer pricehigh,
			final Integer page, final Integer quant, Order order,
			Boolean visible);

	public List<Property> getByUser(User user);
}
