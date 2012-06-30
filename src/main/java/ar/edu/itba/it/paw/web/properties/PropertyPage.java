package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.list.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.entities.Property.Service;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.base.*;

import com.google.code.jqwicket.ui.fancybox.*;

@SuppressWarnings("serial")
public class PropertyPage extends BasePage {

	public PropertyPage(final Property prop) {

		final EntityModel<Property> modelProp = new EntityModel<Property>(
				Property.class, prop);

		this.basicLabels(modelProp);

		this.agencyLabel(modelProp);

		this.basicInfoLabels(modelProp);

		this.renderLists(modelProp);

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
				// TODO: I18n
				item.add(new Label("property_service_name",
						new PropertyModel<String>(item.getModel(), "enumName")));
			}

		});

		this.add(new PropertyListView<Room>("property_room",
				new PropertyModel<List<Room>>(modelProp, "rooms")) {

			@Override
			protected void populateItem(final ListItem<Room> item) {
				// TODO: Hacer i18n
				item.add(new Label("property_room_name",
						new PropertyModel<String>(item.getModel(), "type")));
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
				// TODO: parameterize this
				this.setResponsePage(new PropertySearchPage());
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
		this.add(new Label("property_type", new PropertyModel<Property>(
				modelProp, "type")));
		this.add(new Label("property_operation", new PropertyModel<Property>(
				modelProp, "operation")));
		this.add(new Label("property_price", new PropertyModel<Property>(
				modelProp, "price")));
	}
}
