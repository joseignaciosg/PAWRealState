package ar.edu.itba.it.paw.web.resolvers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class JDBCConnectionExceptionResolver implements
		HandlerExceptionResolver {

	public ModelAndView resolveException(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final Exception ex) {
		/* when someone uploads a too large image */
		if (ex.getClass().equals(JDBCConnectionException.class)) {
			final List<String> errors = new ArrayList<String>();
			errors.add("Hubo un problema con la conexión de la base de datos");
			final ModelAndView mav = new ModelAndView("errors/errors");
			mav.addObject("errors", errors);

			return mav;
		}
		return null;
	}

}
