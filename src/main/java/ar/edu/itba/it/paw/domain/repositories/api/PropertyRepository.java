package ar.edu.itba.it.paw.domain.repositories.api;

import java.util.List;

import javax.persistence.OrderBy;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.entities.User;

public interface PropertyRepository {

	public List<Property> getAll();

	public List<Property> getAll(final Operation op, final Type type,
			final Integer pricelow, final Integer pricehigh,
			final Integer page, final Integer quant, OrderBy order,
			Boolean visible);

	public List<Property> getByUser(User user);
}
