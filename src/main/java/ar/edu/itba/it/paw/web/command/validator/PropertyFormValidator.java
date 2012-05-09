package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.PropertyForm;

public class PropertyFormValidator implements Validator {

	public boolean supports(final Class<?> clazz) {
		return PropertyForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {
		final PropertyForm form = (PropertyForm) target;

		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");

	}

}
