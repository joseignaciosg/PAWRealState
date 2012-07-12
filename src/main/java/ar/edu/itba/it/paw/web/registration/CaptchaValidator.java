package ar.edu.itba.it.paw.web.registration;

import org.apache.wicket.validation.*;
import org.apache.wicket.validation.validator.*;

public class CaptchaValidator<T> extends AbstractValidator<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String randomString;

	public CaptchaValidator(final String randomString) {
		this.randomString = randomString;
	}

	@Override
	protected void onValidate(final IValidatable<T> validatable) {
		if (!this.randomString.equals(validatable.getValue())) {
			this.error(validatable, "CaptchaValidators.invalidcaptcha");
		}
	}
}