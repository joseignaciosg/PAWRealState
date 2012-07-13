package ar.edu.itba.it.paw.web.properties;

import java.util.*;

import org.apache.wicket.extensions.markup.html.repeater.util.*;
import org.apache.wicket.model.*;

import ar.edu.itba.it.paw.domain.*;
import ar.edu.itba.it.paw.domain.entities.*;

@SuppressWarnings("serial")
public class StateDataProvider extends SortableDataProvider<State> {

	private EntityModel<Property> propertyModel;

	public StateDataProvider(final Property property) {
		this.propertyModel = new EntityModel<Property>(Property.class, property);
	}

	public Iterator<? extends State> iterator(final int first, final int count) {
		return this.propertyModel.getObject().getStates().iterator();
	}

	public int size() {
		return this.propertyModel.getObject().getStates().size();
	}

	public IModel<State> model(final State object) {
		return new EntityModel<State>(State.class, object);
	}

}
