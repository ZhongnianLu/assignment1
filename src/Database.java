import java.util.ArrayList;

public class Database {
	
	public ArrayList<Profile> readProfiles(){
		//ProfileManager Profiles = new ProfileManager();
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		//ConnectionManager connections = new ConnectionManager();
		Profile Bob = new Adult("bob", "Starlet 99", 35);
		Profile James = new Adult("James", "BMW 05", 90);
		Profile Mary = new Adult("Mary", "Holden 03", 60);
		Profile Lisa = new Adult("Lisa", "Camry 04", 65);
		profiles.add(Bob);
		profiles.add(James);
		profiles.add(Mary);
		profiles.add(Lisa);
		profiles.add( new Child("Evan", "Jaguar 06", 12, Bob, Mary));
		profiles.add( new Child("Veronica", "Subaru 02", 6, James, Lisa));
		
		return profiles;
	}
		
		
	
	public ArrayList<Connection> readConns(){
		
		ArrayList<Connection> conns = new ArrayList<Connection>();
	    //initialise and add connections
		
		return conns;
	
	
}
	
	
	
	
}
