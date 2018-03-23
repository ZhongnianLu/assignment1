import java.io.IOException;
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
		
		for (int i = 0; i < this.get_Plist().size(); i++) { //reset the list
			this.removeProfile(this.get_Plist().get(i));
		}
		
		for (int i = 0; i < len; i++) {
			this.addProfile(profiles.get(i));
		}
		
	}
	
	//checks if a given name is unique among the profiles, return true means it is unique
	public boolean uniqueName(String name) {
		boolean unique = true;
		name = name.toLowerCase();
		
		for (int i = 0; i < this.get_Plist().size(); i++) { //iterates over all profile objects
			String str = this.get_Plist().get(i).getName().toLowerCase();
			if (name.equals(str)) {     //checks if names match
				unique = false;
			}
		}
		return unique; 
	}
	
	public ArrayList<Profile> getAdults() {
		ArrayList<Profile> tempList = this.get_Plist();
		for (int i = 0; i < tempList.size(); i++) {
			Profile temp = tempList.get(i);
			if (temp.getAge() < 16) {
				tempList.remove(temp);
			}
		}
		
		ProfileManager pm = new ProfileManager();
		pm.importList(tempList);
		
		return tempList;
	}
	
	public Profile selectProfile(String title) throws IOException{
		//Scanner scan = new Scanner(System.in);
		//System.out.println("Search Name : ");
		//String searchTerm = scan.nextLine();
		Profile selectProf = null;
		ArrayList<Profile> plist = this.get_Plist();
		//ArrayList<Profile> searched = searchNames(searchTerm, profiles.get_Plist());
		//System.out.println("size of searched is " + searched.size());
		int option;
		ArrayList<String> names = this.listNames();
		names.add("Exit to Main Menu");
		
		do{
		option = Menu.display_Menu(title, names);
		if ( option >= 1 && option <= names.size()-1) {
			selectProf = plist.get(option-1);
		}
		break;
		}while(option != 0);
		return  selectProf;
		
	}
	
	public ArrayList<String> listNames() {
		ArrayList<String> names = new ArrayList<String>();
		String name;
		for (int i = 0; i < this.get_Plist().size(); i++) {
			name = this.get_Plist().get(i).getName();
			names.add(name);
		}				
		
		return names;
	}
	
	public void printp() {
    	for (int i = 0; i < this.get_Plist().size(); i++) {
    		System.out.println(this.get_Plist().get(i).getName());
    	}
    }
	
	
	
}


