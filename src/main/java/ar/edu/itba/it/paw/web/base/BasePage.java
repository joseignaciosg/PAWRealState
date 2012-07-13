package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.*;

import ar.edu.itba.it.paw.web.panels.*;

import com.google.code.jqwicket.*;
import com.google.code.jqwicket.api.*;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
	public BasePage() {
		final NavbarPanel panel = new NavbarPanel("navbar");
		panel.add(new JQBehavior(JQuery.$(panel)));
		final AdsPanel adpanel = new AdsPanel("ads_bar");

		this.add(adpanel);
		this.add(panel);
	}

}
