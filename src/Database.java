import java.util.ArrayList;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains predefined data but can contain methods
 * for reading in data from another source. Methods return
 * ArrayLists to be called to feed the data to another class.
 * 
 */

public class Database {
	
	/* Create new empty ArrayLists of profiles and connections */
	ArrayList<Profile> profiles = new ArrayList<Profile>();
	ArrayList<Connection> conns = new ArrayList<Connection>();	
	
	/* Create profiles */
	Profile Bob = new Profile("bob", "at work", 35);
	Profile James = new Profile("James", "eating KFC", 90);
	Profile Mary = new Profile("Mary", "shopping", 60);
	Profile Lisa = new Profile("Lisa", "at the tennis", 65);
	Profile Evan = new Profile("Evan", "reading", 12);
	Profile Veronica = new Profile("Veronica", "contemplating life", 6);
	
	/* Set ID's of profiles and add them to profile ArrayList */
	public ArrayList<Profile> readProfiles() {		
		
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
		
		
	/* Create connections of profiles and add them to connection ArrayList */
	public ArrayList<Connection> readConnections() {		
		
		conns.add(new Friend_Connection(Bob, Mary));
		conns.add(new Friend_Connection(Lisa, James));
		conns.add(new Couple_Connection(James, Mary));
		conns.add(new Couple_Connection(Bob, Lisa));
		conns.add(new Parent_Connection(Bob, Lisa, Veronica));
		conns.add(new Parent_Connection(James,Mary, Evan));
		
		return conns;
	}
	
	
	
	
}