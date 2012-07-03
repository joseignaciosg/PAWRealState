package ar.edu.itba.it.paw.web.registration;

import java.util.*;
import java.util.Arrays;

import org.apache.wicket.*;
import org.apache.wicket.ajax.*;
import org.apache.wicket.feedback.*;
import org.apache.wicket.markup.html.*;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.*;
import org.apache.wicket.markup.html.form.validation.*;
import org.apache.wicket.markup.html.panel.*;
import org.apache.wicket.model.*;
import org.apache.wicket.spring.injection.annot.*;
import org.apache.wicket.util.lang.*;
import org.apache.wicket.validation.validator.*;

import ar.edu.itba.it.paw.domain.entities.*;
import ar.edu.itba.it.paw.domain.repositories.impl.*;
import ar.edu.itba.it.paw.web.base.*;
import ar.edu.itba.it.paw.web.common.*;

public class RegistrationPage extends BasePage {

	public enum UserType {
		USER, AGENCY;
	}

	@SpringBean
	HibernateUserRepository repo;

	private transient String registration_form_username;
	private transient String registration_form_password;
	private transient String registration_form_password_repeated;
	private transient String registration_form_email;
	private transient String registration_form_first_name;
	private transient String registration_form_last_name;
	private transient String registration_form_phone;
	private transient UserType registration_form_user_type;
	private transient String registration_form_agency_name;
	private transient List<FileUpload> registration_form_logo;

	@SuppressWarnings("serial")
	public RegistrationPage() {

		this.registration_form_user_type = UserType.USER;

		final Form<RegistrationPage> registrationForm = new Form<RegistrationPage>(
				"registration_form",
				new CompoundPropertyModel<RegistrationPage>(this));

		registrationForm.add(new FeedbackPanel("feedback_panel",
				new ContainerFeedbackMessageFilter(registrationForm)));

		registrationForm
				.add(new TextField<String>("registration_form_username").add(
						new UsernameNotRepeatedValidator(this.repo))
						.setRequired(true));

		final TextField<String> password = new PasswordTextField(
				"registration_form_password");

		final TextField<String> passwordRepeated = new PasswordTextField(
				"registration_form_password_repeated");

		final EqualPasswordInputValidator validator = new EqualPasswordInputValidator(
				password, passwordRepeated);

		registrationForm.add(validator);

		registrationForm.add(password);
		registrationForm.add(passwordRepeated);

		registrationForm.add(new TextField<String>("registration_form_email")
				.add(EmailAddressValidator.getInstance()).setRequired(true));

		registrationForm.add(new TextField<String>(
				"registration_form_first_name").setRequired(true));
		registrationForm.add(new TextField<String>(
				"registration_form_last_name").setRequired(true));

		registrationForm.add(new TextField<String>("registration_form_phone")
				.setRequired(true).add(TelephoneValidator.getInstance()));

		final WebMarkupContainer agencyNameDiv = new WebMarkupContainer(
				"agency_name_div");

		final WebMarkupContainer agencyLogoDiv = new WebMarkupContainer(
				"agency_logo_div");

		final FileUploadField fuField = new FileUploadField(
				"registration_form_logo");

		fuField.add(new MultipleFileTypeValidator("image/png", "image/jpeg",
				"image/jpg", "image/gif"));

		registrationForm.setMultiPart(true);
		registrationForm.setMaxSize(Bytes.megabytes(1));

		final TextField<String> agencyName = new TextField<String>(
				"registration_form_agency_name");

		fuField.setOutputMarkupId(true);
		agencyLogoDiv.add(fuField);
		agencyLogoDiv.setOutputMarkupId(true);

		agencyName.setOutputMarkupId(true);
		agencyNameDiv.add(agencyName);
		agencyNameDiv.setOutputMarkupId(true);

		final AjaxFallbackDropDown<UserType> userTypes = new AjaxFallbackDropDown<UserType>(
				"registration_form_user_type",
				Arrays.asList(UserType.values()),
				new EnumChoiceRenderer<UserType>()) {
			@Override
			public void onSelectionChanged(final AjaxRequestTarget target,
					final UserType newSelection) {
				target.add(agencyLogoDiv);
				target.add(fuField);
				target.add(agencyNameDiv);
				target.add(agencyName);

				if (newSelection == UserType.AGENCY) {
					agencyName.setRequired(true);
					fuField.setRequired(true);
					agencyNameDiv.add(new AttributeModifier("class", Model
							.of("control-group agency-control")));
					agencyLogoDiv.add(new AttributeModifier("class", Model
							.of("control-group agency-control")));
				}
				if (newSelection == UserType.USER) {
					agencyName.setRequired(true);
					fuField.setRequired(false);
					agencyNameDiv.add(new AttributeModifier("class", Model
							.of("control-group hidden agency-control")));
					agencyLogoDiv.add(new AttributeModifier("class", Model
							.of("control-group hidden agency-control")));
				}

			}
		};

		final Button submitButton = new Button("registration_form_submit") {
			@Override
			public void onSubmit() {
				User u = null;
				switch (RegistrationPage.this.registration_form_user_type) {
				case USER:
					u = new User(
							RegistrationPage.this.registration_form_first_name,
							RegistrationPage.this.registration_form_last_name,
							RegistrationPage.this.registration_form_email,
							RegistrationPage.this.registration_form_phone,
							RegistrationPage.this.registration_form_username,
							RegistrationPage.this.registration_form_password);
					break;
				case AGENCY:
					final FileUpload upload = RegistrationPage.this.registration_form_logo
							.get(0);

					final Photo p = new Photo(upload.getBytes(),
							upload.getContentType());

					u = new RealStateAgency(
							RegistrationPage.this.registration_form_first_name,
							RegistrationPage.this.registration_form_last_name,
							RegistrationPage.this.registration_form_email,
							RegistrationPage.this.registration_form_phone,
							RegistrationPage.this.registration_form_username,
							RegistrationPage.this.registration_form_password,
							RegistrationPage.this.registration_form_agency_name,
							p);
					break;
				default:
					throw new RuntimeException("D:");
				}

				RegistrationPage.this.repo.save(u);
			}
		};

		submitButton.add(new AttributeModifier("value", Model.of(this
				.getString("label_reg_form_submit"))));

		registrationForm.add(submitButton);
		registrationForm.add(userTypes.setRequired(true));
		registrationForm.add(agencyNameDiv);
		registrationForm.add(agencyLogoDiv);

		this.add(registrationForm);
	}
}
