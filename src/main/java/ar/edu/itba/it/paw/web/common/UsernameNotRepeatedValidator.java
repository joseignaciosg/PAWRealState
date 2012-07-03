package ar.edu.itba.it.paw.web.common;

import org.apache.wicket.validation.*;
import org.apache.wicket.validation.validator.*;

import ar.edu.itba.it.paw.domain.repositories.impl.*;

@SuppressWarnings("serial")
public class UsernameNotRepeatedValidator extends StringValidator {

	private HibernateUserRepository repository;

	public UsernameNotRepeatedValidator(final HibernateUserRepository repo) {
		this.repository = repo;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {
		if (this.repository.getByName(validatable.getValue()) != null) {
			this.error(validatable);
		}
	}
}
