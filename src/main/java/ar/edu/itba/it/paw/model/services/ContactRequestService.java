package ar.edu.itba.it.paw.model.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.services.utils.ServiceUtils;

public class ContactRequestService {

	private ContactRequestDao contactDao;

	public ContactRequestService(final ContactRequestDao contactDao) {
		this.contactDao = contactDao;
	}

	public ContactRequest getContactRequestByID(final Integer ID,
			final List<String> errors) {

		ContactRequest ans = null;
		ans = this.contactDao.getById(ID);
		if (ID == null || ans == null) {
			errors.add("No existe la propiedad solicitada");
			return null;
		}
		return ans;
	}

	public boolean saveContactRequest(final Integer iD, final String name,
			final String email, final String telephone,
			final String description, final Property propRefered,
			final List<String> errors) {

		ServiceUtils.validateNotNull(name, "Tipo inválido", errors);
		ServiceUtils.validateNotNull(email, "Debe ingresar el barrio", errors);
		ServiceUtils.validateNotNull(telephone, "Debe ingresar el barrio",
				errors);

		if (errors.size() > 0) {
			return false;
		}
		final ContactRequest contact;

		if (iD == null) {
			contact = new ContactRequest(iD, name, email, telephone,
					description, propRefered);
		} else {
			contact = this.contactDao.getById(iD);
			contact.setName(name);
			contact.setEmail(email);
			contact.setTelephone(telephone);
			contact.setDescription(description);
			contact.setPropRefered(propRefered);
			contact.setDirty(true);
		}

		this.contactDao.saveOrUpdate(contact);

		return true;
	}

}
