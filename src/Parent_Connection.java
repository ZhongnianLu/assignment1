import java.util.ArrayList;

//this class represents parent connection, it is a subclass of Connection
public class Parent_Connection extends Connection {

	// difference between parent relationship and friend relationship is child profile
	Profile child;
	
	public Parent_Connection(Profile person1, Profile person2, Profile child) {
		super(person1, person2);
		
		this.child=child;
	}
	
	public Profile getChild() {
		return child;
	}
	
	

	public boolean check(ArrayList<Connection> c_list) {
		
		boolean parent_check=false;
		
		
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
				parent_check=true;
			}
		}
		
		return parent_check;
		
	}
	
	

}
