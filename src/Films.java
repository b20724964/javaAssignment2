

import java.util.ArrayList;
import java.util.Arrays;

public class Films implements Reader{
	//Films class has one operation which travels over the result and and adds films by categories.
	String[][] result;
	public ArrayList<Documentary> documentaries = new ArrayList<>();
	public ArrayList<FeatureFilm> featureFilms = new ArrayList<>();
	public ArrayList<ShortFilm> shortFilms = new ArrayList<>();   
	public ArrayList<TVSeries> tvSeries = new ArrayList<>();
	//While the odd elements of the allFilmsId are the film categories, the even elements are the filmids given by that film categories.
	//for example allFilmsId[0]="FeatureFilm:"; allFilmsId[1]="113"
	public ArrayList<String> allFilmsId= new ArrayList<>();
	//While the odd elements of the allFilmsIdAndTitle are the filmids, the even elements are the film titles given by that filmids
	//for example allFilmsIdAndTitle[0]="113"; allFilmsIdAndTitle[1]="Amilie"
	public ArrayList<String> allFilmsIdAndTitle= new ArrayList<>();
	
	public Films(String fileString) {
		// all txt file's values in this result 2D array
		this.result= FileReader(fileString);
		//this operations travel over the result and add films by categories.
		for(int i=0;i<result.length;i++) {
			allFilmsId.add(result[i][0]);
			allFilmsId.add(result[i][1]);
			allFilmsIdAndTitle.add(result[i][1]);
			allFilmsIdAndTitle.add(result[i][2]);
			if(result[i][0].equals("FeatureFilm:")) {
				FeatureFilm featureFilm=new FeatureFilm();
				featureFilm.setId(Integer.parseInt(result[i][1]));
				featureFilm.setTitle(result[i][2]);
				featureFilm.setLanguage(result[i][3]);
				featureFilm.setDirectorList(Arrays.stream(result[i][4].split(",")).mapToInt(Integer::parseInt).toArray());
				featureFilm.setRuntime(Integer.parseInt(result[i][5]));
				featureFilm.setCountry(result[i][6]);
				featureFilm.setPerformers(Arrays.stream(result[i][7].split(",")).mapToInt(Integer::parseInt).toArray());
				featureFilm.setTypes((result[i][8].split(",")));
				featureFilm.setReleaseDate(result[i][9].substring(6));
				featureFilm.setWriters(Arrays.stream(result[i][10].split(",")).mapToInt(Integer::parseInt).toArray());
				featureFilm.setBudget(Long.parseLong(result[i][11]));
				this.featureFilms.add(featureFilm);
			}else if(result[i][0].equals("ShortFilm:")) {
				if(Integer.parseInt(result[i][5])>40) {
					System.out.println("the duration of this short film is more than 40 minutes");
				}else {
				ShortFilm shortFilm=new ShortFilm();
				shortFilm.setId(Integer.parseInt(result[i][1]));
				shortFilm.setTitle(result[i][2]);
				shortFilm.setLanguage(result[i][3]);
				shortFilm.setDirectorList(Arrays.stream(result[i][4].split(",")).mapToInt(Integer::parseInt).toArray());
				shortFilm.setRuntime(Integer.parseInt(result[i][5]));
				shortFilm.setCountry(result[i][6]);
				shortFilm.setPerformers(Arrays.stream(result[i][7].split(",")).mapToInt(Integer::parseInt).toArray());
				shortFilm.setTypes((result[i][8].split(",")));
				shortFilm.setReleaseDate(result[i][9].substring(6));
				shortFilm.setWriters(Arrays.stream(result[i][10].split(",")).mapToInt(Integer::parseInt).toArray());
				this.shortFilms.add(shortFilm);	
				}				
			}else if(result[i][0].equals("TVSeries:")) {
				TVSeries tvSeries=new TVSeries();
				tvSeries.setId(Integer.parseInt(result[i][1]));
				tvSeries.setTitle(result[i][2]);
				tvSeries.setLanguage(result[i][3]);
				tvSeries.setDirectorList(Arrays.stream(result[i][4].split(",")).mapToInt(Integer::parseInt).toArray());
				tvSeries.setRuntime(Integer.parseInt(result[i][5]));
				tvSeries.setCountry(result[i][6]);
				tvSeries.setPerformers(Arrays.stream(result[i][7].split(",")).mapToInt(Integer::parseInt).toArray());
				tvSeries.setTypes((result[i][8].split(",")));
				tvSeries.setWriters(Arrays.stream(result[i][9].split(",")).mapToInt(Integer::parseInt).toArray());
				tvSeries.setStartDate(result[i][10].substring(6));
				tvSeries.setEndDate(result[i][11].substring(6));
				tvSeries.setSeasonNumber(Integer.parseInt(result[i][12]));
				tvSeries.setEpisodeNumber(Integer.parseInt(result[i][13]));
				this.tvSeries.add(tvSeries);				
			}else if(result[i][0].equals("Documentary:")) {
				Documentary documentary=new Documentary();
				documentary.setId(Integer.parseInt(result[i][1]));
				documentary.setTitle(result[i][2]);
				documentary.setLanguage(result[i][3]);
				documentary.setDirectorList(Arrays.stream(result[i][4].split(",")).mapToInt(Integer::parseInt).toArray());
				documentary.setRuntime(Integer.parseInt(result[i][5]));
				documentary.setCountry(result[i][6]);
				documentary.setPerformers(Arrays.stream(result[i][7].split(",")).mapToInt(Integer::parseInt).toArray());
				documentary.setReleaseDate(result[i][8].substring(6));
				this.documentaries.add(documentary);				
			}else {
				System.out.println("undefined film category");
			}
		}
	}
}
