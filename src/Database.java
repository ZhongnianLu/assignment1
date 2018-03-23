import java.util.ArrayList;

public class Database {
	
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	ArrayList<Connection> conns = new ArrayList<Connection>();
	//Profile Bob = new Adult("bob", "Starlet 99", 35);
	//public void main(String[] args) {
		
		Profile Bob = new Profile("bob", "at work", 35);
		
		Profile James = new Profile("James", "eating KFC", 90);
		Profile Mary = new Profile("Mary", "shopping", 60);
		Profile Lisa = new Profile("Lisa", "at the tennis", 65);
		Profile Evan = new Profile("Evan", "reading", 12);
		Profile Veronica = new Profile("Veronica", "contemplating life", 6);
		
		
	//}
	
	public ArrayList<Profile> readProfiles(){		
		//System.out.println("returned profiles");
		Bob.setID(1);
		James.setID(2);
		Mary.setID(3);
		Lisa.setID(4);
		Evan.setID(5);
		Veronica.setID(6);
		profiles.add(Bob);
		profiles.add(James);
		profiles.add(Mary);
		profiles.add(Lisa);
		profiles.add(Evan);
		profiles.add(Veronica);
		return profiles;
		
	}
		
		
	
	public ArrayList<Connection> readConnections(){		
		//System.out.println("returned conns");
		conns.add(new Friend_Connection(Bob, Mary));
		conns.add(new Friend_Connection(Lisa, James));
		conns.add(new Couple_Connection(James, Mary));
		conns.add(new Couple_Connection(Bob, Lisa));
		
		conns.add(new Parent_Connection(Bob, Lisa, Veronica));
		conns.add(new Parent_Connection(James,Mary, Evan));
		return conns;
	}
	
	
	
	
}
