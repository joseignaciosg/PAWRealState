package ar.edu.itba.it.paw.domain.repositories.api;

import ar.edu.itba.it.paw.domain.entities.Room.RoomType;

public class RoomSearch {

	private RoomType type;
	private Integer minSize;

	public void setType(final RoomType type) {
		this.type = type;
	}

	public void setMinSize(final Integer minSize) {
		this.minSize = minSize;
	}

	public void setMaxSize(final Integer maxSize) {
		this.maxSize = maxSize;
	}

	private Integer maxSize;

	public RoomSearch() {

	}

	public RoomSearch(final RoomType type, final Integer minSize,
			final Integer maxSize) {
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

	public Integer getMinSize() {
		return this.minSize;
	}

	public Integer getMaxSize() {
		return this.maxSize;
	}

}
