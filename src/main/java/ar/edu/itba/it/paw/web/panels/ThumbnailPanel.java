package ar.edu.itba.it.paw.web.panels;

import java.util.*;

import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.photos.*;
import ar.edu.itba.it.paw.web.utils.*;

public class ThumbnailPanel extends Panel {

	@SpringBean
	private PropertyRepository repo;

	public ThumbnailPanel(final String id, final IModel<?> model) {
		super(id, model);

		final Link<Void> link = WicketShortcuts.linkProperty("thumbnail_link",
				new PropertyModel<Integer>(model, "id"));

		final PageParameters params = this.resolveImageLink(model);

		if (params != null) {
			link.add(new Image("img", new ImageResourceReference(this.repo),
					params));
		} else {
			link.add(new Image("img", RealStateApp.NO_PROP_PICTURE));
		}

		link.add(new Label("neighborhood", new PropertyModel<String>(model,
				"neighborhood")));

		link.add(new Label("price", new PropertyModel<String>(model, "price")));

		this.add(link);
	}

	private PageParameters resolveImageLink(final IModel<?> model) {
		final PageParameters parameters = new PageParameters();

		final List<Photo> photos = new PropertyModel<List<Photo>>(model,
				"photos").getObject();

		if (photos == null || photos.size() > 0) {
			parameters.set("id", photos.get(0).getId());
			return parameters;
		}
		return null;
	}
}
