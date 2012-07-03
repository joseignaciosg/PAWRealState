package ar.edu.itba.it.paw.web.common;

import org.apache.wicket.validation.*;
import org.apache.wicket.validation.validator.*;

@SuppressWarnings("serial")
public class NotEmptyValidator<T> extends AbstractValidator<T> {

	@Override
	protected void onValidate(final IValidatable<T> validatable) {
		if (validatable.getValue() == null) {
			this.error(validatable);
		}

		if (validatable.getValue() instanceof String) {
			final String str = (String) validatable.getValue();
			if (str.equals("")) {
				this.error(validatable);
			}
		}
	}
}
