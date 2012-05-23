package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contact_requests")
public class ContactRequest extends PersistentEntity {

	private String username;
	private String email;
	private String phone;
	private String comment;

	@ManyToOne
	@JoinColumn(name = "prop_id")
	private Property property;

	public ContactRequest(final String name, final String email,
			final String telephone, final String description,
			final Property propRefered) {
		this(null, name, email, telephone, description, propRefered);
	}

	public ContactRequest(final Integer iD, final String name,
			final String email, final String telephone,
			final String description, final Property propRefered) {
		this.username = name;
		this.email = email;
		this.phone = telephone;
		this.comment = description;
		this.property = propRefered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.comment == null) ? 0 : this.comment.hashCode());
		result = prime * result
				+ ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result
				+ ((this.phone == null) ? 0 : this.phone.hashCode());
		result = prime * result
				+ ((this.property == null) ? 0 : this.property.hashCode());
		result = prime * result
				+ ((this.username == null) ? 0 : this.username.hashCode());
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
		if (this.comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!this.comment.equals(other.comment)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!this.phone.equals(other.phone)) {
			return false;
		}
		if (this.property == null) {
			if (other.property != null) {
				return false;
			}
		} else if (!this.property.equals(other.property)) {
			return false;
		}
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
			return false;
		}
		return true;
	}

	public String getDescription() {
		return this.comment;
	}

	public String getName() {
		return this.username;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelephone() {
		return this.phone;
	}

	public Property getPropRefered() {
		return this.property;
	}

}
