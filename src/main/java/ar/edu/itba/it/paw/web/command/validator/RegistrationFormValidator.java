package ar.edu.itba.it.paw.web.command.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ar.edu.itba.it.paw.domain.repositories.impl.HibernateUserRepository;
import ar.edu.itba.it.paw.web.command.RegistrationForm;

@Component
public class RegistrationFormValidator implements Validator {

	private final String VALID_MAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern emailPattern = Pattern.compile(this.VALID_MAIL);

	private HibernateUserRepository userRepository;

	@Autowired
	public RegistrationFormValidator(
			final HibernateUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public boolean supports(final Class<?> clazz) {
		return RegistrationForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {
		final RegistrationForm form = (RegistrationForm) target;
		final CommonsMultipartFile file = form.getPhoto();
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
		if (form.getUserName() != null) {
			if (this.userRepository.getByName(form.getUserName()) != null) {
				errors.rejectValue("userName", "alreadyInUse");
			}

		}

		if (form.getType() != null && form.getType().equals("RealStateAgency")) {
			ValidationUtils.rejectIfEmpty(errors, "agencyName", "empty");
			if (file != null && file.getSize() > 10000000) {
				errors.rejectValue("photo", "toobig");
			}
			if (file != null
					&& !(file.getOriginalFilename().endsWith(".jpeg")
							|| file.getOriginalFilename().endsWith(".jpg")
							|| file.getOriginalFilename().endsWith(".gif") || file
							.getOriginalFilename().endsWith(".png"))) {
				errors.rejectValue("photo", "invalidformat");
			}
		}
	}

}
