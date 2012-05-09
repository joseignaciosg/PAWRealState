package ar.edu.itba.it.paw.domain.entities;

public abstract class BaseEntity implements Entity {

	private Integer id = null;
	private boolean isDirty = false;

	public BaseEntity() {

	}

	public BaseEntity(final Integer id) {
		this.id = id;
	}

	public final boolean isNew() {
		return this.id == null;
	}

	public final boolean isDirty() {
		return this.isDirty;
	}

	public final void setDirty() {
		this.isDirty = true;
	}

	public final void setDirty(final boolean dirty) {
		this.isDirty = dirty;
	}

	public final Integer getId() {
		return this.id;
	}

	public final void setId(final Integer ID) {
		this.id = ID;
	}

}
