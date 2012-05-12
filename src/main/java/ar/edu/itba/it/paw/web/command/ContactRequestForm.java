package ar.edu.itba.it.paw.web.command;

import ar.edu.itba.it.paw.domain.entities.ContactRequest;
import ar.edu.itba.it.paw.domain.entities.Property;

public class ContactRequestForm implements BuilderForm<ContactRequest> {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;
	private Property property;

	public ContactRequestForm() {

	}

	public ContactRequestForm(final String first_name, final String last_name,
			final String email, final String phone, final String description,
			final Property property) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.setProperty(property);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Property getProperty() {
		return this.property;
	}

	public void setProperty(final Property property) {
		this.property = property;
	}

	public ContactRequest build() {
		return new ContactRequest(this.firstName + this.lastName, this.email,
				this.phone, this.description, this.property);
	}
}
