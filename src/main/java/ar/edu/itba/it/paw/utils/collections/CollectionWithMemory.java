package ar.edu.itba.it.paw.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Collection implementation with memory of the deleted items
 * 
 * @author cris
 * 
 * @param <T>
 *            The kind of item to remember after deletion
 */
public class CollectionWithMemory<T> implements Collection<T> {

	private Collection<T> deletedItems;
	private Collection<T> collection;
	private boolean isModified = false;

	public boolean isModified() {
		return this.isModified;
	}

	public void setModified(final boolean isModified) {
		this.isModified = isModified;
	}

	/**
	 * @param actualCollection
	 *            Collection to have deletion memory on
	 */
	public CollectionWithMemory(final Collection<T> actualCollection) {
		this.deletedItems = new ArrayList<T>();
		this.collection = actualCollection;
	}

	private Collection<T> getCollection() {
		return this.collection;
	}

	public boolean add(final T e) {
		this.isModified = true;
		return this.getCollection().add(e);
	}

	public boolean addAll(final Collection<? extends T> c) {
		this.isModified = true;
		return this.getCollection().addAll(c);
	}

	public void clear() {
		this.getCollection().clear();
	}

	public boolean contains(final Object o) {
		return this.getCollection().contains(o);
	}

	public boolean containsAll(final Collection<?> c) {
		return this.getCollection().containsAll(c);
	}

	public boolean isEmpty() {
		return this.getCollection().isEmpty();
	}

	public Iterator<T> iterator() {
		return this.getCollection().iterator();
	}

	/**
	 * Retains the deleted item so that it can be later accessed
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(final Object o) {
		this.isModified = true;
		this.getDeletedItems().add((T) o);
		return this.getCollection().remove(o);
	}

	/**
	 * Intercepts all the removes
	 */
	public boolean removeAll(final Collection<?> c) {
		boolean b = true;
		for (final Object item : c) {
			b = b && this.remove(item);
		}
		return b;
	}

	/**
	 * Intercepts all the removes
	 */
	public boolean retainAll(final Collection<?> c) {
		boolean b = true;
		final List<Object> toRemove = new ArrayList<Object>();
		for (final Object item : this.collection) {
			if (!c.contains(item)) {
				toRemove.add(item);
			}
		}

		for (final Object item : toRemove) {
			b = b && this.remove(item);
		}
		return b;
	}

	public int size() {
		return this.getCollection().size();
	}

	public Object[] toArray() {
		return this.getCollection().toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(final T[] a) {
		return this.getCollection().toArray(a);
	}

	public Collection<T> getDeletedItems() {
		return this.deletedItems;
	}

}
