package ar.edu.itba.it.paw.domain.repositories.api;

import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

public class RoomSearch {

	private RoomType type;
	private int minSize;
	private int maxSize;

	public RoomSearch(final RoomType type, final int minSize, final int maxSize) {
		this.type = type;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	public RoomSearch(final RoomType type) {
		this(type, 0, 0);
	}

	public RoomType getType() {
		return this.type;
	}

	public int getMinSize() {
		return this.minSize;
	}

	public int getMaxSize() {
		return this.maxSize;
	}

}
