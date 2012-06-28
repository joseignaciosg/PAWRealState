package ar.edu.itba.it.paw.web;

import static ar.edu.itba.it.paw.web.utils.WicketShortcuts.*;

import java.util.*;

import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.web.agencies.*;
import ar.edu.itba.it.paw.web.base.*;
import ar.edu.itba.it.paw.web.panels.*;
import ar.edu.itba.it.paw.web.properties.*;

@SuppressWarnings("serial")
public class HomePage extends BasePage {

	public HomePage() {

		this.add(link("search_properties", PropertySearchPage.class));
		this.add(link("search_agencies", AgencySearchPage.class));
		this.add(link("all_on_rent", PropertySearchPage.class));
		this.add(link("all_on_sale", PropertySearchPage.class));

		this.add(new ThumbnailView("rents", new PropertySearch(Operation.RENT,
				null, null, null, 0, 2, Order.DESC, null, null, true, null)));

		this.add(new ThumbnailView("sells", new PropertySearch(Operation.SELL,
				null, null, null, 0, 2, Order.DESC, null, null, true, null)));

	}

	private static class ThumbnailView extends RefreshingView<Property> {

		@SpringBean
		PropertyRepository repo;

		private PropertySearch searchObject;

		public ThumbnailView(final String id, final PropertySearch search) {
			super(id);
			this.searchObject = search;
		}

		@Override
		protected Iterator<IModel<Property>> getItemModels() {
			final List<IModel<Property>> result = new ArrayList<IModel<Property>>();
			for (final Property d : this.repo.getAll(this.searchObject)) {
				result.add(new EntityModel<Property>(Property.class, d));
			}
			return result.iterator();
		}

		@Override
		protected void populateItem(final Item<Property> item) {
			item.add(new ThumbnailPanel("item", item.getModel()));
		}
	}
}
