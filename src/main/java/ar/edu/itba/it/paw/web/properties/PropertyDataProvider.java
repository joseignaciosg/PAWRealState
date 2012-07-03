package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.extensions.markup.html.repeater.util.*;
import org.apache.wicket.model.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

@SuppressWarnings("serial")
public class PropertyDataProvider extends SortableDataProvider<Property> {

	PropertyRepository properties;

	PropertySearch searchObject;

	public PropertyDataProvider(final PropertySearch searchObject,
			final PropertyRepository repository) {
		this.searchObject = searchObject;
		this.properties = repository;
	}

	public Iterator<? extends Property> iterator(final int first,
			final int count) {
		final int page = first / count;

		final PropertySearch modifiedSearch = new PropertySearch(
				this.searchObject.getOperation(), this.searchObject.getType(),
				this.searchObject.getPriceLow(),
				this.searchObject.getPriceHigh(), page, count,
				this.searchObject.getOrder(), this.searchObject.getServices(),
				this.searchObject.getRooms(),
				this.searchObject.getVisibility(),
				this.searchObject.getCurrency(), this.searchObject.getUser());

		return this.properties.getAll(modifiedSearch).iterator();
	}

	public int size() {
		final PropertySearch modifiedSearch = new PropertySearch(
				this.searchObject.getOperation(), this.searchObject.getType(),
				this.searchObject.getPriceLow(),
				this.searchObject.getPriceHigh(), null, null,
				this.searchObject.getOrder(), this.searchObject.getServices(),
				this.searchObject.getRooms(),
				this.searchObject.getVisibility(),
				this.searchObject.getCurrency(), this.searchObject.getUser());

		// TODO: Improve this
		return this.properties.getAll(modifiedSearch).size();
	}

	public IModel<Property> model(final Property object) {
		return new EntityModel<Property>(Property.class, object);
	}

}
