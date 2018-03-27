import java.util.ArrayList;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class creates restrictions for adding couple connection.
 * 
 */

public class Couple_Connection extends Connection{

	//Constructor from super class	
	public Couple_Connection(Profile person1, Profile person2) {
		
		super(person1, person2);
	}

	
	//Override check method from super class. 
	//Each person in a couple can't exist in other couple connections.
	@Override
	public boolean check(ArrayList<Connection> c_list) {

		boolean success = true;
		
		for(int i = 0;i < c_list.size();i++) {
			
			Profile person_x;	
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			person_x = c_list.get(i).getPerson1();
			
			person_y = c_list.get(i).getPerson2();
			
			if(c_list.get(i) instanceof Couple_Connection) {
				
		    	if(person_x.getID() == getPerson1().getID() 
					|| person_y.getID() == getPerson2().getID()) {
				
			    	success = false;
				
			    }
			
	    		if(person_x.getID() == getPerson2().getID() 
					|| person_y.getID() == getPerson1().getID()) {
				
    				success = false;
			
		        }
		    }
		}
		
		return success;
	}
}

	
	

