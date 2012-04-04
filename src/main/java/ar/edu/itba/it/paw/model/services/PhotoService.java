package ar.edu.itba.it.paw.model.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.User;
import ar.edu.itba.it.paw.model.services.utils.ServiceUtils;

public class PhotoService {

	private PhotoDao photoDao;

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

	public boolean savePhoto(final byte[] data, final int propertyId,
			final User user, final List<String> errors) {
		ServiceUtils.validateNotNull(data, "Se debe adjuntar una imágen",
				errors);
		ServiceUtils.validateNotNull(data, "EL ID de la propiedad es inválida",
				errors);

		// TODO: Validate ownership of propertyId

		if (errors.size() > 0) {
			return false;
		}

		final Photo p = new Photo(null, data, "jpeg", propertyId);

		this.photoDao.saveOrUpdate(p);

		return !p.isNew();
	}
}