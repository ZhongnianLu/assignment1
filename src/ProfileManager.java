import java.util.ArrayList;

/*
 * This class contains all profiles
 * 
 */

public class ProfileManager {
	
	// list of all profiles
	ArrayList<Profile> p_list=new ArrayList<Profile>();

	public ArrayList<Profile> get_Plist(){
		return p_list;
	}
	
	
	//method to add new profile in profile list
	public void addProfile(Profile add) {
		
		p_list.add(add);
		
		
	}
}
