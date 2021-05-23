

public class ShortFilm extends Film {
	//FeatureFilm also has extra releaseDate, writers, types values
	private String releaseDate = "";
	private int[] writers = {};
	private String[] types = {};

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
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
