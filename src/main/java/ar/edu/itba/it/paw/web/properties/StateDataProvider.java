package ar.edu.itba.it.paw.web.properties;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.entities.Property;
import ar.edu.itba.it.paw.domain.entities.State;

@SuppressWarnings("serial")
public class StateDataProvider extends SortableDataProvider<State> {

	Property property;

	public StateDataProvider(final Property property) {
		this.property = property;
	}

	public Iterator<? extends State> iterator(final int first, final int count) {
		return this.property.getStates().iterator();
	}

	public int size() {
		return this.property.getStates().size();
	}

	public IModel<State> model(final State object) {
		return new EntityModel<State>(State.class, object);
	}

}
