package ar.edu.itba.it.paw.web.utils.customcolumns;

import org.apache.wicket.*;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.*;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;

// Source: http://www.javabeat.net/2011/04/displaying-data-using-datatable-in-apache-wicket/2/
@SuppressWarnings("serial")
public abstract class ClickableColumn<T> extends AbstractColumn<T> {

	private IModel<String> message;

	public ClickableColumn(final IModel<String> displayModel,
			final IModel<String> message) {
		super(displayModel);
		this.message = message;
	}

	public void populateItem(final Item<ICellPopulator<T>> cellItem,
			final String componentId, final IModel<T> rowModel) {

		final Link<Void> link = new Link<Void>(componentId) {
			@Override
			public void onClick() {
				ClickableColumn.this.onClick(this, rowModel);
			}
		};

		link.add(new AttributeModifier("class", Model
				.of("btn btn-active btn-large")));

		link.setBody(this.message);

		cellItem.add(link);
	}

	protected abstract void onClick(Link<Void> owner, IModel<T> clicked);
}
