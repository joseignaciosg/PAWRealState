package ar.edu.itba.it.paw.web.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.State;
import ar.edu.itba.it.paw.domain.entities.User;
import ar.edu.itba.it.paw.domain.repositories.api.PropertyRepository;
import ar.edu.itba.it.paw.domain.repositories.api.UserRepository;
import ar.edu.itba.it.paw.web.RealStateApp;
import ar.edu.itba.it.paw.web.RealStateSession;
import ar.edu.itba.it.paw.web.base.BasePage;

import com.google.code.jqwicket.ui.accordion.AccordionWebMarkupContainer;

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
				"propertyModel") {

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

				final MarkupContainer unreserved_link = new WebMarkupContainer(
						"unreserved_link");

				final Link<Void> reservedLink = new Link<Void>("reserved_link") {

					@Override
					public void onClick() {
						final boolean isReserved = item.getModelObject()
								.isReserved();
						if (isReserved) {
							item.getModelObject().unreserve();
						} else {
							item.getModelObject().reserve();
						}

						this.setVisible(isReserved);
						unreserved_link.setVisible(!isReserved);

					}
				};

				final Link<Void> unreservedLink = new Link<Void>(
						"unreserved_link") {

					@Override
					public void onClick() {
						final boolean isReserved = item.getModelObject()
								.isReserved();
						if (isReserved) {
							item.getModelObject().unreserve();
						} else {
							item.getModelObject().reserve();
						}
						reservedLink.setVisible(isReserved);
						this.setVisible(!isReserved);

					}
				};

				final MarkupContainer unsold_link = new WebMarkupContainer(
						"unsold_link");

				final Link<Void> soldLink = new Link<Void>("sold_link") {

					@Override
					public void onClick() {
						final boolean isSold = item.getModelObject().isSold();
						item.getModelObject().toggleSold();
						this.setVisible(isSold);
						unsold_link.setVisible(!isSold);
					}

				};

				final Link<Void> unsoldLink = new Link<Void>("unsold_link") {

					@Override
					public void onClick() {
						final boolean isSold = item.getModelObject().isSold();
						item.getModelObject().toggleSold();
						this.setVisible(isSold);
						soldLink.setVisible(!isSold);
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

				final Link<Void> deletelink = new Link<Void>(
						"property_delete_link") {
					@Override
					public void onClick() {
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

				final boolean isReserved = item.getModelObject().isReserved();
				reservedLink.setVisible(isReserved);
				unreservedLink.setVisible(!isReserved);

				final boolean isSold = item.getModelObject().isSold();
				soldLink.setVisible(isSold);
				unsoldLink.setVisible(!isSold);

				final List<IColumn<State>> columns = new ArrayList<IColumn<State>>();

				columns.add(new PropertyColumn<State>(Model.of(this
						.getString("state_date")), "date"));

				columns.add(new PropertyColumn<State>(Model.of(this
						.getString("state_previous")), "previous"));

				columns.add(new PropertyColumn<State>(Model.of(this
						.getString("state_actual")), "actual"));

				int size = item.getModelObject().getStates().size();
				if (size == 0) {
					size = 1;
				}

				final DefaultDataTable<State> stateTable = new DefaultDataTable<State>(
						"states", columns, new StateDataProvider(
								item.getModelObject()), size);

				stateTable.add(new AttributeModifier("class", Model
						.of("table table-striped properties-table")));

				item.add(link);
				item.add(editlink);
				item.add(toggleLink);
				item.add(reservedLink);
				item.add(unreservedLink);
				item.add(soldLink);
				item.add(unsoldLink);
				item.add(deletelink);
				item.add(viewlink);
				item.add(stateTable);

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
