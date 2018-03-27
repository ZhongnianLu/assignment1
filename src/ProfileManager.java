import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains the profile list and methods
 * to manipulate the list as well as search it and 
 * other related functions
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
	
	public void addProfile(Profile add) {
		profiles.add(add);
	}
	
	public void removeProfile(Profile del) {
		profiles.remove(del);
	}
	
	/* ArrayList given to fill an empty list or override an existing list */
	public void importList(ArrayList<Profile> profiles) {
		int len = profiles.size();
		
		/* Iterate over the list to override and delete all entries */
		for (int i = 0; i < this.get_Plist().size(); i++) { //reset the list
			this.removeProfile(this.get_Plist().get(i));
		}
		
		/* Iterate over given list and add entries to main list */
		for (int i = 0; i < len; i++) {
			this.addProfile(profiles.get(i));
		}
		
	}
	
	/*checks if a given name is unique among the profiles, return true means it is unique */
	public boolean uniqueName(String name) {
		boolean unique = true;
		name = name.toLowerCase();
		
		/* Iterates over all profiles to match the given name */
		for (int i = 0; i < this.get_Plist().size(); i++) { 
			String str = this.get_Plist().get(i).getName().toLowerCase();
			
			/* If names match return a false boolean value */
			if (name.equals(str)) {    
				unique = false;
			}
		}
		return unique; 
	}
	
	/* Returns list of adult from the ProfileManager it is used on */
	public ArrayList<Profile> getAdults() {
		ArrayList<Profile> tempList = new ArrayList<Profile>();
		
		for (int i = 0; i < this.get_Plist().size(); i++) {
			Profile temp = this.get_Plist().get(i);
			
			/* iterate through list and if age is over 16, add to empty list */
			if (temp.getAge() >= 16) {
				tempList.add(temp);
			}
		}
		
		return tempList;
	}
	
	/* Method to return a selected profile from a given list */
	public Profile selectProfile(String title) throws IOException {
		
		Profile selectProf = null; //initiate profile object
		ArrayList<Profile> plist = this.get_Plist();
		int option;
		
		/* Create list of names */
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
	
	/* Method to return list of names of ProgramManager it is used on */
	public ArrayList<String> listNames() {
		
		ArrayList<String> names = new ArrayList<String>();
		String name;
		
		/* Iterate on profile list adding names to list */
		for (int i = 0; i < this.get_Plist().size(); i++) {
			
			name = this.get_Plist().get(i).getName();
			names.add(name);
			
		}
		
		return names;
	}
	
	/* Method to ask and receive profile info from user and create profile object */
	public Profile askInfo() throws IOException, InputMismatchException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		
		/* enter name and check if unique */
		System.out.println("\nPlease enter your name : ");
		String name = scan.nextLine();
		if (this.uniqueName(name) != true) {
			throw new IOException("\nError: Name must be unique");
		}
	
		
		System.out.println("\nPlease enter your age : "); 
		int age = scan.nextInt();
		if (age <= 0) {
			throw new IOException("\nError: Age must be nonzero and positive");
		}
		
		scan.nextLine();
		System.out.println("\nPlease enter a status update : ");   
		String status = scan.nextLine();
		
		/* create profile object and set ID */
		Profile person = new Profile(name, status, age); 
		person.setID(get_Plist().size()+1); 
		
		return person;			
	}


}

