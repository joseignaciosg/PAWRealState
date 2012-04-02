package ar.edu.itba.it.paw.daos.impl;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;

public class InMemoryContactRequestDao implements ContactRequestDao {

	List<ContactRequest> contactrequests = null;

	public InMemoryContactRequestDao(final List<ContactRequest> data) {
		this.contactrequests = data;

	}

	public ContactRequest getById(final Integer id) {
		for (final ContactRequest c : this.contactrequests) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	public boolean delete(final ContactRequest obj) {
		return this.contactrequests.remove(obj);
	}

	public boolean saveOrUpdate(final ContactRequest obj) {
		if (!this.contactrequests.contains(obj)) {
			return this.contactrequests.add(obj);
		} else {
			if (obj.isDirty()) {
				this.contactrequests.remove(obj);
				obj.setDirty(false);
				return this.contactrequests.add(obj);
			} else {
				return false;
			}
		}
	}

	public List<ContactRequest> getAll() {
		return this.contactrequests;
	}

}
