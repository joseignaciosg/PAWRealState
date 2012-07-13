package ar.edu.itba.it.paw.domain.entities;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.apache.commons.lang.*;

@Entity
@Table(name = "states")
public class State extends PersistentEntity {

	public enum StateType {
		RESERVED("Reservado"), UNRESERVED("Sin Reservar"), SOLD("Vendido"), ONSALE(
				"En venta"), VISIBLE("Visible"), UNVISIBLE("Oculto");

		private String prefix;

		StateType(final String prefix) {
			this.prefix = prefix;
		}

		public String getPrefix() {
			return this.prefix;
		}

		@Override
		public String toString() {
			return this.getPrefix();
		}
	}

	@Column(name = "date")
	private Date date;

	@Enumerated(EnumType.STRING)
	private StateType previous;

	@Enumerated(EnumType.STRING)
	private StateType actual;

	@ManyToOne
	@JoinColumn(name = "property_id")
	private Property property;

	State() {

	}

	State(final StateType previous, final StateType actual) {
		Validate.notNull(previous);
		Validate.notNull(actual);
		this.date = Calendar.getInstance().getTime();
		this.previous = previous;
		this.actual = actual;
	}

	State(final Date date, final StateType previous, final StateType actual,
			final Property property) {
		Validate.notNull(date);
		Validate.notNull(previous);
		Validate.notNull(actual);
		Validate.notNull(property);
		this.date = date;
		this.previous = previous;
		this.actual = actual;
		this.property = property;
	}

	public Property getProperty() {
		return this.property;
	}

	public StateType getPrevious() {
		return this.previous;
	}

	public Date getDate() {
		return this.date;
	}

	public StateType getActual() {
		return this.actual;
	}

	void setProperty(final Property property) {
		this.property = property;
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
