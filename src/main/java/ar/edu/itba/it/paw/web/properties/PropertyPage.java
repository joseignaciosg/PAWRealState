package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.behavior.*;
import org.apache.wicket.feedback.*;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;
import org.apache.wicket.spring.injection.annot.*;
import org.apache.wicket.validation.validator.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.base.*;
import ar.edu.itba.it.paw.web.common.*;

import com.google.code.jqwicket.ui.fancybox.*;

@SuppressWarnings("serial")
public class PropertyPage extends BasePage {

	@SpringBean
	HibernatePropertyRepository repo;

	private transient String contact_form_firstname;
	private transient String contact_form_lastname;
	private transient String contact_form_email;
	private transient String contact_form_phone;
	private transient String contact_form_description;

	public PropertyPage(final Property prop) {

		final EntityModel<Property> modelProp = new EntityModel<Property>(
				Property.class, prop);

		this.basicLabels(modelProp);

		this.agencyLabel(modelProp);

		this.basicInfoLabels(modelProp);

		this.renderLists(modelProp);

		this.renderImageList(modelProp);

		this.renderContactForm(modelProp);
	}

	private void renderContactForm(final EntityModel<Property> modelProp) {
		final IModel<PropertyPage> model = new CompoundPropertyModel<PropertyPage>(
				this);

		final Form<PropertyPage> f = new Form<PropertyPage>("contact_form",
				model) {

			@Override
			public void onSubmit() {
				final ContactRequest request = new ContactRequest(
						model.getObject().contact_form_firstname + " "
								+ model.getObject().contact_form_lastname,
						model.getObject().contact_form_email,
						model.getObject().contact_form_phone,
						model.getObject().contact_form_description,
						modelProp.getObject());

				PropertyPage.this.repo.sendContactRequest(request);
				this.setResponsePage(new ContactRequestDonePage(request));
			}

		};

		f.add(new FeedbackPanel("feedback_panel",
				new ContainerFeedbackMessageFilter(f)));

		f.add(new TextField<String>("contact_form_firstname").setRequired(true));
		f.add(new TextField<String>("contact_form_lastname").setRequired(true));
		f.add(new TextField<String>("contact_form_email").add(
				EmailAddressValidator.getInstance()).setRequired(true));
		f.add(new TextField<String>("contact_form_phone").add(
				TelephoneValidator.getInstance()).setRequired(true));
		f.add(new TextField<String>("contact_form_description"));

		final Button contact = new Button("contact_form_submit");

		contact.add(new AttributeAppender("value", Model.of(this
				.getString("label_contact_submit"))));

		f.add(contact);

		this.add(f);
	}

	private void renderImageList(final EntityModel<Property> modelProp) {
		this.add(new PropertyListView<Photo>("property_image",
				new PropertyModel<List<Photo>>(modelProp, "photos")) {

			@Override
			protected void populateItem(final ListItem<Photo> item) {

				final PageParameters params = new PageParameters();

				params.add("id", item.getModelObject().getId());

				final Image img = new Image("property_image_tag",
						RealStateApp.imageReference, params);

				item.add(img);

				img.add(new AttributeModifier("style", Model
						.of("width:120px; height:90px;")));

				item.add(new AttributeModifier("href", Model.of(this.urlFor(
						RealStateApp.imageReference, params).toString())));
			}

		});

		this.add(new GenericFancyBoxBehavior("a[rel=picture_group]",
				new FancyBoxOptions().transitionIn("none")
						.transitionOut("none").type("image")));
	}

	private void renderLists(final EntityModel<Property> modelProp) {
		this.add(new PropertyListView<Service>("property_service",
				new PropertyModel<List<Service>>(modelProp, "services")) {

			@Override
			protected void populateItem(final ListItem<Service> item) {
				item.add(new Label("property_service_name", Model.of(this
						.getString("Service."
								+ item.getModelObject().toString()))));
			}

		});

		this.add(new PropertyListView<Room>("property_room",
				new PropertyModel<List<Room>>(modelProp, "listView")) {

			@Override
			protected void populateItem(final ListItem<Room> item) {
				item.add(new Label("property_room_name", Model.of(this
						.getString("RoomType."
								+ item.getModelObject().getType().toString())
						+ " " + item.getModelObject().getSize() + " m2")));
			}
		});
	}

	private void basicInfoLabels(final EntityModel<Property> modelProp) {

		this.add(new Label("property_spaces_count",
				new PropertyModel<Property>(modelProp, "spaces")));
		this.add(new Label("property_covered_area",
				new PropertyModel<Property>(modelProp, "coveredArea")));
		this.add(new Label("property_free_area", new PropertyModel<Property>(
				modelProp, "freeArea")));
		this.add(new Label("property_age", new PropertyModel<Property>(
				modelProp, "age")));
		this.add(new Label("property_description", new PropertyModel<Property>(
				modelProp, "description")));
		this.add(new Link<Void>("property_more_of_same_user") {
			@Override
			public void onClick() {
				this.setResponsePage(new PropertySearchPage(new PropertySearch(
						modelProp.getObject().getOwner())));
			}
		});
	}

	private void agencyLabel(final EntityModel<Property> modelProp) {
		if (modelProp.getObject().getOwner() instanceof RealStateAgency) {
			final RealStateAgency agency = (RealStateAgency) modelProp
					.getObject().getOwner();
			this.add(new Label("property_agency_name", Model.of(agency
					.getAgencyName())));

			final PageParameters params = new PageParameters();

			params.add("id", agency.getPhoto().getId());

			this.add(new Image("property_agency_image",
					RealStateApp.imageReference, params));
		} else {
			this.add(new Label("property_agency_name", Model.of(""))
					.setVisible(false));
			this.add(new Image("property_agency_image", Model.of(""))
					.setVisible(false));
		}
	}

	private void basicLabels(final EntityModel<Property> modelProp) {

		this.add(new Label("property_address", new PropertyModel<Property>(
				modelProp, "address")));
		this.add(new Label("property_neighborhood",
				new PropertyModel<Property>(modelProp, "neighborhood")));
		this.add(new Label("property_visit_count", new PropertyModel<Property>(
				modelProp, "visitCount")));
		this.add(new Label("property_type", this.getString("Type."
				+ modelProp.getObject().getType())));
		this.add(new Label("property_operation", this.getString("Operation."
				+ modelProp.getObject().getOperation())));
		this.add(new Label("property_currency", new PropertyModel<Property>(
				modelProp, "currency")));
		this.add(new Label("property_price", new PropertyModel<Property>(
				modelProp, "price")));
		this.add(new Label("property_currency_2", new PropertyModel<Property>(
				modelProp, "currency")));
		this.add(new Label("property_price_m2", new PropertyModel<Property>(
				modelProp, "squareMeterPrice")));
	}
}
