package ar.edu.itba.it.paw.model.entities;

public interface Entity {

	public boolean isNew();

	public boolean isDirty();

	public Integer getId();

	public void setId(Integer ID);

}
