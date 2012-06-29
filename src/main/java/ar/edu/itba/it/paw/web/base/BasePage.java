package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.*;

import ar.edu.itba.it.paw.web.panels.*;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	public BasePage() {
		this.add(new NavbarPanel("navbar"));
	}
}
