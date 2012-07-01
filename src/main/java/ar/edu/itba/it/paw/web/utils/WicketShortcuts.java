package ar.edu.itba.it.paw.web.utils;

import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.model.*;
import org.apache.wicket.request.component.*;
import org.apache.wicket.request.mapper.parameter.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.web.properties.*;

public class WicketShortcuts {

	@SuppressWarnings("serial")
	public static Link<Void> link(final String name,
			final Class<? extends IRequestablePage> target) {
		return new Link<Void>(name) {
			@Override
			public void onClick() {
				this.setResponsePage(target);
			}
		};
	}

	@SuppressWarnings("serial")
	public static Link<Void> linkProperty(final String name,
			final IModel<?> model) {
		return new Link<Void>(name) {
			@Override
			public void onClick() {
				final PageParameters parameters = new PageParameters();
				parameters.add("id", model.getObject());
				this.setResponsePage(new PropertyPage((Property) model
						.getObject()));
			}

		};
	}
}
