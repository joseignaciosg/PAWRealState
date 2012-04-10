package ar.edu.itba.it.paw.model.entities.utils;

import java.util.Collection;

import ar.edu.itba.it.paw.model.entities.Entity;

public class EntityUtils {
	public static boolean contains(final Collection<? extends Entity> entities,
			final Entity t) {
		for (final Entity entity : entities) {
			if (entity.getId().equals(t.getId())) {
				return true;
			}
		}
		return false;
	}

	public static boolean remove(final Collection<? extends Entity> entities,
			final Entity t) {
		Entity toRemove = null;
		for (final Entity entity : entities) {
			if (entity.getId().equals(t.getId())) {
				toRemove = entity;
				break;
			}
		}

		if (toRemove == null) {
			return false;
		}

		entities.remove(toRemove);

		return true;
	}
}
