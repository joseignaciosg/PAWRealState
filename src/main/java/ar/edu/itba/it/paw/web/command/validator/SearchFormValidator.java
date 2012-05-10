package ar.edu.itba.it.paw.web.command.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.web.command.SearchForm;

@Component
public class SearchFormValidator implements Validator {

	public SearchFormValidator() {
	}

	public boolean supports(final Class<?> clazz) {
		return SearchForm.class.equals(clazz);
	}

	public void validate(final Object target, final Errors errors) {

		final SearchForm form = (SearchForm) target;
		System.out.println(form);
		if (form.getPricelow() != null) {
			if (form.getPricelow() < 0) {
				errors.rejectValue("pricelow", "lowercero");
			}
		}

		if (form.getPricehigh() != null) {
			if (form.getPricehigh() < 0) {
				errors.rejectValue("pricehigh", "lowercero");
			}
		}

		if (form.getQuant() != null) {
			if (form.getQuant() < 0) {
				errors.rejectValue("quant", "lowercero");
			}
		}
		if (form.getPage() != null) {
			if (form.getPage() < 0) {
				errors.rejectValue("page", "lowercero");
			}
		}
	}
}