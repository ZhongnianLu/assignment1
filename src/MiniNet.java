<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MiniNet {
	
	public static void main(String[] args) throws IOException 
	{
	
	//ArrayList<Profile> profiles = new ArrayList<Profile>();
	ProfileManager Profiles = new ProfileManager();
	//ConnectionManager connections = new ConnectionManager();
	Profile Bob = new Adult("bob", "Starlet 99", 35);
	Profile James = new Adult("James", "BMW 05", 90);
	Profile Mary = new Adult("Mary", "Holden 03", 60);
	Profile Lisa = new Adult("Lisa", "Camry 04", 65);
	Profiles.addProfile(Bob);
	Profiles.addProfile(James);
	Profiles.addProfile(Mary);
	Profiles.addProfile(Lisa);
	Profiles.addProfile( new Child("Evan", "Jaguar 06", 12, Bob, Mary));
	Profiles.addProfile( new Child("Veronica", "Subaru 02", 6, James, Lisa));
		
		
	
		int option;
		do{
		option = display_menu();
		if ( option == 1 )
	          addProfile(Profiles);
	    else if ( option == 2)
	    	  selectProfile(Profiles, "Select");
	    else if( option == 3)
	    	  addConnection();    
	    else if ( option == 4)
				checkConnection();
		else if ( option == 5)
	    	  findRelative();
	    else if (option == 6)
	    	selectProfile(Profiles, "Delete");
		}while(option != 0);

	}
	
	public static int display_menu(){
		
		int option = 1; //initial option
			try{System.out.println();
				System.out.println("    MininNet Main Menu");
				String [] options =  {"Create Profile", "Select Profile", "Add Friend", "Check Friends", "Find Relatives", "Delete Profile", "Exit"};
				int [] pids =  {1, 2, 3, 4, 5, 6, 0};  //creating arrays of numbers of the choices
				int i = 0;
				for( String s : options ) { 
					System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
				}
		
				Scanner scan = new Scanner(System.in);
				System.out.println("        Your Choice : ");
				option = scan.nextInt();
				if (option >= 0 && option <=7){return option;} //if option is valid, return value
				else {throw new IOException("Input invalid, must input corresponding number for selection.");}
				}  //if option is outside if range of choices throw exception
			catch (IOException io) {System.out.println(io.getMessage());}
		return option;
	}
	
	
	public static void addProfile(ProfileManager profiles) throws IOException{
		//ask age
		//ask for rest of details
		//if age 16 and over create adult class, thats it
		
		//if age less than 16, child class
		//ask to select their parents with the search name function
		//if parents are not connected, connect anyway? otherwise cancel creation of profile and return to main menu
		
		//if age 2 or less, unable to create a profile 
		Scanner scan = new Scanner(System.in);
		System.out.println("Search Name : ");
		//String searchTerm = scan.nextLine();
		
					}
	
	
 	public static void addConnection(){
			//search names
 		//select first profile
 		//search again to select second profile
 		//try to make a connection between them doing checks
 		//    -check if already friends
 		//    -if person is already a parent or coupled, cannot make another couple connection
 		//    -check for age difference if child classes (diff <= 3)
 		//    -check if have same parents if children
 		//    -if child already have 2 parents, can't have 3
			//want connections.addFriendConnection(profile.getID(), profile.getID())
			//so aim to get the profile classes to be able to call the getID methods on them
		}
		
 	public static ArrayList<Profile> searchNames(String term, ArrayList<Profile> plist){
 		ArrayList<Profile> observed = new ArrayList<Profile>();
 		
 		for (int i = 0; i < plist.size(); i++) {
 			String pname = plist.get(i).getName();
 			if (pname.toLowerCase().contains(term.toLowerCase())){
 				observed.add(plist.get(i));
 			}
 		}
 		return observed;
 	}
 	
 	
 	
 	public static void selectProfile(ProfileManager profiles, String type) throws IOException{
			//Scanner scan = new Scanner(System.in);
			//System.out.println("Search Name : ");
			//String searchTerm = scan.nextLine();
			
			//ArrayList<Profile> searched = searchNames(searchTerm, profiles.get_Plist());
			//System.out.println("size of searched is " + searched.size());
			int option;
			do{
			option = displayNames(profiles.get_Plist());
			if ( option >= 1 && option <= profiles.get_Plist().size()) {
				if (type.equals("Select")) {
					displayProfile(profiles.get_Plist().get(option-1), profiles);
				}
				else if (type.equals("Delete")) {
					deleteProfile(profiles.get_Plist().get(option-1), profiles);
				}
			}
			break;
			}while(option != 0);
			
			
 	}
	
 	
 	public static int[] numArray(int num) {
 	    
 	   int[] a = new int[num+1];
 	    for (int i = 0; i < num; ++i) {
 	    	//System.out.println("num array, i is " + i);
 	        a[i] = i+1;
 	    }
 	    a[num] = 0;
 	    //for (int i = 0; i < a.length; i++) {
 	    	//System.out.println("index " + i + " in a is " + a[i]);
 	 	    
 	    //}
 	    //System.out.println(Arrays.toString(a));
 	    return a;
 	}
 	
 	public static void displayProfile(Profile prof, ProfileManager profiles) {
 		System.out.println("========================================\n");
 		printInfo(prof.getImage());
 		System.out.println("\n========================================\n");
 		printInfo(prof.getName());
 		System.out.println("========================================\n");
 		printInfo(prof.getStatus());
 		System.out.println("\n========================================\n");
 		printInfo("Friends :");
 		ArrayList<Profile> plist = profiles.get_Plist();
 		for (int i = 0; i < plist.size(); i++) {
 			Profile person = plist.get(i);
 			//check friends
 			//if return true to mean they are friends
 			//print name of profile
 			printInfo("person.getName() \n");
 			
 			
 		}
 		profileMenu(prof, profiles);
 	}
 	
 	public static void profileMenu(Profile prof, ProfileManager profiles) {
 		int option;
		do{
		option = displayProfileMenu();
		if ( option == 1 )
	          updateInfo(1, prof, profiles);
	    else if ( option == 2)
	    	  updateInfo(2, prof, profiles);
	    else if( option == 3)
	    	  updateInfo(3, prof, profiles);    
	    else if (option == 5)
	    	deleteProfile(prof, profiles);
		break;
		}while(option != 0);
 		
 	}
 	
 	public static void updateInfo(int choice, Profile prof, ProfileManager profiles) {
 		String info;
 		if (choice == 1) {
 			info = "name";
 		}
 		else if (choice == 2) {
 			info = "image";
 		}
 		else info = "status";
 		
 		//String str;
 		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a new " + info +  " : ");
		String str = scan.nextLine();
		
		if (choice == 1) {
 			prof.setName(str);
 		}
 		else if (choice == 2) {
 			prof.setImage(str);
 		}
 		else prof.setStatus(str);
		displayProfile(prof, profiles);
		
 		
 	}
 	
 	public static int displayProfileMenu() {
 		
 		int option = 1; //initialising option
		try{System.out.println();
			System.out.println("       Profile Menu");
			String [] options =  {"Update Name", "Update Image", "Update Status", "Delete Profile", "Exit to Main Menu"};
			int [] pids =  {1, 2, 3, 4, 0};  //creating arrays of numbers of the choices
			int i = 0;
			for( String s : options ) { 
				System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
			}
	
			Scanner scan = new Scanner(System.in);
			System.out.println("        Your Choice : ");
			option = scan.nextInt();
			if (option >= 0 && option <=4){return option;} //if option is valid, return value
			else {throw new IOException("Input invalid, must input corresponding number for selection.");}
			}  //if option is outside if range of choices throw exception
		catch (IOException io) {System.out.println(io.getMessage());}
	return option;
 	}
 	
 	public static void printInfo(String string) {
 		int nlength = string.length();
 		if (nlength <= 40) {
 			int rem = (40-nlength)/2;
 			for (int i = 0; i < rem; i++) {
 				System.out.print(" ");
 			}
 			System.out.print(string + "\n");
 		}
 		else {
 			ArrayList<String> splitS = getParts(string, 39);
 			int splitLen = splitS.size();
 			for (int i = 0; i < (splitLen-1); i++) {
 				String part = splitS.get(i);
 				if (part.endsWith(" ")) {
 					System.out.println(part);
 				}
 				else {
 					System.out.println(part + "-");
 				}
 			}
 			String part = splitS.get(splitLen-1);
 			int pLen = part.length();
 			int rem = (40-pLen)/2;
 			for (int i = 0; i < rem; i++) {
 				System.out.print(" ");
 			}
 			System.out.print(part + "\n");
 			}
 	}
 	
 	private static ArrayList<String> getParts(String string, int splitLength) {
        ArrayList<String> parts = new ArrayList<String>();
        int sLength = string.length();
        for (int i=0; i<sLength; i+=splitLength)
        {
            parts.add(string.substring(i, Math.min(sLength, i + splitLength)));
        }
        return parts;
    }
 	
 	public static int displayNames(ArrayList<Profile> plist) {
 		
 		int option = 1; //initialising option
		try{System.out.println();
			System.out.println("      Search Results :      ");
			System.out.println("-----------------------------");
			ArrayList<String> names = listNames(plist);
			names.add("Exit to Main Menu");
			//String res = String.join(",", names);
	        //System.out.println(res);

			//String[] options = listNames(plist).toArray(new String[listNames(plist).size()]);
			String[] options = names.stream().toArray(String[]::new);
			//System.out.println("options is " + Arrays.toString(options));

			
			int [] pids =  numArray(plist.size());  //creating arrays of numbers of the choices
			int i = 0;
			for( String s : options ) { 
				System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
			}
	
			Scanner scan = new Scanner(System.in);
			System.out.println("-----------------------------");
			System.out.println("        Your Choice : ");
			option = scan.nextInt();
			if (option >= 0 && option <=names.size()){return option;} //if option is valid, return value
			else {throw new IOException("Input invalid, must input corresponding number for selection.");}
			}  //if option is outside if range of choices throw exception
		catch (IOException io) {System.out.println(io.getMessage());}
		
	return option;
 	}
 	
	public static ArrayList<String> listNames(ArrayList<Profile> plist) {
				ArrayList<String> names = new ArrayList<String>();
				String name;
				for (int i = 0; i < plist.size(); i++) {
					name = plist.get(i).getName();
					names.add(name);
				}				
				
				return names;
			}
				
	public static void checkConnection() {
		
	}
	
	public static void findRelative() {
		
	}
	
	
    public static void deleteProfile(Profile prof, ProfileManager profiles) {
    	//checks?
    	//delete connections?
    	profiles.removeProfile(prof);
    	System.out.println("The profile has been deleted");
		}
			
		

	
	
	
	
	
	

}
=======
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MiniNet {
	
	public static void main(String[] args) throws IOException 
	{
	
	//ArrayList<Profile> profiles = new ArrayList<Profile>();
	ProfileManager Profiles = new ProfileManager();
	//ConnectionManager connections = new ConnectionManager();
	Profile Bob = new Adult("bob", "Starlet 99", 35);
	Profile James = new Adult("James", "BMW 05", 90);
	Profile Mary = new Adult("Mary", "Holden 03", 60);
	Profile Lisa = new Adult("Lisa", "Camry 04", 65);
	Profiles.addProfile(Bob);
	Profiles.addProfile(James);
	Profiles.addProfile(Mary);
	Profiles.addProfile(Lisa);
	Profiles.addProfile( new Child("Evan", "Jaguar 06", 12, Bob, Mary));
	Profiles.addProfile( new Child("Veronica", "Subaru 02", 6, James, Lisa));
		
		
	
		int option;
		do{
		option = display_menu();
		if ( option == 1 )
	          addProfile(Profiles);
	    else if ( option == 2)
	    	  selectProfile(Profiles, "Select");
	    else if( option == 3)
	    	  addConnection();    
	    else if ( option == 4)
				checkConnection();
		else if ( option == 5)
	    	  findRelative();
	    else if (option == 6)
	    	selectProfile(Profiles, "Delete");
		}while(option != 0);

	}
	
	public static int display_menu(){
		
		int option = 1; //initial option
			try{System.out.println();
				System.out.println("    MininNet Main Menu");
				String [] options =  {"Create Profile", "Select Profile", "Add Friend", "Check Friends", "Find Relatives", "Delete Profile", "Exit"};
				int [] pids =  {1, 2, 3, 4, 5, 6, 0};  //creating arrays of numbers of the choices
				int i = 0;
				for( String s : options ) { 
					System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
				}
		
				Scanner scan = new Scanner(System.in);
				System.out.println("        Your Choice : ");
				option = scan.nextInt();
				if (option >= 0 && option <=7){return option;} //if option is valid, return value
				else {throw new IOException("Input invalid, must input corresponding number for selection.");}
				}  //if option is outside if range of choices throw exception
			catch (IOException io) {System.out.println(io.getMessage());}
		return option;
	}
	
	
	public static void addProfile(ProfileManager profiles) throws IOException{
		//ask age
		//ask for rest of details
		//if age 16 and over create adult class, thats it
		
		//if age less than 16, child class
		//ask to select their parents with the search name function
		//if parents are not connected, connect anyway? otherwise cancel creation of profile and return to main menu
		
		//if age 2 or less, unable to create a profile 
		Scanner scan = new Scanner(System.in);
		System.out.println("Search Name : ");
		//String searchTerm = scan.nextLine();
		
					}
	
	
 	public static void addConnection(){
			//search names
 		//select first profile
 		//search again to select second profile
 		//try to make a connection between them doing checks
 		//    -check if already friends
 		//    -if person is already a parent or coupled, cannot make another couple connection
 		//    -check for age difference if child classes (diff <= 3)
 		//    -check if have same parents if children
 		//    -if child already have 2 parents, can't have 3
			//want connections.addFriendConnection(profile.getID(), profile.getID())
			//so aim to get the profile classes to be able to call the getID methods on them
		}
		
 	public static ArrayList<Profile> searchNames(String term, ArrayList<Profile> plist){
 		ArrayList<Profile> observed = new ArrayList<Profile>();
 		
 		for (int i = 0; i < plist.size(); i++) {
 			String pname = plist.get(i).getName();
 			if (pname.toLowerCase().contains(term.toLowerCase())){
 				observed.add(plist.get(i));
 			}
 		}
 		return observed;
 	}
 	
 	
 	
 	public static void selectProfile(ProfileManager profiles, String type) throws IOException{
			//Scanner scan = new Scanner(System.in);
			//System.out.println("Search Name : ");
			//String searchTerm = scan.nextLine();
			
			//ArrayList<Profile> searched = searchNames(searchTerm, profiles.get_Plist());
			//System.out.println("size of searched is " + searched.size());
			int option;
			do{
			option = displayNames(profiles.get_Plist());
			if ( option >= 1 && option <= profiles.get_Plist().size()) {
				if (type.equals("Select")) {
					displayProfile(profiles.get_Plist().get(option-1), profiles);
				}
				else if (type.equals("Delete")) {
					deleteProfile(profiles.get_Plist().get(option-1), profiles);
				}
			}
			break;
			}while(option != 0);
			
			
 	}
	
 	
 	public static int[] numArray(int num) {
 	    
 	   int[] a = new int[num+1];
 	    for (int i = 0; i < num; ++i) {
 	    	//System.out.println("num array, i is " + i);
 	        a[i] = i+1;
 	    }
 	    a[num] = 0;
 	    //for (int i = 0; i < a.length; i++) {
 	    	//System.out.println("index " + i + " in a is " + a[i]);
 	 	    
 	    //}
 	    //System.out.println(Arrays.toString(a));
 	    return a;
 	}
 	
 	public static void displayProfile(Profile prof, ProfileManager profiles) {
 		System.out.println("========================================\n");
 		printInfo(prof.getImage());
 		System.out.println("\n========================================\n");
 		printInfo(prof.getName());
 		System.out.println("========================================\n");
 		printInfo(prof.getStatus());
 		System.out.println("\n========================================\n");
 		printInfo("Friends :");
 		ArrayList<Profile> plist = profiles.get_Plist();
 		for (int i = 0; i < plist.size(); i++) {
 			Profile person = plist.get(i);
 			//check friends
 			//if return true to mean they are friends
 			//print name of profile
 			printInfo("person.getName() \n");
 			
 			
 		}
 		profileMenu(prof, profiles);
 	}
 	
 	public static void profileMenu(Profile prof, ProfileManager profiles) {
 		int option;
		do{
		option = displayProfileMenu();
		if ( option == 1 )
	          updateInfo(1, prof, profiles);
	    else if ( option == 2)
	    	  updateInfo(2, prof, profiles);
	    else if( option == 3)
	    	  updateInfo(3, prof, profiles);    
	    else if (option == 5)
	    	deleteProfile(prof, profiles);
		break;
		}while(option != 0);
 		
 	}
 	
 	public static void updateInfo(int choice, Profile prof, ProfileManager profiles) {
 		String info;
 		if (choice == 1) {
 			info = "name";
 		}
 		else if (choice == 2) {
 			info = "image";
 		}
 		else info = "status";
 		
 		//String str;
 		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a new " + info +  " : ");
		String str = scan.nextLine();
		
		if (choice == 1) {
 			prof.setName(str);
 		}
 		else if (choice == 2) {
 			prof.setImage(str);
 		}
 		else prof.setStatus(str);
		displayProfile(prof, profiles);
		
 		
 	}
 	
 	public static int displayProfileMenu() {
 		
 		int option = 1; //initialising option
		try{System.out.println();
			System.out.println("       Profile Menu");
			String [] options =  {"Update Name", "Update Image", "Update Status", "Delete Profile", "Exit to Main Menu"};
			int [] pids =  {1, 2, 3, 4, 0};  //creating arrays of numbers of the choices
			int i = 0;
			for( String s : options ) { 
				System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
			}
	
			Scanner scan = new Scanner(System.in);
			System.out.println("        Your Choice : ");
			option = scan.nextInt();
			if (option >= 0 && option <=4){return option;} //if option is valid, return value
			else {throw new IOException("Input invalid, must input corresponding number for selection.");}
			}  //if option is outside if range of choices throw exception
		catch (IOException io) {System.out.println(io.getMessage());}
	return option;
 	}
 	
 	public static void printInfo(String string) {
 		int nlength = string.length();
 		if (nlength <= 40) {
 			int rem = (40-nlength)/2;
 			for (int i = 0; i < rem; i++) {
 				System.out.print(" ");
 			}
 			System.out.print(string + "\n");
 		}
 		else {
 			ArrayList<String> splitS = getParts(string, 39);
 			int splitLen = splitS.size();
 			for (int i = 0; i < (splitLen-1); i++) {
 				String part = splitS.get(i);
 				if (part.endsWith(" ")) {
 					System.out.println(part);
 				}
 				else {
 					System.out.println(part + "-");
 				}
 			}
 			String part = splitS.get(splitLen-1);
 			int pLen = part.length();
 			int rem = (40-pLen)/2;
 			for (int i = 0; i < rem; i++) {
 				System.out.print(" ");
 			}
 			System.out.print(part + "\n");
 			}
 	}
 	
 	private static ArrayList<String> getParts(String string, int splitLength) {
        ArrayList<String> parts = new ArrayList<String>();
        int sLength = string.length();
        for (int i=0; i<sLength; i+=splitLength)
        {
            parts.add(string.substring(i, Math.min(sLength, i + splitLength)));
        }
        return parts;
    }
 	
 	public static int displayNames(ArrayList<Profile> plist) {
 		
 		int option = 1; //initialising option
		try{System.out.println();
			System.out.println("      Search Results :      ");
			System.out.println("-----------------------------");
			ArrayList<String> names = listNames(plist);
			names.add("Exit to Main Menu");
			//String res = String.join(",", names);
	        //System.out.println(res);

			//String[] options = listNames(plist).toArray(new String[listNames(plist).size()]);
			String[] options = names.stream().toArray(String[]::new);
			//System.out.println("options is " + Arrays.toString(options));

			
			int [] pids =  numArray(plist.size());  //creating arrays of numbers of the choices
			int i = 0;
			for( String s : options ) { 
				System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
			}
	
			Scanner scan = new Scanner(System.in);
			System.out.println("-----------------------------");
			System.out.println("        Your Choice : ");
			option = scan.nextInt();
			if (option >= 0 && option <=names.size()){return option;} //if option is valid, return value
			else {throw new IOException("Input invalid, must input corresponding number for selection.");}
			}  //if option is outside if range of choices throw exception
		catch (IOException io) {System.out.println(io.getMessage());}
		
	return option;
 	}
 	
	public static ArrayList<String> listNames(ArrayList<Profile> plist) {
				ArrayList<String> names = new ArrayList<String>();
				String name;
				for (int i = 0; i < plist.size(); i++) {
					name = plist.get(i).getName();
					names.add(name);
				}				
				
				return names;
			}
				
	public static void checkConnection() {
		
	}
	
	public static void findRelative() {
		
	}
	
	
    public static void deleteProfile(Profile prof, ProfileManager profiles) {
    	//checks?
    	//delete connections?
    	profiles.removeProfile(prof);
    	System.out.println("The profile has been deleted");
		}
			
		

	
	
	
	
	
	

}
>>>>>>> e9c1847a423b5097e63beba66d56749ec811e688
