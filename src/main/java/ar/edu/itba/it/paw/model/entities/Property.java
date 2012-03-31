package ar.edu.itba.it.paw.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Property implements Entity {

	private Integer ID;
	private boolean dirty;
	private String type;
	private String operation;
	private String neighborhood;
	private String address;
	private String price;
	private String spaces;
	private String coveredArea;
	private String freeArea;
	private String age;
	private Services service;
	private String description;
	private List<Photo> photos;

	public Property(final Integer iD, final boolean dirty, final String type,
			final String operation, final String neighborhood,
			final String address, final String price, final String spaces,
			final String coveredArea, final String freeArea, final String age,
			final Services service, final String description) {
		this.ID = iD;
		this.dirty = dirty;
		this.type = type;
		this.operation = operation;
		this.neighborhood = neighborhood;
		this.address = address;
		this.price = price;
		this.spaces = spaces;
		this.coveredArea = coveredArea;
		this.freeArea = freeArea;
		this.age = age;
		this.service = service;
		this.description = description;
		this.photos = new ArrayList<Photo>();
	}

	public Integer getID() {
		return this.ID;
	}

	public void setID(final Integer iD) {
		this.ID = iD;
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(final boolean dirty) {
		this.dirty = dirty;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(final String operation) {
		this.operation = operation;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(final String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(final String price) {
		this.price = price;
	}

	public String getSpaces() {
		return this.spaces;
	}

	public void setSpaces(final String spaces) {
		this.spaces = spaces;
	}

	public String getCoveredArea() {
		return this.coveredArea;
	}

	public void setCoveredArea(final String coveredArea) {
		this.coveredArea = coveredArea;
	}

	public String getFreeArea() {
		return this.freeArea;
	}

	public void setFreeArea(final String freeArea) {
		this.freeArea = freeArea;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(final String age) {
		this.age = age;
	}

	public Services getService() {
		return this.service;
	}

	public void setService(final Services service) {
		this.service = service;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void addPhoto(final Photo photo) {
		this.photos.add(photo);
	}

	public void removePhoto(final Photo photo) {
		this.photos.remove(photo);
	}

	private class Services {

		private boolean cable;
		private boolean telephone;
		private boolean swimmingpool;
		private boolean lobby;
		private boolean paddle;
		private boolean quincho;

		public Services(final boolean cable, final boolean telephone,
				final boolean swimmingpool, final boolean lobby,
				final boolean paddle, final boolean quincho) {
			super();
			this.cable = cable;
			this.telephone = telephone;
			this.swimmingpool = swimmingpool;
			this.lobby = lobby;
			this.paddle = paddle;
			this.quincho = quincho;
		}

		public boolean isCable() {
			return this.cable;
		}

		public void setCable(final boolean cable) {
			this.cable = cable;
		}

		public boolean isTelephone() {
			return this.telephone;
		}

		public void setTelephone(final boolean telephone) {
			this.telephone = telephone;
		}

		public boolean isSwimmingpool() {
			return this.swimmingpool;
		}

		public void setSwimmingpool(final boolean swimmingpool) {
			this.swimmingpool = swimmingpool;
		}

		public boolean isLobby() {
			return this.lobby;
		}

		public void setLobby(final boolean lobby) {
			this.lobby = lobby;
		}

		public boolean isPaddle() {
			return this.paddle;
		}

		public void setPaddle(final boolean paddle) {
			this.paddle = paddle;
		}

		public boolean isQuincho() {
			return this.quincho;
		}

		public void setQuincho(final boolean quincho) {
			this.quincho = quincho;
		}

	}

}
