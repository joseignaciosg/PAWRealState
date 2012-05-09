package ar.edu.itba.it.paw.web.command.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.RegistrationForm;

@Component
public class RegistrationFormValidator implements Validator {

	private final String VALID_MAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern emailPattern = Pattern.compile(this.VALID_MAIL);

	public boolean supports(final Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {
		final RegistrationForm form = (RegistrationForm) target;
		ValidationUtils.rejectIfEmpty(errors, "userName", "empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "empty");
		ValidationUtils.rejectIfEmpty(errors, "repeatedPassword", "empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "empty");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "empty");
		ValidationUtils.rejectIfEmpty(errors, "phone", "empty");

		if (form.getPassword() != null && form.getRepeatedPassword() != null) {
			if (!form.getPassword().equals(form.getRepeatedPassword())) {
				errors.rejectValue("password", "passNotMaching");
			}
		}
		if (form.getUserName() != null) {
			if (form.getUserName().length() > 20) {
				errors.rejectValue("userName", "toolong");
			}
		}
		if (form.getPassword() != null) {
			if (form.getPassword().length() > 20) {
				errors.rejectValue("password", "toolong");
			}
		}
		if (form.getEmail() != null) {
			if (!this.emailPattern.matcher(form.getEmail()).find()) {
				errors.rejectValue("email", "notvalid");
			}
		}

	}
}
