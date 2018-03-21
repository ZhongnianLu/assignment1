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
	public void addProfile(Profile add) {
		profiles.add(add);
	}
	
	//method to remove a profile object from profile list
	public void removeProfile(Profile del) {
		profiles.remove(del);
	}
	
	
	public void importList(ArrayList<Profile> profiles) {
		int len = profiles.size();
		
		for (int i = 0; i < len; i++) {
			this.addProfile(profiles.get(i));
		}
		
	}
	
	// checks if a given name is unique among the profiles, return true means it is unique
	public boolean uniqueName(String name) {
		boolean unique = true;
		
		for (int i = 0; i < this.get_Plist().size(); i++) { //iterates over all profile objects
			if (name.equals(this.get_Plist().get(i))) {     //checks if names match
				unique = false;
			}
		}
		return unique; 
	}
	
	
}


