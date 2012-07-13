package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.feedback.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.*;
import org.apache.wicket.validation.validator.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Currency;
import ar.edu.itba.it.paw.domain.entities.Property.Operation;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.entities.Property.Type;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.base.*;

@SuppressWarnings("serial")
public class PropertySavePage extends SecuredPage {

	@SpringBean
	UserRepository users;

	@SpringBean
	HibernatePropertyRepository properties;

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

	// Wicket expects this ones to be here, but it doesn't use them

	private IModel<Property> model;

	private AjaxRoomListPanel roomsPanel;

	public PropertySavePage(final Property property) {

		this.model = new EntityModel<Property>(Property.class, property);

		this.populateForm();

		this.loadForm();
	}

	private void populateForm() {
		if (this.model.getObject() != null) {
			this.property_type = this.model.getObject().getType();
			this.property_operation = this.model.getObject().getOperation();
			this.property_address = this.model.getObject().getAddress();
			this.property_neighborhood = this.model.getObject()
					.getNeighborhood();
			this.property_price = this.model.getObject().getPrice();
			this.property_currency = this.model.getObject().getCurrency();
			this.property_spaces = this.model.getObject().getSpaces();
			this.property_covered_area = this.model.getObject()
					.getCoveredArea();
			this.property_free_area = this.model.getObject().getFreeArea();
			this.property_age = this.model.getObject().getAge();
			this.property_description = this.model.getObject().getDescription();
			this.property_service = this.model.getObject().getServices();
		}
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		this.populateForm();
	}

	private void loadForm() {
		final Form<PropertySavePage> propertyForm = new Form<PropertySavePage>(
				"property_form", new CompoundPropertyModel<PropertySavePage>(
						this));

		propertyForm.add(new FeedbackPanel("feedback_panel",
				new ContainerFeedbackMessageFilter(propertyForm)));

		propertyForm.add(new DropDownChoice<Type>("property_type", Arrays
				.asList(Type.values()), new EnumChoiceRenderer<Type>())
				.setRequired(true));

		propertyForm.add(new DropDownChoice<Operation>("property_operation",
				Arrays.asList(Operation.values()),
				new EnumChoiceRenderer<Operation>()).setRequired(true));

		propertyForm.add(new TextField<String>("property_address")
				.setRequired(true));
		propertyForm.add(new TextField<String>("property_neighborhood")
				.setRequired(true));
		propertyForm.add(new TextField<Integer>("property_price").add(
				new MinimumValidator<Integer>(0)).setRequired(true));

		propertyForm.add(new DropDownChoice<Currency>("property_currency",
				Arrays.asList(Currency.values()),
				new EnumChoiceRenderer<Currency>()).setRequired(true));

		propertyForm.add(new TextField<Integer>("property_spaces").add(
				new MinimumValidator<Integer>(0)).setRequired(true));
		propertyForm.add(new TextField<Integer>("property_covered_area").add(
				new MinimumValidator<Integer>(0)).setRequired(true));
		propertyForm.add(new TextField<Integer>("property_free_area").add(
				new MinimumValidator<Integer>(0)).setRequired(true));
		propertyForm.add(new TextField<Integer>("property_age").add(
				new MinimumValidator<Integer>(0)).setRequired(true));
		propertyForm.add(new TextField<String>("property_description")
				.setRequired(true));

		propertyForm.add(new CheckBoxMultipleChoice<Service>(
				"property_service", Arrays.asList(Service.values()),
				new EnumChoiceRenderer<Service>()));

		final String code = (this.model.getObject() == null) ? "label_property_create"
				: "label_property_edit";

		propertyForm.add(new Button("property_submit", Model.of(this
				.getString(code))) {
			@Override
			public void onSubmit() {

				Property toPersist = null;

				final List<Room> rooms = PropertySavePage.this.roomsPanel
						.getRooms();

				final User owner = PropertySavePage.this.users
						.getByName(RealStateSession.get().getUsername());

				if (PropertySavePage.this.model.getObject() == null) {
					toPersist = new Property(
							PropertySavePage.this.property_type,
							PropertySavePage.this.property_operation,
							PropertySavePage.this.property_neighborhood,
							PropertySavePage.this.property_address,
							PropertySavePage.this.property_price,
							PropertySavePage.this.property_spaces,
							PropertySavePage.this.property_covered_area,
							PropertySavePage.this.property_free_area,
							PropertySavePage.this.property_age,
							PropertySavePage.this.property_service, rooms,
							PropertySavePage.this.property_description, owner,
							PropertySavePage.this.property_currency);

					PropertySavePage.this.properties.save(toPersist);

				} else {

					PropertySavePage.this.model.detach();

					toPersist = PropertySavePage.this.model.getObject();
					if (owner.equals(toPersist.getOwner())) {
						toPersist.setType(PropertySavePage.this.property_type);
						toPersist
								.setOperation(PropertySavePage.this.property_operation);
						toPersist
								.setNeighborhood(PropertySavePage.this.property_neighborhood);
						toPersist
								.setAddress(PropertySavePage.this.property_address);
						toPersist
								.setPrice(PropertySavePage.this.property_price);
						toPersist
								.setSpaces(PropertySavePage.this.property_spaces);
						toPersist
								.setCoveredArea(PropertySavePage.this.property_covered_area);
						toPersist
								.setFreeArea(PropertySavePage.this.property_free_area);
						toPersist.setAge(PropertySavePage.this.property_age);
						toPersist.getServices().clear();
						for (final Service r : PropertySavePage.this.property_service) {
							toPersist.getServices().add(r);
						}

						toPersist.clearRooms();
						for (final Room r : rooms) {
							toPersist.addRoom(r);
						}
						toPersist
								.setDescription(PropertySavePage.this.property_description);
						toPersist
								.setCurrency(PropertySavePage.this.property_currency);
					} else {
						this.setResponsePage(InvalidPermissionPage.class);
						return;
					}
				}

				this.setResponsePage(new PropertyUserPage());
			}
		});

		if (this.model.getObject() != null) {
			propertyForm.add(this.roomsPanel = new AjaxRoomListPanel(
					"property_rooms", propertyForm, this.model.getObject()
							.getRooms()));
		} else {

			propertyForm.add(this.roomsPanel = new AjaxRoomListPanel(
					"property_rooms", propertyForm));
		}
		this.add(propertyForm);
	}
}
