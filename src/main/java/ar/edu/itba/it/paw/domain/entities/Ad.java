package ar.edu.itba.it.paw.domain.entities;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "ads")
public class Ad extends PersistentEntity {

	private String url;
	private int weight;

	Ad() {

	}

	public Ad(final String externalUrl, final int weight) {
		this.url = externalUrl;
		this.weight = weight;
	}

	public String getUrl() {
		return this.url;
	}

	public int getWeight() {
		return this.weight;
	}

}
