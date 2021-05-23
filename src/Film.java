

import java.util.ArrayList;

public class Film {
	//this class is parent class for all films categories
	//ratingScore is an array. it has only contains userids and ratingpoints
	//While the odd elements of the ratingScore are the userids, the even elements are the points given by that userid.
	//for example ratingScore[0]=userid; ratingScore[1]=rating point
	private ArrayList<Integer> ratingScore = new ArrayList<>();
	private int id = -1;
	private String title = "";
	private String language = "";
	private int runtime = -1; //movie duration
	private String country = "";
	private int[] directorList = {};
	private int[] performers = {};
	
	public String getRatingScore() {
		double rs = 0;
		for(int i=1;i<ratingScore.size();i+=2) {
			rs+=ratingScore.get(i);
		}
		if(ratingScore.size()==0) {
			return "0";
		}else {
			rs=Math.round((rs*2)/ratingScore.size()*10.0)/10.0;
			if(rs%1==0){
				return String.valueOf(Math.round(rs));
			}
			return String.valueOf(rs);
		}
		
	}
	
	public int getRatingScoreSize() {
		//It shows how many users rated this movie.
		return this.ratingScore.size()/2;
	}

	public void addRatingScore(int userId,int rate) {
		this.ratingScore.add(userId);
		this.ratingScore.add(rate);
		
	}
	//to change movie's rate point
	public void editRatingScore(int userId,int rate) {
		for(int i=0;i<this.ratingScore.size();i++) {
			if (this.ratingScore.get(i)==userId) {
				this.ratingScore.set(i, userId);
				this.ratingScore.set(i+1, rate);
			}
		}
	}
	//to remove movie's rate point
	public void removeRatingScore(int userId) {
		for(int i=0;i<this.ratingScore.size();i++) {
			if(this.ratingScore.get(i)==userId){
				this.ratingScore.remove(i+1);
				this.ratingScore.remove(i);
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int[] getDirectorList() {
		return directorList;
	}

	public void setDirectorList(int[] directorList) {
		this.directorList = directorList;
	}

	public int[] getPerformers() {
		return performers;
	}

	public void setPerformers(int[] performers) {
		this.performers = performers;
	}

}
