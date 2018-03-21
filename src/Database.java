import java.util.ArrayList;

public class Database {
	
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	ArrayList<Connection> conns = new ArrayList<Connection>();
	//Profile Bob = new Adult("bob", "Starlet 99", 35);
	
	//public void main(String[] args) {
		
		Profile Bob = new Adult("bob", "Starlet 99", 35);
		Profile James = new Adult("James", "BMW 05", 90);
		Profile Mary = new Adult("Mary", "Holden 03", 60);
		Profile Lisa = new Adult("Lisa", "Camry 04", 65);
		Profile Evan = new Child("Evan", "Jaguar 06", 12);
		Profile Veronica = new Child("Veronica", "Subaru 02", 6);
		
		
	//}
	
	public ArrayList<Profile> readProfiles(){		
		System.out.println("returned profiles");
		profiles.add(Bob);
		profiles.add(James);
		profiles.add(Mary);
		profiles.add(Lisa);
		profiles.add(Evan);
		profiles.add(Veronica);
		return profiles;
		
	}
		
		
	
	public ArrayList<Connection> readConnections(){		
		System.out.println("returned conns");
		conns.add(new Friend_Connection(Bob, Mary));
		conns.add(new Friend_Connection(Bob, James));
		conns.add(new Couple_Connection(James, Mary));
		conns.add(new Parent_Connection(Bob, Mary, Veronica));
		conns.add(new Parent_Connection(James, Mary, Evan));
		return conns;
	}
	
	
	
	
}
