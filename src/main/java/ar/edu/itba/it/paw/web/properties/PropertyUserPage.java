package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.ajax.*;
import org.apache.wicket.ajax.markup.html.*;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.base.*;

import com.google.code.jqwicket.ui.accordion.*;

@SuppressWarnings("serial")
public class PropertyUserPage extends BasePage {

	@SpringBean
	UserRepository users;

	@SpringBean
	PropertyRepository properties;

	public PropertyUserPage(final User user) {

		final AccordionWebMarkupContainer a1 = new AccordionWebMarkupContainer(
				"properties_accordion");

		final RefreshingView<Property> accordionView = new RefreshingView<Property>(
				"property") {

			IModel<User> userModel = new EntityModel<User>(User.class,
					user.getId());

			PropertyDetachableModel model = new PropertyDetachableModel(
					this.userModel);

			List<IModel<Property>> result;

			@Override
			protected Iterator<IModel<Property>> getItemModels() {
				this.result = new ArrayList<IModel<Property>>();
				for (final Property view : this.model.load()) {
					final IModel<Property> item = new EntityModel<Property>(
							Property.class, view.getId());
					this.result.add(item);
				}
				return this.result.iterator();
			}

			@Override
			protected void populateItem(final Item<Property> item) {

				item.setOutputMarkupId(true);

				final Link<Void> link = new Link<Void>("property_picture_link") {
					@Override
					public void onClick() {
						PropertyUserPage.this.linkToPropertyPage(this, item);
					}
				};

				final PageParameters params = new PageParameters();

				if (item.getModelObject().getPhotos().size() > 0) {
					params.add("id", item.getModelObject().getPhotos().get(0)
							.getId());

					link.add(new Image("property_picture",
							RealStateApp.imageReference, params));

				} else {
					link.setVisible(false);
				}

				item.add(new Label("property_name_header",
						new PropertyModel<String>(item.getModel(), "address")));

				final AjaxFallbackLink<Void> toggleLink = new AjaxFallbackLink<Void>(
						"toggle_link") {

					@Override
					public void onClick(final AjaxRequestTarget target) {
						target.add(this);
						item.getModelObject().toggleVisibility();

					}
				};

				final Link<Void> editlink = new Link<Void>("property_edit_link") {
					@Override
					public void onClick() {
						// Mandar a la pagina de edicion.

					}
				};

				final Link<Void> viewlink = new Link<Void>("property_view_link") {
					@Override
					public void onClick() {
						PropertyUserPage.this.linkToPropertyPage(this, item);
					}
				};

				final AjaxFallbackLink<Void> deletelink = new AjaxFallbackLink<Void>(
						"property_delete_link") {
					@Override
					public void onClick(final AjaxRequestTarget target) {
						target.add(this);

						target.add(item);

						final String username = ((RealStateSession) Session
								.get()).getUsername();

						final User u = PropertyUserPage.this.users
								.getByName(username);

						if (u != null
								&& u.getProperties().contains(
										item.getModelObject())) {
							u.getProperties().remove(item.getModelObject());
							item.add(new AttributeModifier("style", Model
									.of("display:none;")));
						}
					}
				};

				item.add(link);
				item.add(editlink);
				item.add(toggleLink);
				item.add(deletelink);
				item.add(viewlink);

			}
		};

		final Label l = new Label("no_properties_message");

		final int propertiesSize = PropertyUserPage.this.properties.getAll()
				.size();

		accordionView.setVisible(propertiesSize > 0);
		l.setVisible(!accordionView.isVisible());

		a1.add(accordionView);
		this.add(l);
		this.add(a1);

	}

	private class PropertyDetachableModel extends
			LoadableDetachableModel<List<Property>> {

		IModel<User> user;

		public PropertyDetachableModel(final IModel<User> user) {
			this.user = user;
		}

		@Override
		protected List<Property> load() {
			this.user.detach();
			final List<Property> props = (this.user.getObject())
					.getProperties();
			return props;
		}
	}

	private void linkToPropertyPage(final Link<Void> link,
			final Item<Property> item) {
		link.setResponsePage(new PropertyPage(item.getModelObject()));
	}

	private void deleteProperty(final Item<Property> item) {
		this.properties.getAll().remove(item.getModel().getObject());

	}
}
