import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class contains all profiles
 * 
 */

public class ProfileManager {
	ArrayList<Profile> profiles =new ArrayList<Profile>();
	
	public ProfileManager(ArrayList<Profile> profs) {
		this.profiles = profs;
	}
	
	public ProfileManager() {
		this.profiles = new ArrayList<Profile>();
	}
	
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
		ArrayList<Profile> tempList = new ArrayList<Profile>();
		for (int i = 0; i < this.get_Plist().size(); i++) {
			Profile temp = this.get_Plist().get(i);
			if (temp.getAge() >= 16) {
				tempList.add(temp);
			}
		}
		
		return tempList;
	}
	
	public Profile selectProfile(String title) throws IOException{
		Profile selectProf = null;
		ArrayList<Profile> plist = this.get_Plist();
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
	
	public Profile askInfo() throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("\nPlease enter your name : ");
		String name = scan.nextLine();
		if (this.uniqueName(name) != true) throw new IOException("\nError: Name must be unique");
	
		
		System.out.println("\nPlease enter your age : "); //enter age
		int age = scan.nextInt();
		if (age <= 0) throw new IOException("\nError: Age must be positve");
		
		scan.nextLine();
		System.out.println("Please enter a status update : ");   // enter a status
		String status = scan.nextLine();
		
		Profile person = new Profile(name, status, age); //create a profile object
		person.setID(this.get_Plist().size()); //set ID based on number of profiles
		
		return person;
	}


}


