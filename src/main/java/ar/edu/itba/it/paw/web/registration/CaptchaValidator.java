package ar.edu.itba.it.paw.web.registration;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

public class CaptchaValidator extends AbstractValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String randomString;

	public CaptchaValidator(final String randomString) {
		this.randomString = randomString;
	}

	@Override
	protected void onValidate(final IValidatable validatable) {
		if (!this.randomString.equals(validatable.getValue())) {
			this.error(validatable, "CaptchaValidators.invalidcaptcha");
		}
	}
}