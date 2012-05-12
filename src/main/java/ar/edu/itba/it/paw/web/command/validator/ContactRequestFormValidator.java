package ar.edu.itba.it.paw.web.command.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.ContactRequestForm;

@Component
public class ContactRequestFormValidator implements Validator {
	private final String VALID_MAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern emailPattern = Pattern.compile(this.VALID_MAIL);

	public ContactRequestFormValidator() {

	}

	public boolean supports(final Class<?> clazz) {
		return ContactRequestForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {
		final ContactRequestForm form = (ContactRequestForm) target;
		ValidationUtils.rejectIfEmpty(errors, "firstName", "empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "empty");
		ValidationUtils.rejectIfEmpty(errors, "phone", "empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "empty");
		if (form.getEmail() != null) {
			if (!this.emailPattern.matcher(form.getEmail()).find()) {
				errors.rejectValue("email", "notvalid");
			}
		}
	}
}
