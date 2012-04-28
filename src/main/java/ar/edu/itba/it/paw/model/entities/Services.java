package ar.edu.itba.it.paw.model.entities;

public class Services {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.cable ? 1231 : 1237);
		result = prime * result + (this.lobby ? 1231 : 1237);
		result = prime * result + (this.paddle ? 1231 : 1237);
		result = prime * result + (this.quincho ? 1231 : 1237);
		result = prime * result + (this.swimmingpool ? 1231 : 1237);
		result = prime * result + (this.telephone ? 1231 : 1237);
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
		final Services other = (Services) obj;
		if (this.cable != other.cable) {
			return false;
		}
		if (this.lobby != other.lobby) {
			return false;
		}
		if (this.paddle != other.paddle) {
			return false;
		}
		if (this.quincho != other.quincho) {
			return false;
		}
		if (this.swimmingpool != other.swimmingpool) {
			return false;
		}
		if (this.telephone != other.telephone) {
			return false;
		}
		return true;
	}

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
