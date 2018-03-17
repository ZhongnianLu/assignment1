// this class is an abstract class representing different types of connection

public abstract class Connection {
	
	//in this task, assume all connections based on two people
	Profile person1;
	Profile person2;
	
	public Connection(Profile person1, Profile person2) {
		this.person1=person1;
		this.person2=person2;
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

}
