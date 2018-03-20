<<<<<<< HEAD
import java.util.ArrayList;

/*
 * This class contains all profiles
 * 
 */

public class ProfileManager {
	
	// list of all profiles
	ArrayList<Profile> profiles=new ArrayList<Profile>();

	public ArrayList<Profile> get_Plist(){
		return profiles;
	}
	
	
	//method to add new profile in profile list
	//sets the ID based on index in array
	public void addProfile(Profile add) {
		int ID = profiles.size();
		add.setID(ID);
		profiles.add(add);
	}
	
	
	public void removeProfile(Profile del) {
		int index = profiles.indexOf(del);
		profiles.remove(del);
	}
	
	
	
	
	
	
}
=======
import java.util.ArrayList;

/*
 * This class contains all profiles
 * 
 */

public class ProfileManager {
	
	// list of all profiles
	ArrayList<Profile> profiles=new ArrayList<Profile>();

	public ArrayList<Profile> get_Plist(){
		return profiles;
	}
	
	
	//method to add new profile in profile list
	//sets the ID based on index in array
	public void addProfile(Profile add) {
		int ID = profiles.size();
		add.setID(ID);
		profiles.add(add);
	}
	
	
	public void removeProfile(Profile del) {
		int index = profiles.indexOf(del);
		profiles.remove(del);
	}
	
	
	
	
	
	
}
>>>>>>> e9c1847a423b5097e63beba66d56749ec811e688
