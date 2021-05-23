

public class FeatureFilm extends Film {
	//FeatureFilm also has extra releaseDate,budget, writers, types values
	private String releaseDate = "";
	private long budget = -1;
	private int[] writers = {};
	private String[] types = {};

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
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
