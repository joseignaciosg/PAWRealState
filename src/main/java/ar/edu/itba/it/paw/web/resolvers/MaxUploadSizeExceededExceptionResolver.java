package ar.edu.itba.it.paw.web.resolvers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.web.command.RegistrationForm;

@Component
public class MaxUploadSizeExceededExceptionResolver implements
		HandlerExceptionResolver {

	public ModelAndView resolveException(final HttpServletRequest request,
			final HttpServletResponse response, final Object handler,
			final Exception ex) {
		/* when someone uploads a too large image */
		if (ex.getClass().equals(MaxUploadSizeExceededException.class)) {
			final RegistrationForm registrationForm = (RegistrationForm) request
					.getAttribute("registrationForm");
			final List<String> errors = new ArrayList<String>();
			errors.add("Las imágen subida es demasiado grande");
			final ModelAndView mav = new ModelAndView("errors/errors");
			mav.addObject("errors", errors);

			return mav;
		}
		return null;
	}
}
