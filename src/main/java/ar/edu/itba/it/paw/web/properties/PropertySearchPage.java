package ar.edu.itba.it.paw.web.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.Property.Currency;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch;
import ar.edu.itba.it.paw.domain.repositories.api.PropertySearch.Order;
import ar.edu.itba.it.paw.web.base.BasePage;
import ar.edu.itba.it.paw.web.utils.customcolumns.ClickableColumn;
import ar.edu.itba.it.paw.web.utils.customcolumns.PhotoPropertyColumn;

@SuppressWarnings("serial")
public class PropertySearchPage extends BasePage {
	@SpringBean
	PropertyRepository properties;

	private transient Integer search_form_pricelow;
	private transient Integer search_form_pricehigh;
	private transient Currency search_form_pricecurrency;
	private transient Type search_form_type;
	private transient Operation search_form_operation;
	private transient Order search_form_order;

	public PropertySearchPage() {
		this(new PropertySearch());

	}

	public PropertySearchPage(final PropertySearch search) {

		final IModel<PropertySearchPage> model = new CompoundPropertyModel<PropertySearchPage>(
				this);

		this.populateFormWithSearch(search);

		this.renderForm(search, model);

		this.buildDataTable(search);
	}

	private void renderForm(final PropertySearch search,
			final IModel<PropertySearchPage> model) {
		final Form<PropertySearchPage> f = new Form<PropertySearchPage>(
				"search_form", model) {

			@Override
			public void onSubmit() {

				final Integer priceLow = (PropertySearchPage.this.search_form_pricelow == null) ? search
						.getPriceLow()
						: PropertySearchPage.this.search_form_pricelow;
				final Integer priceHigh = (PropertySearchPage.this.search_form_pricehigh == null) ? search
						.getPriceHigh()
						: PropertySearchPage.this.search_form_pricehigh;
				final Currency currency = (PropertySearchPage.this.search_form_pricecurrency == null) ? search
						.getCurrency()
						: PropertySearchPage.this.search_form_pricecurrency;

				final Type type = (PropertySearchPage.this.search_form_type == null) ? search
						.getType() : PropertySearchPage.this.search_form_type;

				final Operation operation = (PropertySearchPage.this.search_form_operation == null) ? search
						.getOperation()
						: PropertySearchPage.this.search_form_operation;

				final Order order = (PropertySearchPage.this.search_form_order == null) ? search
						.getOrder() : PropertySearchPage.this.search_form_order;

				search.setOperation(operation);
				search.setOrder(order);
				search.setCurrency(currency);
				search.setType(type);
				search.setPriceHigh(priceHigh);
				search.setPriceLow(priceLow);

				this.setResponsePage(PropertySearchPage.this);
			}
		};

		f.add(new FeedbackPanel("feedback_panel"));
		f.add(new TextField<Integer>("search_form_pricelow"));
		f.add(new TextField<Integer>("search_form_pricehigh"));

		f.add(new DropDownChoice<Currency>("search_form_pricecurrency", Arrays
				.asList(Currency.values()), new EnumChoiceRenderer<Currency>()));

		f.add(new DropDownChoice<Type>("search_form_type", Arrays.asList(Type
				.values()), new EnumChoiceRenderer<Type>()));

		f.add(new DropDownChoice<Operation>("search_form_operation", Arrays
				.asList(Operation.values()),
				new EnumChoiceRenderer<Operation>()));

		f.add(new DropDownChoice<Order>("search_form_order", Arrays
				.asList(Order.values()), new EnumChoiceRenderer<Order>())
				.setNullValid(false));

		final Button doSearch = new Button("search_form_submit");

		doSearch.add(new AttributeAppender("value", Model.of(this
				.getString("label_search_submit"))));

		f.add(doSearch);

		this.add(f);
	}

	private void populateFormWithSearch(final PropertySearch search) {
		this.search_form_pricelow = search.getPriceLow();
		this.search_form_pricehigh = search.getPriceHigh();
		this.search_form_pricecurrency = search.getCurrency();
		this.search_form_type = search.getType();
		this.search_form_operation = search.getOperation();
		this.search_form_order = search.getOrder();
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
				.getString("property_currency")), "currency"));

		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_price")), "price"));

		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_sq_meter_price")), "squareMeterPrice"));

		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_type")), "type") {
			@Override
			public void populateItem(final Item<ICellPopulator<Property>> item,
					final String componentId, final IModel<Property> rowModel) {
				item.add(new Label(componentId, PropertySearchPage.this
						.getString("Type." + rowModel.getObject().getType())));
			}
		});

		columns.add(new PropertyColumn<Property>(Model.of(this
				.getString("property_operation")), "operation") {
			@Override
			public void populateItem(final Item<ICellPopulator<Property>> item,
					final String componentId, final IModel<Property> rowModel) {
				item.add(new Label(componentId, PropertySearchPage.this
						.getString("Operation."
								+ rowModel.getObject().getOperation())));
			}
		});

		columns.add(new ClickableColumn<Property>(Model.of(this
				.getString("property_actions")), Model.of("Ver esta propiedad")) {
			@Override
			protected void onClick(final Link<Void> owner,
					final IModel<Property> clicked) {

				owner.setResponsePage(new PropertyPage(clicked.getObject()));
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
