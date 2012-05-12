package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class RealStateAgency extends User {

	@Column(name = "agency_name")
	private String agencyName;

	@OneToOne(mappedBy = "agency")
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	private Photo photo;

	public RealStateAgency() {

	}

	public RealStateAgency(final String name, final String surname,
			final String mail, final String telephone, final String username,
			final String password, final String agencyName, final Photo photo) {
		super(name, surname, mail, telephone, username, password);

		this.agencyName = agencyName;
		this.photo = photo;
		this.photo.setAgency(this);
	}

	public String getAgencyName() {
		return this.agencyName;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((this.agencyName == null) ? 0 : this.agencyName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RealStateAgency other = (RealStateAgency) obj;
		if (this.agencyName == null) {
			if (other.agencyName != null) {
				return false;
			}
		} else if (!this.agencyName.equals(other.agencyName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RealStateAgency [agencyName=" + this.agencyName + "]";
	}

}