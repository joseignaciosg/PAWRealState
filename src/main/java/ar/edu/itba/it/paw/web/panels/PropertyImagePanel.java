package ar.edu.itba.it.paw.web.panels;

import java.util.*;

import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.mapper.parameter.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.web.*;

@SuppressWarnings("serial")
public class PropertyImagePanel extends Panel {

	private Image renderedImage;

	public PropertyImagePanel(final String id, final IModel<?> model) {
		super(id, model);
		final PageParameters params = this.resolveImageLink(model);

		if (params != null) {
			this.add(this.renderedImage = new Image("img",
					RealStateApp.imageReference, params));
		} else {
			this.add(this.renderedImage = new Image("img",
					RealStateApp.NO_PROP_PICTURE));
		}
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

	public Image getRenderedImage() {
		return this.renderedImage;
	}
}
