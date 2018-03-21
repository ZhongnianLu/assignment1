import java.util.ArrayList;

public class Couple_Connection extends Connection{

	public Couple_Connection(Profile person1, Profile person2) {
		super(person1, person2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean check(ArrayList<Connection> c_list) {

		
		boolean success=true;
		
		
		
		for(int i=0;i<c_list.size();i++) {
			
			Profile person_x;
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			
			person_x=c_list.get(i).getPerson1();
			
			person_y=c_list.get(i).getPerson2();
			
			if(c_list.get(i) instanceof Couple_Connection) {
			if(person_x.getID()==getPerson1().getID()||person_y.getID()==getPerson2().getID()) {
				success=false;
			}
			
			if(person_x.getID()==getPerson2().getID()||person_y.getID()==getPerson1().getID()) {
				success=false;
			}
			}
			}
		
		
		return success;
	}


	/*@Override
	public String search(Profile target) {


		String screen=null;

			if(getPerson1().getID()==target.getID()||getPerson2().getID()==target.getID()) {
				screen+="\n<Couple>  "+getPerson1().getName()+" --- "+getPerson2().getName();
			}
		
		return screen;
		
	}	*/
	}

	
	

