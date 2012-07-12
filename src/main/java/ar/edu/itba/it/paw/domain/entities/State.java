package ar.edu.itba.it.paw.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class State extends PersistentEntity {

	@Column(name = "date")
	private Date date;

	@Column(name = "previous")
	private String previous;

	@Column(name = "actual")
	private String actual;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	State() {

	}

	State(final Date date, final String previous, final String next) {
		this.date = date;
		this.previous = previous;
		this.actual = next;
	}

	State(final Date date, final String previous, final String next,
			final Property property) {
		this.date = date;
		this.previous = previous;
		this.actual = next;
		this.property = property;
	}

	public Property getProperty() {
		return this.property;
	}

	public String getPrevious() {
		return this.previous;
	}

	public Date getDate() {
		return this.date;
	}

	public String getActual() {
		return this.actual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.actual == null) ? 0 : this.actual.hashCode());
		result = prime * result
				+ ((this.previous == null) ? 0 : this.previous.hashCode());
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
		final State other = (State) obj;
		if (this.actual == null) {
			if (other.actual != null) {
				return false;
			}
		} else if (!this.actual.equals(other.actual)) {
			return false;
		}
		if (this.previous == null) {
			if (other.previous != null) {
				return false;
			}
		} else if (!this.previous.equals(other.previous)) {
			return false;
		}
		return true;
	}

}
