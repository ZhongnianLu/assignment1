import java.util.ArrayList;

// this class is an abstract class representing different types of connection

public abstract class Connection {
	
	//in this task, assume all connections based on two people
	private Profile person1;
	private Profile person2;
	
	
	
	public Connection(Profile person1, Profile person2) {
		this.person1=person1;
		this.person2=person2;
	}
	
	public Connection(Couple_Connection couple, Profile child) {
	}
	
	public Connection(Parent_Connection connection1, Parent_Connection connection2) {
	}

	public Profile getPerson1() {
		return person1;
	}
	
	public Profile getPerson2() {
		return person2;
	}

	public Profile getChild() {
		return null;
	}
	

	public abstract boolean check(ArrayList<Connection> c_list) ;

}
