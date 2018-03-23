import java.util.ArrayList;

/*An abstract class represents the concept of connection for further inheritance and polymorphism.
 * 
 * Assume all connections contains two major person (person1 and person2).
 * 
 * Include method to avoid adding any repeated connection. 
*/

public abstract class Connection {
	
	//in this task, assume all connections based on two people
	private Profile person1;
	private Profile person2;
	
	
	
	//Constructor passing two profiles
	public Connection(Profile person1, Profile person2) {
		this.person1=person1;
		this.person2=person2;
	}
	

	
	//accessor to get profiles of two persons
	public Profile getPerson1() {
		return person1;
	}
	
	
	
	public Profile getPerson2() {
		return person2;
	}

	
	
	//accessor for child in parent relationship
	public Profile getChild() {
		return null;
	}
	
	
	
    //Abstract method to check whether this connection is valid to be added. Need to be override.
	public abstract boolean check(ArrayList<Connection> c_list) ;
	
	
	
	//Check whether this connection already added in the connection list
	public boolean repeat_check(ArrayList<Connection> c_list) {
		
		boolean repeat = false;
		
		for(int i=0;i<c_list.size();i++) {
			
			if(c_list.get(i).getPerson1().getID() == getPerson1().getID() 
					&& c_list.get(i).getPerson2().getID() == getPerson2().getID()
					&& c_list.get(i).getClass().equals(getClass())){
			 
				repeat = true;
				
				System.out.println("Repeated");
			}
	
			if(c_list.get(i).getPerson2().getID() == getPerson1().getID() 
					&& c_list.get(i).getPerson1().getID() == getPerson2().getID()
					&& c_list.get(i).getClass().equals(getClass())){
				
				repeat = true;
				
				System.out.println("Repeated");
				}
			}
		
		return repeat;
	}
	
	
	
	// Get all persons' profiles within this connection
	public  ArrayList<Profile> getProfileInside(){
		
    	 ArrayList<Profile> linked_person = new ArrayList<Profile>();
		
		linked_person.add(getPerson1());
		
		linked_person.add(getPerson2());

		return linked_person;
	} 
	
	
	
	//Serve the search function: check whether the target profile is in this connection
	public  boolean in (Profile target){
		
	boolean in=false;
	
	if(getPerson1().getID() == target.getID()||getPerson2().getID() == target.getID()) {
		in = true;
	}

	return in;
	
	};
		
	


}
