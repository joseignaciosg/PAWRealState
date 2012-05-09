package ar.edu.itba.it.paw.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.utils.ServiceUtils;

@Component
public class PhotoService {

	private PhotoDao photoDao;

	@Autowired
	public PhotoService(final PhotoDao dao) {
		this.photoDao = dao;
	}

	public Photo getPhotoById(final Integer id, final List<String> errors) {
		ServiceUtils.validateNotNull(id, "El id no puede ser nulo", errors);

		if (errors.size() > 0) {
			return null;
		}

		return this.photoDao.getById(id);
	}

	public boolean deletePhoto(final int photoId, final User user,
			final List<String> errors) {

		ServiceUtils.validateNotNull(photoId, "EL ID de la foto es inválida",
				errors);

		if (errors.size() > 0) {
			return false;
		}

		final Photo p = this.photoDao.getById(photoId);

		boolean owned = false;
		for (final Property prop : user.getProperties()) {
			if (prop.getId().equals(Integer.valueOf(p.getPropertyId()))) {
				owned = true;
			}
		}

		if (!owned) {
			errors.add("El usuario actual no tiene la propiedad dada");
		}

		if (errors.size() > 0) {
			return false;
		}

		this.photoDao.delete(p);

		return !p.isNew();
	}

	public boolean savePhoto(final byte[] data, final int propertyId,
			final User user, final List<String> errors) {
		ServiceUtils.validateNotNull(data, "Se debe adjuntar una imágen",
				errors);
		ServiceUtils.validateNotNull(propertyId,
				"EL ID de la propiedad es inválida", errors);

		boolean owned = false;
		for (final Property prop : user.getProperties()) {
			if (prop.getId().equals(Integer.valueOf(propertyId))) {
				owned = true;
			}
		}

		if (!owned) {
			errors.add("El usuario actual no tiene la propiedad dada");
		}

		if (errors.size() > 0) {
			return false;
		}

		final Photo p = new Photo(data, "jpeg", propertyId);

		this.photoDao.saveOrUpdate(p);

		return !p.isNew();
	}
}
