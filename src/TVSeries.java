

public class TVSeries extends Film {
	//TVSeries also has extra startDate,endDate,seasonNumber,episodeNumber, writers and types values
	private String startDate = "";
	private String endDate = "";
	private int seasonNumber = -1;
	private int episodeNumber = -1;
	private int[] writers = {};
	private String[] types = {};

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public int[] getWriters() {
		return writers;
	}

	public void setWriters(int[] writers) {
		this.writers = writers;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

}
