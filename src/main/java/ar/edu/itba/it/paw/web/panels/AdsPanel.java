package ar.edu.itba.it.paw.web.panels;

import java.util.*;

import org.apache.wicket.*;
import org.apache.wicket.markup.html.image.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.api.*;

@SuppressWarnings("serial")
public class AdsPanel extends Panel {

	@SpringBean
	AdRepository ads;

	public AdsPanel(final String id) {
		super(id);

		final LoadableDetachableModel<List<Ad>> adModel = new LoadableDetachableModel<List<Ad>>() {

			@Override
			public List<Ad> load() {
				return AdsPanel.this.ads.getRandomAds(4);
			}

		};

		this.add(new RefreshingView<Ad>("ad_item") {

			@Override
			protected Iterator<IModel<Ad>> getItemModels() {
				final List<IModel<Ad>> result = new ArrayList<IModel<Ad>>();
				for (final Ad ad : adModel.getObject()) {
					result.add(new EntityModel<Ad>(Ad.class, ad));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<Ad> item) {
				item.add(new Image("ad_img", "").add(new AttributeModifier(
						"src", Model.of(item.getModelObject().getUrl()))));
			}

		});

	}
}
