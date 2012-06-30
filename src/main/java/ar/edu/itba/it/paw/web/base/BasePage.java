package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.*;

import ar.edu.itba.it.paw.web.panels.*;

import com.google.code.jqwicket.*;
import com.google.code.jqwicket.api.*;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	public BasePage() {
		final NavbarPanel panel = new NavbarPanel("navbar");

		// Esto carga jQuery...
		panel.add(new JQBehavior(JQuery.$(panel)));
		this.add(panel);
	}
}
