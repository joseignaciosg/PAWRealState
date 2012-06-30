package ar.edu.itba.it.paw.web.agencies;

import java.util.*;

import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.base.*;
import ar.edu.itba.it.paw.web.properties.*;

import com.google.code.jqwicket.ui.accordion.*;

@SuppressWarnings("serial")
public class AgencySearchPage extends BasePage {

	// TODO: Sacar este repo, solo interfacesss.
	@SpringBean
	HibernateAgencyRepository agencies;

	@SpringBean
	PropertyRepository properties;

	public AgencySearchPage() {
		final AccordionWebMarkupContainer a1 = new AccordionWebMarkupContainer(
				"agencies_accordion");

		final RefreshingView<RealStateView> accordionView = new RefreshingView<RealStateView>(
				"agency") {
			@Override
			protected Iterator<IModel<RealStateView>> getItemModels() {
				final List<IModel<RealStateView>> result = new ArrayList<IModel<RealStateView>>();
				for (final RealStateView view : AgencySearchPage.this.agencies
						.getAllWithProp()) {
					result.add(Model.of(view));
					result.add(Model.of(view));
					result.add(Model.of(view));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<RealStateView> item) {
				final Link<Void> link = new Link<Void>("agency_picture_link") {
					@Override
					public void onClick() {
						final PageParameters parameters = new PageParameters();
						parameters.add("user", item.getModelObject().getId());
						this.setResponsePage(PropertySearchPage.class,
								parameters);
					}
				};

				final PageParameters params = new PageParameters();

				params.add("id", item.getModelObject().getPhotoID());

				link.add(new Image("agency_picture",
						RealStateApp.imageReference, params));

				item.add(new Label(
						"agency_name_header",
						new PropertyModel<String>(item.getModel(), "agencyName")));

				item.add(new Label("agency_property_count",
						new PropertyModel<String>(item.getModel(), "propCount")));

				item.add(new Link<Void>("agency_link") {
					@Override
					public void onClick() {
						final PageParameters parameters = new PageParameters();
						parameters.add("user", item.getModelObject().getId());
						this.setResponsePage(PropertySearchPage.class,
								parameters);
					}
				});
				item.add(link);
			}
		};

		final Label l = new Label("no_agencies_message");

		final int agenciesSize = AgencySearchPage.this.agencies
				.getAllWithProp().size();

		accordionView.setVisible(agenciesSize > 0);
		l.setVisible(!accordionView.isVisible());

		a1.add(accordionView);
		this.add(l);
		this.add(a1);
	}
}
