package ar.edu.itba.it.paw.web.command;

import ar.edu.itba.it.paw.domain.entities.PersistentEntity;

public interface EntityForm<T extends PersistentEntity> {
	public T build();
}
