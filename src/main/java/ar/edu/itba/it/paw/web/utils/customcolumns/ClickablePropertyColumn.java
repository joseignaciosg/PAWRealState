package ar.edu.itba.it.paw.web.utils.customcolumns;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.*;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;

// Source: http://www.javabeat.net/2011/04/displaying-data-using-datatable-in-apache-wicket/2/
@SuppressWarnings("serial")
public abstract class ClickablePropertyColumn<T> extends AbstractColumn<T> {
	private final String property;

	public ClickablePropertyColumn(final IModel<String> displayModel,
			final String property) {
		this(displayModel, property, null);
	}

	public ClickablePropertyColumn(final IModel<String> displayModel,
			final String property, final String sort) {
		super(displayModel, sort);
		this.property = property;
	}

	public void populateItem(final Item<ICellPopulator<T>> cellItem,
			final String componentId, final IModel<T> rowModel) {
		cellItem.add(new LinkPanel(componentId, rowModel,
				new PropertyModel<Object>(rowModel, this.property)));
	}

	protected abstract void onClick(Link<Void> owner, IModel<T> clicked);

	private class LinkPanel extends Panel {
		public LinkPanel(final String id, final IModel<T> rowModel,
				final IModel<?> labelModel) {
			super(id);
			final Link<Void> link = new Link<Void>("link") {
				@Override
				public void onClick() {
					ClickablePropertyColumn.this.onClick(this, rowModel);
				}
			};
			this.add(link);
			link.add(new Label("label", labelModel));
		}
	}
}
