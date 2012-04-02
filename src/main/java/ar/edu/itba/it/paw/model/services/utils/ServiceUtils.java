package ar.edu.itba.it.paw.model.services.utils;

import java.util.List;

public class ServiceUtils {
	private ServiceUtils() {

	}

	public static void validateNotNull(final Object obj, final String message,
			final List<String> errors) {
		if (obj == null) {
			errors.add(message);
		}
	}

}