
import java.util.ArrayList;

public class User extends People{
	//User also has extra rateList value
	//this is the only person category who can vote movies
	//While the odd elements of the rateList are the filmid, the even elements are the rate given by that user.
	//for example rateList[0]=113; rateList[1]=10
	private ArrayList<Integer> rateList=new ArrayList<>();

	public ArrayList<Integer> getRateList() {
		return rateList;
	}

	public void addRate(int filmId,int rate) {
		this.rateList.add(filmId);
		this.rateList.add(rate);
		
	}
	
	public void editRate(int filmId,int rate) {
		for(int i=0;i<this.rateList.size();i++) {
			if (this.rateList.get(i)==filmId) {
				this.rateList.set(i, filmId);
				this.rateList.set(i+1, rate);
			}
		}
	}

	public void removeRate(int filmId) {
		for(int i=0;i<this.rateList.size();i++) {
			if(this.rateList.get(i)==filmId){
				this.rateList.remove(i+1);
				this.rateList.remove(i);
			}
		}
	}
}
