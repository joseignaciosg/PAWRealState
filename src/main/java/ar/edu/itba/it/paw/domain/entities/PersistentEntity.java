package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return this.id;
	}

	public boolean isNew() {
		return this.id == 0;
	}

	public <T extends PersistentEntity> void copy(final T entity) {
		if (entity.isNew()) {
			entity.id = this.id;
		}
	}
}
