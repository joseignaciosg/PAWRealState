package ar.edu.itba.it.paw.web.bindings;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import ar.edu.itba.it.paw.daos.api.PropertyDao;
import ar.edu.itba.it.paw.model.entities.Property;

// La idea de esta clase era que agregue binders para poder
// Pasar todos los @PathVariables que son string a objetos desde un solo lugar.
// No consigo que la configuración funcione.
@Component(value = "customBindingInitializer")
public class CustomBindingInitializer implements WebBindingInitializer {

	public static class PropertyCustomEditor extends PropertyEditorSupport {

		@Autowired
		PropertyDao dao;

		private String value;

		@Override
		public void setAsText(final String text)
				throws IllegalArgumentException {
			this.value = text;
		}

		@Override
		public Object getValue() {
			try {
				return this.dao.getById(Integer.valueOf(this.value));
			} catch (final Exception e) {
				return null;
			}
		}
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder, final WebRequest request) {
		binder.registerCustomEditor(Property.class, new PropertyCustomEditor());
	}
}
