package ar.edu.itba.it.paw.utils.collections;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.Factory;

/**
 * <pre>
 * Collection loaded lazily via a Factory. 
 * The factory should create the whole collection.
 * </pre>
 * 
 * @author cris
 * 
 * @param <T>
 */
public class LazyCollection<T> implements Collection<T> {

	private Collection<T> original;
	private Factory collectionFactory;

	/**
	 * @param fact
	 *            Factory which should return a Collection<T>
	 */
	public LazyCollection(final Factory fact) {
		this.collectionFactory = fact;
	}

	@SuppressWarnings("unchecked")
	private Collection<T> getCollection() {
		if (this.original == null) {
			this.original = ((Collection<T>) this.collectionFactory.create());
		}

		return this.original;
	}

	public boolean add(final T e) {
		return this.getCollection().add(e);
	}

	public boolean addAll(final Collection<? extends T> c) {
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

	public boolean remove(final Object o) {
		return this.getCollection().remove(o);
	}

	public boolean removeAll(final Collection<?> c) {
		return this.getCollection().removeAll(c);
	}

	public boolean retainAll(final Collection<?> c) {
		return this.getCollection().retainAll(c);
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

}