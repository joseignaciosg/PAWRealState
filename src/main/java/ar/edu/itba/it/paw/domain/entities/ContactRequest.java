package ar.edu.itba.it.paw.domain.entities;

public class ContactRequest extends BaseEntity {

	private String name;
	private String email;
	private String telephone;
	private String description;
	private Property propRefered;

	public ContactRequest(final String name, final String email,
			final String telephone, final String description,
			final Property propRefered) {
		this(null, name, email, telephone, description, propRefered);
	}

	public ContactRequest(final Integer iD, final String name,
			final String email, final String telephone,
			final String description, final Property propRefered) {
		super();
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.description = description;
		this.propRefered = propRefered;
		this.setDirty(false);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
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

	public Property getPropRefered() {
		return this.propRefered;
	}

	public void setPropRefered(final Property propRefered) {
		this.propRefered = propRefered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result
				+ ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result
				+ ((this.name == null) ? 0 : this.name.hashCode());
		result = prime
				* result
				+ ((this.propRefered == null) ? 0 : this.propRefered.hashCode());
		result = prime * result
				+ ((this.telephone == null) ? 0 : this.telephone.hashCode());
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
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.propRefered == null) {
			if (other.propRefered != null) {
				return false;
			}
		} else if (!this.propRefered.equals(other.propRefered)) {
			return false;
		}
		if (this.telephone == null) {
			if (other.telephone != null) {
				return false;
			}
		} else if (!this.telephone.equals(other.telephone)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ContactRequest [ID=" + this.getId() + ", name=" + this.name
				+ ", email=" + this.email + ", telephone=" + this.telephone
				+ "";
	}

}
