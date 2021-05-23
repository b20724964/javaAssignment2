

import java.util.ArrayList;
import java.util.Arrays;

public class Peoples implements Reader{
	//Peoples class has one operation which travels over the result and and adds person by categories.
	public String[][] result;
	public ArrayList<Actor> actors = new ArrayList<>();
	public ArrayList<ChildActor> childActors = new ArrayList<>();
	public ArrayList<Director> directors = new ArrayList<>();
	public ArrayList<StuntPerformer> stuntPerformers = new ArrayList<>();
	public ArrayList<User> users = new ArrayList<>();
	public ArrayList<Writer> writers = new ArrayList<>();
	//While the odd elements of the allPeoplesId are the people categories, the even elements are the personid given by that people categories.
	//for example allPeoplesId[0]="User:"; allPeoplesId[1]="470"
	public ArrayList<String> allPeoplesId=new ArrayList<>();
	//While the odd elements of the allIdsAndNames are the filmids, the even elements are the film titles given by that filmids
	//for example allIdsAndNames[0]=""; allIdsAndNames[1]="Amilie"
	public ArrayList<String> allIdsAndNames= new ArrayList<>();
	
	public Peoples(String fileString) {
		this.result=FileReader(fileString);
		for(int i=0;i<result.length;i++) {
			allPeoplesId.add(result[i][0]);
			allPeoplesId.add(result[i][1]);
			allIdsAndNames.add(result[i][1]);
			allIdsAndNames.add(result[i][2]+" "+result[i][3]);
			if(result[i][0].equals("Actor:")) {				
				Actor actor=new Actor();
				actor.setId(Integer.parseInt(result[i][1]));
				actor.setName(result[i][2]);
				actor.setSurname(result[i][3]);
				actor.setCountry(result[i][4]);
				actor.setHeight(result[i][5]);
				this.actors.add(actor);
			}else if(result[i][0].equals("ChildActor:")){
				ChildActor childActor= new ChildActor();
				childActor.setId(Integer.parseInt(result[i][1]));
				childActor.setName(result[i][2]);
				childActor.setSurname(result[i][3]);
				childActor.setCountry(result[i][4]);
				childActor.setAge(Integer.parseInt(result[i][5]));
				this.childActors.add(childActor);
			}else if(result[i][0].equals("Director:")) {
				Director director= new Director();
				director.setId(Integer.parseInt(result[i][1]));
				director.setName(result[i][2]);
				director.setSurname(result[i][3]);
				director.setCountry(result[i][4]);
				director.setAgent(result[i][5]);
				this.directors.add(director);
			}else if (result[i][0].equals("Writer:")) {
				Writer writer=new Writer();
				writer.setId(Integer.parseInt(result[i][1]));
				writer.setName(result[i][2]);
				writer.setSurname(result[i][3]);
				writer.setCountry(result[i][4]);
				writer.setStyle(result[i][5]);
				this.writers.add(writer);
			}else if (result[i][0].equals("StuntPerformer:")) {
				StuntPerformer stuntPerformer=new StuntPerformer();
				stuntPerformer.setId(Integer.parseInt(result[i][1]));
				stuntPerformer.setName(result[i][2]);
				stuntPerformer.setSurname(result[i][3]);
				stuntPerformer.setCountry(result[i][4]);
				stuntPerformer.setHeight(result[i][5]);
				stuntPerformer.setActorList(Arrays.stream(result[i][6].split(",")).mapToInt(Integer::parseInt).toArray());
				this.stuntPerformers.add(stuntPerformer);
			}else if (result[i][0].equals("User:")) {
				User user=new User();
				user.setId(Integer.parseInt(result[i][1]));
				user.setName(result[i][2]);
				user.setSurname(result[i][3]);
				user.setCountry(result[i][4]);
				this.users.add(user);
			}else {
				System.out.println("undefined person category");
			}
		}
	}
}
