package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.SearchForm;

@Component
public class SearchFormValidator implements Validator {

	public boolean supports(final Class<?> clazz) {
		return SearchForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {

		errors.reject("error.hola");
		return;
	}
}