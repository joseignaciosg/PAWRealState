package ar.edu.itba.it.paw.web.panels;

import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.web.utils.*;

public class ThumbnailPanel extends Panel {

	public ThumbnailPanel(final String id, final IModel<Property> model) {
		super(id, model);

		final Link<Void> link = WicketShortcuts.linkProperty("thumbnail_link",
				model);

		link.add(new PropertyImagePanel("img", model));

		link.add(new Label("neighborhood", new PropertyModel<String>(model,
				"neighborhood")));

		link.add(new Label("currency", new PropertyModel<String>(model,
				"currency")));

		link.add(new Label("price", new PropertyModel<String>(model, "price")));

		this.add(link);
	}

}
