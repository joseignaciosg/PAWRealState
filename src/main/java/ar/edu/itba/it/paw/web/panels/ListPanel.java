package ar.edu.itba.it.paw.web.panels;

import org.apache.wicket.markup.html.panel.*;

public class ListPanel extends Panel {
	public ListPanel(final String id, final Panel p) {
		super(id);
		this.add(p);
	}
}
