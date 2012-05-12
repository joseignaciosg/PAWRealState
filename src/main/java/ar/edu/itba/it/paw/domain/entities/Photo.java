package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photos")
public class Photo extends PersistentEntity {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.agency == null) ? 0 : this.agency.hashCode());
		result = prime * result
				+ ((this.property == null) ? 0 : this.property.hashCode());
		result = prime * result
				+ ((this.type == null) ? 0 : this.type.hashCode());
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
		final Photo other = (Photo) obj;
		if (this.agency == null) {
			if (other.agency != null) {
				return false;
			}
		} else if (!this.agency.equals(other.agency)) {
			return false;
		}
		if (this.property == null) {
			if (other.property != null) {
				return false;
			}
		} else if (!this.property.equals(other.property)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	private byte[] data = null;
	private String type;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	@OneToOne
	@JoinColumn(name = "agent_id")
	private RealStateAgency agency;

	public Photo() {

	}

	public Photo(final byte[] data, final String type) {
		this.data = data;
		this.type = type;
	}

	public Photo(final byte[] data, final String type, final Property property) {
		this.data = data;
		this.type = type;
		this.property = property;
	}

	public byte[] getData() {
		return this.data;
	}

	public String getType() {
		return this.type;
	}

	public Property getProperty() {
		return this.property;
	}

	public RealStateAgency getAgency() {
		return this.agency;
	}

	public void setAgency(final RealStateAgency agency) {
		this.agency = agency;
	}

}
