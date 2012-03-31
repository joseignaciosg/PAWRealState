package ar.edu.itba.it.paw.daos.impl;

import java.util.List;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;

public class InMemoryPropertyDao implements PropertyDao {

	List<Property> data;

	public InMemoryPropertyDao(final List<Property> data) {
		this.data = data;
	}

	public Property getById(final Integer id) {
		for (int i = 0; i < this.data.size(); i++) {
			if (id == this.data.get(i).getID()) {
				return this.data.get(i);
			}
		}
		return null;
	}

	public boolean delete(final Property obj) {
		if (this.data.contains(obj)) {
			this.data.remove(obj);
			return true;
		}
		return false;
	}

	public boolean saveOrUpdate(final Property obj) {
		if (!this.data.contains(obj)) {
			obj.setDirty(false);
			return this.data.add(obj);
		} else {
			if (obj.isDirty()) {
				this.data.remove(obj);
				obj.setDirty(false);
				return this.data.add(obj);
			} else {
				return false;
			}
		}
	}

	public List<Property> getAll() {
		return this.data;
	}

}
