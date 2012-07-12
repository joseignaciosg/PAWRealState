package ar.edu.itba.it.paw.web;

import static ar.edu.itba.it.paw.web.utils.WicketShortcuts.link;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.web.agencies.AgencySearchPage;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.panels.ThumbnailPanel;
import ar.edu.itba.it.paw.web.properties.PropertySearchPage;

@SuppressWarnings("serial")
public class HomePage extends BasePage {

	@SpringBean
	private PropertyRepository repo;

	public HomePage() {

		this.add(link("search_properties", PropertySearchPage.class));
		this.add(link("search_agencies", AgencySearchPage.class));
		this.add(new Link<Void>("all_on_rent") {
			@Override
			public void onClick() {
				this.setResponsePage(new PropertySearchPage(new PropertySearch(
						Operation.RENT)));
			}
		});

		this.add(new Link<Void>("all_on_sale") {
			@Override
			public void onClick() {
				this.setResponsePage(new PropertySearchPage(new PropertySearch(
						Operation.SELL)));
			}
		});

		this.add(new ThumbnailView("rents", new ThumbnailDetachableModel(
				new PropertySearch(Operation.RENT, null, null, null, 0, 2,
						Order.DESC, null, null, true, null, null, false))));

		this.add(new ThumbnailView("sells", new ThumbnailDetachableModel(
				new PropertySearch(Operation.SELL, null, null, null, 0, 2,
						Order.DESC, null, null, true, null, null, false))));

	}

	private class ThumbnailDetachableModel extends
			LoadableDetachableModel<List<Property>> {

		PropertySearch search;

		public ThumbnailDetachableModel(final PropertySearch search) {
			this.search = search;
		}

		@Override
		protected List<Property> load() {
			return HomePage.this.repo.getAll(this.search);
		}

	}

	private static class ThumbnailView extends RefreshingView<Property> {

		private ThumbnailDetachableModel model;

		public ThumbnailView(final String id,
				final ThumbnailDetachableModel model) {
			super(id);
			this.model = model;
		}

		@Override
		protected Iterator<IModel<Property>> getItemModels() {
			final List<IModel<Property>> result = new ArrayList<IModel<Property>>();
			for (final Property d : this.model.load()) {
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
