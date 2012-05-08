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

	public static void validateNotNegative(final Integer integer,
			final String message, final List<String> errors) {
		if (integer != null && integer < 0) {
			errors.add(message);
		}

	}

}
