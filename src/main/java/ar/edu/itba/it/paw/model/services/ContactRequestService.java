package ar.edu.itba.it.paw.model.services;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.Property;

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

	public boolean saveContactRequest(final String name, final String email,
			final String telephone, final String description,
			final Property propRefered, final List<String> errors) {

		if (name.length() == 1) {
			errors.add("Debe Ingresar un Nombre");
		}
		if (email.length() == 0) {
			errors.add("Debe Ingresar un Email");
		} else {
			final String[] splited = email.split("@");
			if (splited.length < 2) {
				errors.add("Debe ingresar un E-mail valido");
			} else {
				if (splited[0].length() == 0 || splited[1].length() == 0) {
					errors.add("Debe ingresar un E-mail valido");
				}
			}
		}
		if (telephone.length() == 0) {
			errors.add("Debe Ingresar un Telefono");
		}

		if (errors.size() > 0) {
			return false;
		}
		final ContactRequest contact;

		contact = new ContactRequest(name, email, telephone, description,
				propRefered);

		this.contactDao.saveOrUpdate(contact);

		return true;
	}
}
