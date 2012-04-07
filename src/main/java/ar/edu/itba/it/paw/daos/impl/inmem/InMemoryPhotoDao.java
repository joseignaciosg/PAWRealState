package ar.edu.itba.it.paw.daos.impl.inmem;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.model.entities.Photo;

public class InMemoryPhotoDao implements PhotoDao {

	List<Photo> photos;

	public InMemoryPhotoDao(final List<Photo> photos) {
		this.photos = photos;
	}

	public List<Photo> getAll() {
		return this.photos;
	}

	public Photo getById(final Integer id) {

		for (int i = 0; i < this.photos.size(); i++) {
			if (id == this.photos.get(i).getId()) {
				return this.photos.get(i);
			}
		}
		return null;
	}

	public boolean delete(final Photo obj) {
		if (this.photos.contains(obj)) {
			this.photos.remove(obj);
			return true;
		}
		return false;
	}

	public boolean saveOrUpdate(final Photo obj) {

		if (!this.photos.contains(obj)) {
			return this.photos.add(obj);
		} else {
			if (obj.isDirty()) {
				this.photos.remove(obj);
				obj.setDirty(false);
				return this.photos.add(obj);
			} else {
				return false;
			}
		}
	}

	public List<Photo> getByPropertyId(final Integer id) {
		final List<Photo> ans = new ArrayList<Photo>();
		for (final Photo p : this.photos) {
			if (p.getPropertyId() == id) {
				ans.add(p);
			}
		}
		return ans;
	}

}
