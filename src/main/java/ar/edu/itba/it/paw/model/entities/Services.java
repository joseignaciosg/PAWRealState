package ar.edu.itba.it.paw.model.entities;

public class Services {

	private boolean cable;
	private boolean telephone;
	private boolean swimmingpool;
	private boolean lobby;
	private boolean paddle;
	private boolean quincho;

	public Services() {
	}

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
