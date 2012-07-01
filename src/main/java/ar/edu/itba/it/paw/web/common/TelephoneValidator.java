package ar.edu.itba.it.paw.web.common;

import java.util.regex.*;

import org.apache.wicket.validation.*;
import org.apache.wicket.validation.validator.*;

public class TelephoneValidator extends StringValidator {

	private static TelephoneValidator validator;

	private Pattern pattern = Pattern.compile("(\\+)?\\d+");

	private TelephoneValidator() {

	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {
		final Matcher matcher = this.pattern.matcher(validatable.getValue());

		if (!matcher.matches()) {
			this.error(validatable);
		}
	}

	public static TelephoneValidator getInstance() {
		if (validator == null) {
			validator = new TelephoneValidator();
		}

		return validator;
	}

}
