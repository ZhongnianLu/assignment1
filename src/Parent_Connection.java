import java.util.ArrayList;

//this class represents parent connection, it is a subclass of Connection
public class Parent_Connection extends Connection {

	// difference between parent relationship and friend relationship is child profile
	Profile child;
	
	ArrayList<Profile> linked_person=new ArrayList<Profile>();
	
	public Parent_Connection(Profile person1, Profile person2, Profile child) {
		super(person1, person2);
		
		this.child=child;
	}
	
	public Profile getChild() {
		return child;
	}
	
	
	public  ArrayList<Profile> getProfileInside(){
		
		linked_person.add(getPerson1());
		
		linked_person.add(getPerson2());

		linked_person.add(getChild());
		
		return linked_person;
	} 
	

	
    // Check whether added parent is valid or not
	public boolean check(ArrayList<Connection> c_list) {
		
		boolean success=false;
		
		
		for(int i=0;i<c_list.size();i++) {
			
			Profile person_x;
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			boolean connection_check=false;
			
			person_x=c_list.get(i).getPerson1();
			
			person_y=c_list.get(i).getPerson2();
			
			
			if(person_x.getID()==getPerson1().getID()&&person_y.getID()==getPerson2().getID()) {
				connection_check=true;
			}
			
			if(person_x.getID()==getPerson2().getID()&&person_y.getID()==getPerson1().getID()) {
				connection_check=true;
			}
			
			//check whether the connection we found is a friend connection
			if(c_list.get(i) instanceof Couple_Connection&&connection_check==true) {
				success=true;
			}
		}
		
		return success;
		}

	
	
	
    // Due to the child is extra to normal connection, this method check three profiles in parent connection and override method in super class.
	public  boolean in (Profile target){
		
		boolean in=false;
		
		if(getPerson1().getID()==target.getID()||getPerson2().getID()==target.getID()||getChild().getID()==target.getID()) {
			in=true;
		}		
		return in;
		
		};

}
