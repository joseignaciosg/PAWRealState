package ar.edu.itba.it.paw.web.panels;

import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.*;

import ar.edu.itba.it.paw.web.*;

@SuppressWarnings("serial")
public class NavbarPanel extends Panel {

	public NavbarPanel(final String id) {
		super(id);

		this.add(new Link<Void>("base_path") {
			@Override
			public void onClick() {
				this.setResponsePage(HomePage.class);
			}
		});

		final RealStateSession session = (RealStateSession) this.getSession();

		final Form loginForm = new Form("login_form");

		loginForm.setVisible(!session.isSignedIn());

		this.add(new Link<Void>("user_dropdown") {
			@Override
			public void onClick() {

			}
		}.setVisible(session.isSignedIn()));

		this.add(loginForm);
	}
}
