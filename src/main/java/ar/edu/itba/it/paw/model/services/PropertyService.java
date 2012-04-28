package ar.edu.itba.it.paw.model.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.daos.api.UserDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.Services;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.utils.ServiceUtils;

public class PropertyService {

	public enum Order {
		ASC, DESC
	}

	private PropertyDao propertyDao;
	private UserDao userDao;

	public PropertyService(final PropertyDao dao, final UserDao userDao) {
		this.propertyDao = dao;
		this.userDao = userDao;
	}

	public List<Property> advancedSearch(final Operation op, final Type type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, final Order order) {
		List<Property> ans = null;
		ans = this.propertyDao.getAll(op, type, pricelow, pricehigh, page,
				quant, order, true);
		return ans;
	}

	public Property getPropertyByID(final Integer ID, final List<String> errors) {

		Property ans = null;
		ans = this.propertyDao.getById(ID);
		if (ID == null || ans == null) {
			errors.add("No existe la propiedad solicitada");
			return null;
		}
		return ans;
	}

	public boolean deleteProperty(final Integer id, final User user,
			final List<String> errors) {

		ServiceUtils.validateNotNull(id, "El id debe ser válido", errors);
		ServiceUtils.validateNotNull(user,
				"El usuario logueado debe ser válido", errors);

		final Property property = this.propertyDao.getById(id);

		ServiceUtils.validateNotNull(property,
				"La propiedad debe existir para poder ser eliminada", errors);

		boolean belongs = false;
		for (final Property prop : user.getProperties()) {
			if (prop.getId().equals(property.getId())) {
				belongs = true;
			}
		}
		if (!belongs) {
			errors.add("El usuario debe ser dueño de la propiedad para poder eliminarla");
		}

		if (errors.size() > 0) {
			return false;
		}

		return this.propertyDao.delete(property);
	}

	public boolean toggleVisibility(final Integer propertyId,
			final List<String> errors) {

		ServiceUtils.validateNotNull(propertyId, "El ID debe ser no nulo",
				errors);

		if (errors.size() > 0) {
			return false;
		}

		final Property prop = this.propertyDao.getById(propertyId);

		ServiceUtils.validateNotNull(prop, "La propiedad debe existir", errors);

		if (errors.size() > 0) {
			return false;
		}

		prop.setVisible(!prop.getVisible());

		return this.propertyDao.saveOrUpdate(prop);
	}

	public boolean saveProperty(final String operationStr,
			final String typeStr, final String neighborhood,
			final String address, final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service,
			final String description, final List<String> errors,
			final User owner) {
		return this.saveProperty(operationStr, typeStr, neighborhood, address,
				price, spaces, coveredArea, freeArea, age, service,
				description, errors, owner, null);
	}

	public boolean saveProperty(final String operationStr,
			final String typeStr, final String neighborhood,
			final String address, final Integer price, final Integer spaces,
			final Integer coveredArea, final Integer freeArea,
			final Integer age, final Services service,
			final String description, final List<String> errors,
			final User owner, final Integer id) {

		final Operation operation = Operation.valueOf(operationStr);
		final Type type = Type.valueOf(typeStr);

		ServiceUtils.validateNotNull(operation, "Operación inválida", errors);
		ServiceUtils.validateNotNull(type, "Tipo inválido", errors);
		ServiceUtils.validateNotNull(neighborhood, "Debe ingresar el barrio",
				errors);
		ServiceUtils.validateNotNull(address, "Debe ingresar la dirección",
				errors);

		ServiceUtils.validateNotNegative(price, "El precio debe ser positivo",
				errors);
		ServiceUtils.validateNotNull(price, "Debe ingresar el precio", errors);
		ServiceUtils.validateNotNegative(spaces,
				"La cantidad de ambientes debe ser positivo", errors);
		ServiceUtils.validateNotNull(spaces,
				"Debe ingresar la cantidad de ambientes", errors);
		ServiceUtils.validateNotNull(coveredArea,
				"Debe ingresar el área cubierta", errors);
		ServiceUtils.validateNotNegative(coveredArea,
				"Debe ingresar un área cubierta positiva", errors);
		ServiceUtils.validateNotNull(freeArea,
				"Debe ingresar el área descubierta", errors);
		ServiceUtils.validateNotNegative(freeArea,
				"Debe ingresar un área descubierta positiva", errors);
		ServiceUtils.validateNotNegative(age, "La edad debe ser positiva",
				errors);
		ServiceUtils
				.validateNotNull(age, "Debe ingresar la antiguedad", errors);
		ServiceUtils.validateNotNull(service, "Debe ingresar los servicios",
				errors);
		ServiceUtils.validateNotNull(description,
				"Debe ingresar la descripción", errors);

		if (errors.size() > 0) {
			return false;
		}

		Property prop = null;
		if (id == null) {
			prop = new Property(type, operation, neighborhood, address, price,
					spaces, coveredArea, freeArea, age, service, description,
					owner);
		} else {
			prop = this.propertyDao.getById(id);

			prop.setType(type);
			prop.setOperation(operation);
			prop.setNeighborhood(neighborhood);
			prop.setAddress(address);
			prop.setPrice(price);
			prop.setSpaces(spaces);
			prop.setCoveredArea(coveredArea);
			prop.setFreeArea(freeArea);
			prop.setAge(age);
			prop.setService(service);
			prop.setDescription(description);

			prop.setDirty(true);
		}

		this.propertyDao.saveOrUpdate(prop);

		return true;
	}
}
