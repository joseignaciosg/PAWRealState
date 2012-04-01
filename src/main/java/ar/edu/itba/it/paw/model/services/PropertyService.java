package ar.edu.itba.it.paw.model.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.OPERATION;
import ar.edu.itba.it.paw.model.entities.Property.TYPE;

public class PropertyService {

	public enum ORDER {
		ASC, DESC
	}

	private PropertyDao dao;

	public PropertyService(final PropertyDao dao) {
		this.dao = dao;
	}

	public List<Property> advacedSearch(final OPERATION op, final TYPE type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, final ORDER order) {
		List<Property> ans = null;
		ans = this.dao
				.getAll(op, type, pricelow, pricehigh, page, quant, order);
		return ans;
	}

	public Property getPropertyByID(final Integer ID, final List<String> errors) {

		Property ans = null;
		ans = this.dao.getById(ID);
		if (ID == null || ans == null) {
			errors.add("No existe la propiedad solicitada");
			return null;
		}
		return ans;
	}

}
