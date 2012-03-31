package ar.edu.itba.it.paw.model.entities;

public class ContactRequest implements Entity {

	private Integer ID;
	private String name;
	private String email;
	private String telephone;
	private boolean dirty;

	public ContactRequest(final Integer iD, final String name,
			final String email, final String telephone) {
		super();
		this.ID = iD;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.setDirty(false);
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
		this.setDirty(true);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
		this.setDirty(true);
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
		this.setDirty(true);
	}

	public Integer getID() {
		return this.ID;
	}

	public void setID(final Integer iD) {
		this.ID = iD;
		this.setDirty(true);
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.ID == null) ? 0 : this.ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final ContactRequest other = (ContactRequest) obj;
		if (this.ID == null) {
			if (other.ID != null) {
				return false;
			}
		} else if (!this.ID.equals(other.ID)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ContactRequest [ID=" + this.ID + ", name=" + this.name
				+ ", email=" + this.email + ", telephone=" + this.telephone
				+ ", dirty=" + this.dirty + "]";
	}

}
