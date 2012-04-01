package ar.edu.itba.it.paw.daos.api;

import java.util.List;

import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.OPERATION;
import ar.edu.itba.it.paw.model.entities.Property.TYPE;
import ar.edu.itba.it.paw.model.services.PropertyService.ORDER;

public interface PropertyDao extends Dao<Property> {

	public List<Property> getAll();

	public List<Property> getAll(final OPERATION op, final TYPE type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, ORDER order);

}
