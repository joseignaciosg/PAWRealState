package ar.edu.itba.it.paw.domain.entities;

public class realStateView {

	private Integer photoID;
	private String agencyName;
	private Integer id;
	private Integer propCount;

	public realStateView(final Integer photoID, final String agencyName,
			final Integer id, final long propCount) {
		this.photoID = photoID;
		this.agencyName = agencyName;
		this.id = id;
		this.propCount = (int) propCount;
	}

	public String getAgencyName() {
		return this.agencyName;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getPhotoID() {
		return this.photoID;
	}

	public Integer getPropCount() {
		return this.propCount;
	}
}
