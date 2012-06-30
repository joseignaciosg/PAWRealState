package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.web.base.*;
import ar.edu.itba.it.paw.web.utils.customcolumns.*;

@SuppressWarnings("serial")
public class PropertySearchPage extends BasePage {
	@SpringBean
	PropertyRepository properties;

	public PropertySearchPage() {
		final PropertySearch search = new PropertySearch();

		this.buildDataTable(search);
	}

	private void buildDataTable(final PropertySearch search) {
		final List<IColumn<Property>> columns = new ArrayList<IColumn<Property>>();

		columns.add(new PhotoPropertyColumn<Property>(Model.of(this
				.getString("property_photo")), "photo", 80, 60));

		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_neighborhood")), "neighborhood"));
		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_address")), "address"));

		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_price")), "price"));

		columns.add(new ClickableColumn<Property>(Model.of(this
				.getString("property_actions")), Model.of("Ver esta propiedad")) {
			@Override
			protected void onClick(final Link<Void> owner,
					final IModel<Property> clicked) {
				final PageParameters params = new PageParameters();

				params.add("id", clicked.getObject().getId());

				owner.setResponsePage(PropertyPage.class, params);
			}
		});

		final DefaultDataTable<Property> propertyTable = new DefaultDataTable<Property>(
				"properties", columns, new PropertyDataProvider(search,
						this.properties), search.getQuant());

		propertyTable.add(new AttributeModifier("class", Model
				.of("table table-striped properties-table")));

		this.add(propertyTable);
	}
}
