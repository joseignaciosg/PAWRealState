package ar.edu.itba.it.paw.web.command;

public class ContactRequestForm {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String description;
	private Integer propertyId;

	public ContactRequestForm() {

	}

	public ContactRequestForm(final String first_name, final String last_name,
			final String email, final String phone, final String description,
			final Integer property_id) {
		super();
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.propertyId = property_id;
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

	public Integer getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(final Integer propertyId) {
		this.propertyId = propertyId;
	}

	@Override
	public String toString() {
		return "ContactRequestForm [first_name=" + this.firstName
				+ ", last_name=" + this.lastName + ", email=" + this.email
				+ ", phone=" + this.phone + ", description=" + this.description
				+ ", property_id=" + this.propertyId + "]";
	}

}
