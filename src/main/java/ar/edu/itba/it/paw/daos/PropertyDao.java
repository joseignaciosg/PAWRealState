package DAOS;

import java.sql.Connection;
import java.util.List;

import model.ConnectionManager;
import model.Property;

public class PropertyDao implements Dao<Property> {

	private Connection connection = null;
	private ConnectionManager manager = null;

	public PropertyDao(final ConnectionManager manager) {
		this.manager = manager;
		this.connection = manager.getConnection();
	}

	@Override
	public List<Property> getAll() {
		return null;
	}

	@Override
	public Property getById(final String id) {
		return null;
	}

	@Override
	public boolean delete(final Property obj) {
		return false;
	}

	@Override
	public boolean saveOrUpdate(final Property obj) {
		return true;
	}

	public List<Property> getAdvancedSearch(final String operationType,
			final String propertyType, final Double lowerPrice,
			final Double upperPrice) {

		return null;
	}
}
