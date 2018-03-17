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
	
	

}
