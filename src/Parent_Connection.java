import java.util.ArrayList;

/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class represents parent connection, it requires a couple connection.
 */


public class Parent_Connection extends Connection {

	// difference between parent relationship and friend relationship is child profile
	Profile child;
	
	ArrayList<Profile> linked_person=new ArrayList<Profile>();
	
	
	//Constructor should include a child in parent connection.
	public Parent_Connection(Profile person1, 
			                 Profile person2, 
			                 Profile child) {
		
		super(person1, person2);
		
		this.child=child;
	}
	
	
	//Access information about child 
	public Profile getChild() {
	
		return child;
	}
	
	
	
	//Method to access both parents and child included in a connection.
	public ArrayList<Profile> getProfileInside(){
		
		linked_person.add(getPerson1());
		
		linked_person.add(getPerson2());

		linked_person.add(getChild());
		
		return linked_person;

	} 
	

	
    // Check whether added parent is valid or not
	public boolean check(ArrayList<Connection> c_list) {
		
		boolean success = false;
		
		//Go though the whole connection list to check if person1 and person2 are a couple.
		for(int i = 0;i < c_list.size();i++) {
			
			Profile person_x;
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			boolean connection_check = false;
			
			person_x = c_list.get(i).getPerson1();
			
			person_y = c_list.get(i).getPerson2();
			
			
			if(person_x.getID() == getPerson1().getID() 
					&& person_y.getID() == getPerson2().getID()) {
				
				connection_check = true;
			
			}
			
			if(person_x.getID() == getPerson2().getID() 
					&& person_y.getID() == getPerson1().getID()) {
				
				connection_check = true;
			
			}
			
			//check whether the connection we found is a friend connection
			if(c_list.get(i) instanceof Couple_Connection 
					&& connection_check == true) {
				
				success = true;
			}
		}	
		
		return success;
	}
	
	
	
	// Show difference with normal connection due to the involvement of child.
	 public boolean repeat_check(ArrayList<Connection> c_list) {
		
		boolean repeat = false;
		
		boolean child_repeat = false;
		
		for(int i = 0;i < c_list.size();i++) {
			
			if(c_list.get(i) instanceof Parent_Connection) {
			
    			if(c_list.get(i).getChild().getID() == getChild().getID()){
				
	    			child_repeat = true;
			
		    	}
			
    		 	if(c_list.get(i).getPerson1().getID() == getPerson1().getID() 
	    				&& c_list.get(i).getPerson2().getID() == getPerson2().getID()
		    			&& child_repeat == true){
				
			    	repeat = true;
	    			System.out.println("Repeated");
		    	}
			
    			if(c_list.get(i).getPerson2().getID() == getPerson1().getID() 
	    				&& c_list.get(i).getPerson1().getID() == getPerson2().getID()
		    			&& child_repeat == true){
			
	    			repeat = true;
		    		System.out.println("Repeated");
		    		
    			}
			}	
		}
		
		return repeat;
	}

	
	
    /* Due to the child is extra to normal connection, 
	*  this method check three profiles in parent connection and override method in super class
	*/
	public  boolean in (Profile target){
		
		boolean in = false;
		
		if(getPerson1().getID() == target.getID() 
				|| getPerson2().getID() == target.getID()
				|| getChild().getID() == target.getID()) {
			
			in = true;
		}	
		
		return in;
		
	}

}
