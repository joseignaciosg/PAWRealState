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
