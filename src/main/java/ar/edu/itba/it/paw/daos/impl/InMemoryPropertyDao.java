package ar.edu.itba.it.paw.daos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;
import ar.edu.itba.it.paw.model.entities.Property.Operation;
import ar.edu.itba.it.paw.model.entities.Property.Type;
import ar.edu.itba.it.paw.model.services.PropertyService.Order;

public class InMemoryPropertyDao implements PropertyDao {

	List<Property> data;

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

	public List<Property> getAll(final Operation op, final Type type,
			final int pricelow, final int pricehigh, final int page,
			final int quant, final Order order) {

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
			if ((p.getVisible() == true) && oplist.contains(p)
					&& typelist.contains(p) && pricelowlist.contains(p)
					&& pricehighlist.contains(p)) {
				ans.add(p);
			}

		}

		// ordering
		if (order == null || order.equals(Order.ASC)) {
			Collections.sort(ans, new PriceASCComparator<Property>());
		} else {
			Collections.sort(ans, new PriceDESCComparator<Property>());
		}

		// pagination
		final int start = Math.min(ans.size(), Math.abs(page * quant));
		ans.subList(0, start).clear();
		final int size = ans.size();
		final int end = Math.min(quant, size);
		ans.subList(end, size).clear();

		return ans;
	}
}

class PriceASCComparator<Property> implements Comparator<Property> {

	public int compare(final Object p1, final Object p2) {

		final int price1 = ((ar.edu.itba.it.paw.model.entities.Property) p1)
				.getPrice();
		final int price2 = ((ar.edu.itba.it.paw.model.entities.Property) p2)
				.getPrice();

		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}
}

class PriceDESCComparator<Property> implements Comparator<Property> {

	public int compare(final Object p1, final Object p2) {

		final int price1 = ((ar.edu.itba.it.paw.model.entities.Property) p1)
				.getPrice();
		final int price2 = ((ar.edu.itba.it.paw.model.entities.Property) p2)
				.getPrice();

		if (price1 < price2) {
			return 1;
		} else if (price1 > price2) {
			return -1;
		} else {
			return 0;
		}
	}
}
