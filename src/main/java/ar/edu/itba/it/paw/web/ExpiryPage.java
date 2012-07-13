package ar.edu.itba.it.paw.web;

import org.apache.wicket.markup.html.*;
import org.apache.wicket.markup.html.link.*;

@SuppressWarnings("serial")
public class ExpiryPage extends WebPage {

	public ExpiryPage() {
		this.add(new Link<Void>("back_link") {

			@Override
			public void onClick() {
				this.setResponsePage(HomePage.class);
			}

		});
	}
}
