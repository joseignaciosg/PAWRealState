package ar.edu.itba.it.paw.daos.impl.inmem;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.ContactRequestDao;
import ar.edu.itba.it.paw.model.entities.ContactRequest;
import ar.edu.itba.it.paw.model.entities.utils.EntityUtils;

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
		return EntityUtils.remove(this.contactrequests, obj);
	}

	public boolean saveOrUpdate(final ContactRequest obj) {
		if (!EntityUtils.contains(this.contactrequests, obj)) {
			obj.setDirty(false);
			return this.contactrequests.add(obj);
		} else {
			if (obj.isDirty()) {
				EntityUtils.remove(this.contactrequests, obj);
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
