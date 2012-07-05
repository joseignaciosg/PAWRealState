package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.feedback.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Currency;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.web.base.*;

@SuppressWarnings("serial")
public class PropertySavePage extends BasePage {

	public PropertySavePage() {
		this(null);
	}

	private transient Type property_type;
	private transient Operation property_operation;
	private transient String property_address;
	private transient String property_neighborhood;
	private transient Integer property_price;
	private transient Currency property_currency;
	private transient Integer property_spaces;
	private transient Integer property_covered_area;
	private transient Integer property_free_area;
	private transient Integer property_age;
	private transient String property_description;
	private transient List<Service> property_service;

	public PropertySavePage(final Property property) {
		final Form<PropertySavePage> propertyForm = new Form<PropertySavePage>(
				"property_form", new CompoundPropertyModel<PropertySavePage>(
						this));

		propertyForm.add(new FeedbackPanel("feedback_panel",
				new ContainerFeedbackMessageFilter(propertyForm)));

		propertyForm.add(new DropDownChoice<Type>("property_type", Arrays
				.asList(Type.values()), new EnumChoiceRenderer<Type>()));

		propertyForm.add(new DropDownChoice<Operation>("property_operation",
				Arrays.asList(Operation.values()),
				new EnumChoiceRenderer<Operation>()));

		propertyForm.add(new TextField<String>("property_address")
				.setRequired(true));
		propertyForm.add(new TextField<String>("property_neighborhood")
				.setRequired(true));
		propertyForm.add(new TextField<Integer>("property_price")
				.setRequired(true));

		propertyForm.add(new DropDownChoice<Currency>("property_currency",
				Arrays.asList(Currency.values()),
				new EnumChoiceRenderer<Currency>()));

		propertyForm.add(new TextField<Integer>("property_spaces")
				.setRequired(true));
		propertyForm.add(new TextField<Integer>("property_covered_area")
				.setRequired(true));
		propertyForm.add(new TextField<Integer>("property_free_area")
				.setRequired(true));
		propertyForm.add(new TextField<Integer>("property_age")
				.setRequired(true));
		propertyForm.add(new TextField<String>("property_description")
				.setRequired(true));

		propertyForm.add(new CheckBoxMultipleChoice<Service>(
				"property_service", Arrays.asList(Service.values()),
				new EnumChoiceRenderer<Service>()));

		this.add(propertyForm);
	}
}
