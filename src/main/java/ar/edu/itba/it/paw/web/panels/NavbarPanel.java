package ar.edu.itba.it.paw.web.panels;

import org.apache.wicket.*;
import org.apache.wicket.ajax.*;
import org.apache.wicket.ajax.markup.html.*;
import org.apache.wicket.feedback.*;
import org.apache.wicket.markup.html.*;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.*;

import ar.edu.itba.it.paw.domain.repositories.api.*;
import ar.edu.itba.it.paw.web.*;
import ar.edu.itba.it.paw.web.properties.*;
import ar.edu.itba.it.paw.web.registration.*;

@SuppressWarnings("serial")
public class NavbarPanel extends Panel {

	@SpringBean
	UserRepository repo;

	AjaxFallbackLink<Void> rname;

	AjaxFallbackLink<Void> rsession;

	TextField<String> hiddenRemember;

	private transient String login_form_username;
	private transient String login_form_password;

	public NavbarPanel(final String id) {
		super(id);

		this.add(new Link<Void>("base_path") {
			@Override
			public void onClick() {
				this.setResponsePage(HomePage.class);
			}
		});

		final RealStateSession session = (RealStateSession) this.getSession();

		this.login_form_username = session.getUsername();

		final Form<NavbarPanel> loginForm = new Form<NavbarPanel>("login_form",
				new CompoundPropertyModel<NavbarPanel>(this));

		loginForm.add(new TextField<String>("login_form_username")
				.setRequired(true));
		loginForm.add(new PasswordTextField("login_form_password")
				.setRequired(true));

		this.hiddenRemember = new HiddenField<String>("login_remember");

		this.hiddenRemember.setModel(Model.of("USERNAME"));
		this.hiddenRemember.setOutputMarkupId(true);

		loginForm.add(this.hiddenRemember);

		this.rname = new AjaxFallbackLink<Void>("login_form_rname") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				target.add(this);
				target.add(NavbarPanel.this.rsession);
				target.add(NavbarPanel.this.hiddenRemember);

				NavbarPanel.this.rsession.add(new AttributeModifier("class",
						Model.of("btn btn-primary")));

				this.add(new AttributeModifier("class", Model
						.of("btn btn-primary active")));

				NavbarPanel.this.hiddenRemember.setModelObject("USERNAME");
			}
		};

		this.rsession = new AjaxFallbackLink<Void>("login_form_rsession") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				target.add(this);
				target.add(NavbarPanel.this.rname);
				target.add(NavbarPanel.this.hiddenRemember);

				NavbarPanel.this.rname.add(new AttributeModifier("class", Model
						.of("btn btn-primary")));

				this.add(new AttributeModifier("class", Model
						.of("btn btn-primary active")));

				NavbarPanel.this.hiddenRemember.setModelObject("SESSION");

			}
		};

		loginForm.add(this.rname);
		loginForm.add(this.rsession);
		loginForm.add(new FeedbackPanel("login_feedback_panel",
				new ContainerFeedbackMessageFilter(loginForm)));

		loginForm.add(new Button("login_form_submit") {
			@Override
			public void onSubmit() {

				if (!session.signIn(NavbarPanel.this.login_form_username,
						NavbarPanel.this.login_form_password,
						NavbarPanel.this.repo,
						NavbarPanel.this.hiddenRemember.getConvertedInput())) {
					this.error("invalid.login");
				} else {
					this.setResponsePage(HomePage.class);
				}
			}
		});

		loginForm.setVisible(!session.isSignedIn());

		this.add(new Link<Void>("register_link") {
			@Override
			public void onClick() {
				this.setResponsePage(RegistrationPage.class);
			}
		}.setVisible(!session.isSignedIn()));

		final MarkupContainer dropdownMenu = new WebMarkupContainer(
				"dropdown_menu");

		dropdownMenu.setOutputMarkupId(true);
		dropdownMenu.setVisible(session.isSignedIn());

		final Link<Void> dropdown = new AjaxFallbackLink<Void>("user_dropdown") {

			boolean open = false;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				target.add(dropdownMenu);

				if (!this.open) {
					dropdownMenu.add(new AttributeModifier("class", Model
							.of("dropdown open")));
				} else {
					dropdownMenu.add(new AttributeModifier("class", Model
							.of("dropdown")));
				}
				this.open = !this.open;
			}

		};

		dropdown.add(new Label("user_name_label", new PropertyModel<String>(
				session, "username")).setEnabled(session.isSignedIn()));

		dropdownMenu.add(new Link("my_properties_link") {
			@Override
			public void onClick() {
				this.setResponsePage(new PropertyUserPage(NavbarPanel.this.repo
						.getByName(session.getUsername())));

			}
		});

		dropdownMenu.add(new Link("logout_link") {
			@Override
			public void onClick() {
				session.signOut();
			}
		});

		dropdownMenu.add(new Link("create_property_link") {
			@Override
			public void onClick() {
				this.setResponsePage(new PropertySavePage());
			}
		});

		dropdownMenu.add(dropdown);

		this.add(dropdownMenu);

		this.add(loginForm);
	}
}
