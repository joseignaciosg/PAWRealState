package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.web.command.PropertyPhotoForm;

@Component
public class PropertyPhotoFormValidator implements Validator {

	public PropertyPhotoFormValidator() {

	}

	public boolean supports(final Class<?> clazz) {
		return clazz.equals(PropertyPhotoForm.class);
	}

	public void validate(final Object target, final Errors errors) {
		final PropertyPhotoForm form = (PropertyPhotoForm) target;
		ValidationUtils.rejectIfEmpty(errors, "property", "empty");
		if (form.getFile() != null) {
			if (form.getFile().getSize() == 0) {
				errors.rejectValue("property", "emptyphoto");
			}
		}
		final CommonsMultipartFile file = (CommonsMultipartFile) form.getFile();
		if (file != null) {
			if (file.getSize() >= 10000000) {
				errors.rejectValue("property", "toobig");
			}
			if (file != null
					&& !(file.getOriginalFilename().endsWith(".jpeg")
							|| file.getOriginalFilename().endsWith(".jpg")
							|| file.getOriginalFilename().endsWith(".gif") || file
							.getOriginalFilename().endsWith(".png"))) {
				errors.rejectValue("property", "invalidformat");
			}
		}
	}
}
