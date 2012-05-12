package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.PropertyForm;

@Component
public class PropertyFormValidator implements Validator {

	public PropertyFormValidator() {
	}

	public boolean supports(final Class<?> clazz) {
		return PropertyForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {
		final PropertyForm form = (PropertyForm) target;

		ValidationUtils.rejectIfEmpty(errors, "operation", "empty");
		ValidationUtils.rejectIfEmpty(errors, "type", "empty");
		ValidationUtils.rejectIfEmpty(errors, "address", "empty");
		ValidationUtils.rejectIfEmpty(errors, "neighborhood", "empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "empty");
		ValidationUtils.rejectIfEmpty(errors, "spaces", "empty");
		ValidationUtils.rejectIfEmpty(errors, "coveredArea", "empty");
		ValidationUtils.rejectIfEmpty(errors, "freeArea", "empty");
		ValidationUtils.rejectIfEmpty(errors, "age", "empty");

		if (form.getNeighborhood() != null) {
			final Integer nesize = form.getNeighborhood().length();
			if (nesize < 1 || nesize > 150) {
				errors.rejectValue("neighborhood", "invalidsize");
			}
		}
		if (form.getAddress() != null) {
			final Integer adsize = form.getAddress().length();
			if (adsize < 1 || adsize > 150) {
				errors.rejectValue("address", "invalidsize");
			}
		}
		if (form.getPrice() != null) {
			if (form.getPrice() < 0) {
				errors.rejectValue("price", "lowercero");
			}
		}
		if (form.getSpaces() != null) {

			if (form.getSpaces() < 0) {
				errors.rejectValue("spaces", "lowercero");
			}
			if (form.getCoveredArea() != null) {
				if (form.getCoveredArea() < 0) {
					errors.rejectValue("coveredArea", "lowercero");
				}
			}
			if (form.getFreeArea() != null) {
				if (form.getFreeArea() < 0) {
					errors.rejectValue("freeArea", "lowercero");
				}
			}
			if (form.getAge() != null) {
				if (form.getAge() < 0) {
					errors.rejectValue("age", "lowercero");
				}
			}
		}
	}

}
