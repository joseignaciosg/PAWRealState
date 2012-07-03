package ar.edu.itba.it.paw.web.utils.customcolumns;

import org.apache.wicket.*;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.*;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.repeater.*;
import org.apache.wicket.model.*;

import ar.edu.itba.it.paw.web.panels.*;

@SuppressWarnings("serial")
public class PhotoPropertyColumn<T> extends AbstractColumn<T> {

	private int width;
	private int height;

	public PhotoPropertyColumn(final IModel<String> displayModel,
			final String sortProperty, final int width, final int height) {
		super(displayModel, sortProperty);
		this.width = width;
		this.height = height;
	}

	public void populateItem(final Item<ICellPopulator<T>> cellItem,
			final String componentId, final IModel<T> rowModel) {
		final PropertyImagePanel image = new PropertyImagePanel(componentId,
				rowModel);
		cellItem.add(image);
		image.getRenderedImage().add(
				new AttributeModifier("style", new Model<String>("width:"
						+ this.width + "px; height:" + this.height + "px;")));
	}
}
