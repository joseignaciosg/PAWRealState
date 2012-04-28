package ar.edu.itba.it.paw.daos.impl.inmem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.comparators.ReverseComparator;

import ar.edu.itba.it.paw.daos.api.PhotoDao;
import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Photo;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.entities.utils.EntityUtils;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;
import ar.edu.itba.it.paw.utils.collections.CollectionWithMemory;

public class InMemoryPropertyDao implements PropertyDao {

	private List<Property> data;
	private PhotoDao photoDao;

	public InMemoryPropertyDao(final List<Property> data) {
		this.data = data;
	}

	public Property getById(final Integer id) {
		for (int i = 0; i < this.data.size(); i++) {
			if (id == this.data.get(i).getId()) {
				return this.data.get(i);
			}
		}
		return null;
	}

	public boolean delete(final Property obj) {
		if (EntityUtils.contains(obj.getOwner().getProperties(), obj)) {
			EntityUtils.remove(obj.getOwner().getProperties(), obj);
		}

		if (EntityUtils.contains(this.data, obj)) {
			EntityUtils.remove(this.data, obj);
			return true;
		}
		return false;
	}

	public boolean saveOrUpdate(final Property obj) {

		this.updateReferences(obj);

		if (!EntityUtils.contains(this.data, obj)) {
			obj.setDirty(false);
			return this.data.add(obj);
		} else {
			if (obj.isDirty()) {
				EntityUtils.remove(this.data, obj);
				obj.setDirty(false);

				return this.data.add(obj);
			} else {
				return false;
			}
		}
	}

	private void updateReferences(final Property obj) {
		final CollectionWithMemory<Photo> photosWithDeletions = (CollectionWithMemory<Photo>) obj
				.getPhotos();

		if (photosWithDeletions.isModified()) {
			for (final Photo photo : photosWithDeletions) {
				this.photoDao.saveOrUpdate(photo);
			}

			for (final Photo photo : photosWithDeletions.getDeletedItems()) {
				this.photoDao.delete(photo);
			}
			photosWithDeletions.getDeletedItems().clear();
			photosWithDeletions.setModified(false);
		}

		if (!EntityUtils.contains(obj.getOwner().getProperties(), obj)) {
			obj.getOwner().getProperties().add(obj);
		}

	}

	public List<Property> getAll() {
		return this.data;
	}

	@SuppressWarnings("unchecked")
	public List<Property> getAll(final Operation op, final Type type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, final Order order, final Boolean visible) {

		final List<Property> ans = new ArrayList<Property>();
		final List<Property> oplist = new ArrayList<Property>();
		final List<Property> typelist = new ArrayList<Property>();
		final List<Property> pricelowlist = new ArrayList<Property>();
		final List<Property> pricehighlist = new ArrayList<Property>();

		if (op != null) {
			for (final Property p : this.data) {
				if (p.getOperation().equals(op)) {
					oplist.add(p);
				}
			}
		} else {
			oplist.addAll(this.data);
		}

		if (type != null) {
			for (final Property p : this.data) {
				if (p.getType().equals(type)) {
					typelist.add(p);
				}
			}
		} else {
			typelist.addAll(this.data);
		}

		if (pricelow >= 0) {
			for (final Property p : this.data) {
				if (p.getPrice() >= pricelow) {
					pricelowlist.add(p);
				}
			}
		} else {
			pricelowlist.addAll(this.data);
		}

		if (pricehigh >= 0) {
			for (final Property p : this.data) {
				if (p.getPrice() <= pricehigh) {
					pricehighlist.add(p);
				}
			}
		} else {
			pricehighlist.addAll(this.data);
		}

		for (final Property p : this.data) {
			if ((p.getVisible() == visible || visible == null)
					&& EntityUtils.contains(oplist, p)
					&& EntityUtils.contains(typelist, p)
					&& EntityUtils.contains(pricelowlist, p)
					&& EntityUtils.contains(pricehighlist, p)) {
				ans.add(p);
			}

		}

		// ordering
		if (order == null || order.equals(Order.ASC)) {
			Collections.sort(ans, new PriceASCComparator());
		} else {
			Collections.sort(ans, new ReverseComparator(
					new PriceASCComparator()));
		}

		// pagination
		final int start = Math.min(ans.size(), Math.abs(page * quant));
		ans.subList(0, start).clear();
		final int size = ans.size();
		final int end = Math.min(quant, size);
		ans.subList(end, size).clear();

		return ans;
	}

	public void setPhotoDao(final PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	public List<Property> getByUserId(final int userId) {
		final List<Property> props = new ArrayList<Property>();

		for (final Property property : this.data) {
			if (property.getOwner().getId().equals(Integer.valueOf(userId))) {
				props.add(property);
			}
		}
		return props;
	}
}

class PriceASCComparator implements Comparator<Property> {

	public int compare(final Property p1, final Property p2) {

		final int price1 = p1.getPrice();
		final int price2 = p2.getPrice();

		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}
}