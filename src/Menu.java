import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
	
	public static Database data = new Database(); //new data object with predefined data
	public static ProfileManager profiles = new ProfileManager();  //new object to manage profile
	public static ConnectionManager conns = new ConnectionManager(); //new object to manage connections
	
	public void getMenu() throws IOException 
	{
	profiles.importList(data.readProfiles()); //import the profiles from the data object to the profile manager
	conns.importList(data.readConnections()); //import the connections from the data object to the connection manager
	conns.Pmanager = profiles;
		
	int option;
	do{
		ArrayList<String> options = new ArrayList<String>(Arrays.asList("Create Profile", "Select Profile", "Add Friend", "Check Friends", "Find Relatives", "Delete Profile", "Exit"));
		
		option = display_Menu("MiniNet Main Menu", options);
		if ( option == 1 )
	          addProfile(profiles, conns);
	    else if ( option == 2) {
	    	Profile prof = profiles.selectProfile("Please select a profile:");
	 		if (prof != null)
	 		printProfile(prof, profiles, conns);
	    }
	    else if( option == 3)
	    	  addConnection(profiles, conns);    
	    else if ( option == 4)
				checkConnection(profiles, conns);
		else if ( option == 5)
	    	  findRelative(profiles, conns);
	    else if (option == 6)
	    	deleteProfile(profiles, conns);
	}while(option != 0);

	}
	
	public static int display_Menu(String title, ArrayList<String> options){
		
		int option = 1; //initial option
			try{System.out.println();
				printInfo(title);
				System.out.println("-----------------------------");
				//String [] options =  {"Create Profile", "Select Profile", "Add Friend", "Check Friends", "Find Relatives", "Delete Profile", "Exit"};
				int length = options.size();
				//System.out.println(length);
				int [] pids =  numArray(options.size());  //creating arrays of numbers of the choices
				//System.out.println(Arrays.toString(pids));
				int i = 0;
				for( String s : options ) { 
					System.out.printf("%-27s %d%n", s, pids[i++]); //printing the menu with equal spacing
				}
		
				Scanner scan = new Scanner(System.in);
				System.out.println("-----------------------------");
				printInfo("Your Choice :");
				option = scan.nextInt();
				if (option >= 0 && option < length){return option;} //if option is valid, return value
				else {throw new IOException("\nError: Input invalid, must input corresponding number for selection.");}
				}  //if option is outside if range of choices throw exception
			
			catch (IOException io) {System.out.println(io.getMessage());}
			catch (InputMismatchException e) {System.out.println("\nError: Input must be a number");}
			

		return option;
	}
	
	public static void addProfile(ProfileManager profiles, ConnectionManager conns) throws IOException{
		
		//String name = enterName(profiles); // method for entering age
		try {			
			Scanner scan = new Scanner(System.in);
			
			System.out.println();
			System.out.println("\nPlease enter your name : ");
			String name = scan.nextLine();
			if (profiles.uniqueName(name) != true) throw new IOException("\nError: Name must be unique");
		
			
			System.out.println("\nPlease enter your age : "); //enter age
			int age = scan.nextInt();
			if (age <= 0) throw new IOException("\nError: Age must be positve");
			
			scan.nextLine();
			System.out.println("Please enter a status update : ");   // enter a status
			String status = scan.nextLine();
			
			//System.out.println("Please enter a status update : ");   // enter a status
			
			int ID = profiles.get_Plist().size();
			Profile person = new Profile(name, status, age, ID); //create a profile object
			//person.setID(profiles.get_Plist().size()+1); //set ID based on number of profiles
			profiles.addProfile(person);
			
			
			if (age > 0 && age < 16) {					//if profile holder is a dependent
				ProfileManager tempList = new ProfileManager();
				tempList.importList(profiles.getAdults());
				System.out.println("\nYou are a dependent. Please select your parents");
				Profile parent1 = tempList.selectProfile("--First Parent--"); //select first parent profile object
				tempList.removeProfile(parent1);   //removes the selected profile from list
				Profile parent2 = tempList.selectProfile("--Second Parent--");
				if(conns.addParentConnection(parent1.getID(), parent2.getID(), person.getID())) {
					//profiles.addProfile(person);
					System.out.println("\nProfile created");
				}
				else {
					profiles.removeProfile(person);
					throw new IOException("\nError: Parents must be connected");
				}
			}
		}
		catch (IOException ie){System.out.println(ie.getMessage());}
	}
		
	
 	public static void addConnection(ProfileManager profiles, ConnectionManager conns) throws IOException{
 		
 		ProfileManager tempList = new ProfileManager();
 		tempList.importList(profiles.get_Plist());
		
 		try {
 			boolean done = false;
			do {
	 		System.out.println("\nPlease select the profiles to connect");
			Profile person1 = tempList.selectProfile("--First Profile--"); //select first parent profile object
			if (person1 == null) throw new IOException ("\nError: Profile is null");
			
			tempList.removeProfile(person1);   //removes the selected profile from list
			printInfo("--Second Profile--");
			Profile person2 = tempList.selectProfile("--Second Profile--");
			if (person2 == null) throw new IOException ("\nError: Profile is null");
			
			
			if (person1.getAge() < 16 || person2.getAge() < 16) {
				
				if (person1.getAge() < 16 && person2.getAge() < 16) {
					//search connections for parents
					//check if parents are the same
					//set success to false if they do have same parents
					if(diffParents(person1, person2, conns)) {
						conns.addFriendConnection(person1.getID(), person2.getID());
						done = true;
					}
				}
				else throw new IOException ("\nError: Cannot connect, adult is not dependent's parent");
			}
			else {
				connectionMenu(person1, person2, conns, profiles);
				//conns.addFriendConnection(person1.getID(), person2.getID())
			
				System.out.println("\nConnection made");
				done = true;
			}
			//else done = true;
			
			}while (done = false);
 		}catch (IOException ie){System.out.println(ie.getMessage());}
		
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
 	
 	public static boolean diffParents(Profile pers1, Profile pers2, ConnectionManager conns) {
 		boolean same = false;
 		Profile parent1 = null;
 		Profile parent2 = null;
 		Profile parent3 = null;
 		Profile parent4 = null;
 		
 		ArrayList<Connection> c1 = getRelations(pers1, conns);
 	
 		ArrayList<Connection> c2 = getRelations(pers2, conns);
 		
 		
 		for(int i = 0; i < c1.size(); i++) {
 			parent1 = c1.get(i).getPerson1();
			parent2 = c1.get(i).getPerson2();
 			for(int j = 0; j < c2.size(); j++) {
				parent3 = c1.get(j).getPerson1();
				parent4 = c1.get(j).getPerson2();
 				if ((parent1.equals(parent3) || parent1.equals(parent4)) && (parent2.equals(parent3) || parent2.equals(parent4))){
 		 			same = true;
 		 			break;
 				}
 			}
 			if(same = true)
 				break;
 		}
 		return same;
 	}
 	
 	public static ArrayList<Connection> getRelations(Profile prof, ConnectionManager conns){
 		ArrayList<Connection> friends = conns.search_clist(prof);//method for returning connection related to profile
 		ArrayList<Connection> relations = new ArrayList<Connection>();		
 		for(int i = 0; i < friends.size(); i++) {
 			if (friends.get(i) instanceof Parent_Connection) {
 				relations.add(friends.get(i));
 			}
 		}
 		
 		return relations;
 	}
 		
 	public static void connectionMenu(Profile pers1, Profile pers2,ConnectionManager conns, ProfileManager profiles )throws IOException{
 		int option;
		do{
			ArrayList<String> options = new ArrayList<String>(Arrays.asList("Friend", "Couple", "Parent", "Exit to Main Menu"));
			
			option = display_Menu("Select Connection Type", options);
			if ( option == 1 )
				if(conns.addFriendConnection(pers1.getID(), pers2.getID())){
					System.out.println("Connection made");
				}else option = 0;
		    else if ( option == 2)
		    	if(conns.addCoupleConnection(pers1.getID(),  pers2.getID())) {
		    		System.out.println("Connection made");
		    	}else throw new IOException("\nError: One or more persons is already coupled");
		    else if ( option == 3) {
		    	createParent(pers1, pers2, profiles, conns);
		    }
			break;
			}while(option != 0);
	 		
 	}
 	
 	public static void createParent(Profile pers1, Profile pers2, ProfileManager profiles, ConnectionManager conns) throws IOException {
 		
 		Scanner scan = new Scanner(System.in);
		ProfileManager pers = new ProfileManager();
		pers.importList(new ArrayList<Profile>(Arrays.asList(pers1, pers2)));
		
 		Profile child = pers.selectProfile("Who the child?");
 		
 		Profile parent1;
 		if(pers1.equals(child)) {
 			parent1 = pers2;
 		} else parent1 = pers1;
 		
 		Profile parent2;
 		System.out.println();
 		pers.importList(profiles.getAdults());
 		
 		pers.removeProfile(parent1);
 		parent2 = pers.selectProfile("Please select the other parent");
 		
 		if(parent1.getAge() > child.getAge() && parent2.getAge() > child.getAge()) {
 			if(conns.addParentConnection(parent1.getID(), parent2.getID(), child.getID()))
 				System.out.println("\nProfile created");
 			else throw new IOException("\nError: Parents must be connected");
 		}else throw new IOException("\nError: Parents must be older than child");
 	}
 	
 	static int[] numArray(int num) {
 	    
 	   int[] a = new int[num];
 	    for (int i = 0; i < num-1; ++i) {
 	    	//System.out.println("num array, i is " + i);
 	        a[i] = i+1;
 	    }
 	    a[num-1] = 0;
 	    //for (int i = 0; i < a.length; i++) {
 	    	//System.out.println("index " + i + " in a is " + a[i]);
 	 	    
 	    //}
 	    //System.out.println(Arrays.toString(a));
 	    return a;
 	}
 				
 	public static void printProfile(Profile prof, ProfileManager profiles, ConnectionManager conns) throws IOException {
 		System.out.println();
 		System.out.println();
 		System.out.println("=============================");
 		printInfo(prof.getImage());
 		System.out.println("=============================");
 		printInfo(prof.getName());
 		System.out.println("=============================");
 		printInfo(prof.getStatus());
 		System.out.println("=============================\n");
 		printInfo("Friends :\n");
 		ArrayList<Profile> friends = conns.search(prof);
 		int count = 0;
 		for (int i = 0; i < friends.size(); i++) {
 			printInfo(friends.get(i).getName());
 			count++;
 		}
 		if (count == 0) printInfo(prof.getName() + " has no friends");
 		profileMenu(prof, profiles, conns);
 	}
 	
 	public static void profileMenu(Profile prof, ProfileManager profiles, ConnectionManager conns) throws IOException {
 		int option;
		do{
			ArrayList<String> options = new ArrayList<String>(Arrays.asList("Update Name", "Update Image", "Update Status", "Delete Profile", "Exit to Main Menu"));
			
			option = display_Menu("Profile Menu", options);
			if ( option == 1 )
		          updateInfo(1, prof, profiles, conns);
		    else if ( option == 2)
		    	  updateInfo(2, prof, profiles, conns);
		    else if( option == 3)
		    	  updateInfo(3, prof, profiles, conns);    
		    else if (option == 5)
		    	deleteProfile(prof, profiles, conns);
			break;
			}while(option != 0);
	 		
 	}
 	
 	public static void updateInfo(int choice, Profile prof, ProfileManager profiles, ConnectionManager conns) throws IOException {
 		String info;
 		if (choice == 1) {
 			info = "name";
 		}
 		else if (choice == 2) {
 			info = "image";
 		}
 		else info = "status";
	 		
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
		printProfile(prof, profiles, conns);
 	}
 	 	
 	public static void printInfo(String string) {
 		int nlength = string.length();
 		if (nlength <= 29) {
 			int rem = (29-nlength)/2;
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
			
	public static void checkConnection(ProfileManager profiles, ConnectionManager conns) throws IOException {
		
		ProfileManager tempList = new ProfileManager();
 		tempList.importList(profiles.get_Plist());
			
 		System.out.println("\nPlease select the profiles to check");
		String title = "--First Profile--";
		Profile person1 = tempList.selectProfile(title); //select first parent profile object
		if (person1 == null) throw new IOException ("\nError: Profile is null");
		
		tempList.removeProfile(person1);   //removes the selected profile from list
		Profile person2 = tempList.selectProfile("--Second Profile--");
		if (person2 == null) throw new IOException ("\nError: Profile is null");
		
		ArrayList<Profile> friends = conns.search(person1);
		boolean connected = false;
		
		for (int i = 0; i < friends.size(); i++) {
			Profile temp = friends.get(i);
			if (temp.equals(person2))
				connected = true;
		}
		
		if (connected)
			System.out.println(person1.getName() + " is friends with " + person2.getName());
		else
			System.out.println(person1.getName() + " is not friends with " + person2.getName());
		
		
	}
	
	public static void findRelative(ProfileManager profiles, ConnectionManager conns) throws IOException {
		System.out.println();
		Profile prof = profiles.selectProfile("Please select the profile");
		
		ArrayList<Connection> family = getRelations(prof, conns);
		
		for (int i = 0; i < family.size(); i++) {
			Connection con = family.get(i);
			if(prof.equals(con.getChild()))
				System.out.println(prof.getName() + " is a child of " + con.getPerson1().getName() + " and " + con.getPerson2().getName());
			else 
				System.out.println(prof.getName() + " is a parent of " + con.getChild().getName());
		}
		
	}
	
    public static void deleteProfile(Profile prof, ProfileManager profiles, ConnectionManager conns) throws IOException {
    	ArrayList<Connection> friends = conns.search_clist(prof);
    	boolean deleted = true;
    	if(friends != null) {
    		for(int i = 0; i < friends.size(); i++) {
    			if(conns.get_Clist().remove(friends.get(i)) == false)
    				deleted = false;
    		}
    	}
     	
    	if(deleted) {
    		profiles.removeProfile(prof);
        	System.out.println("The profile has been deleted");
    	} else throw new IOException("\nError: Unable to delete all friends. Deletion cancelled.");
    }
    
    public static void deleteProfile(ProfileManager profiles, ConnectionManager conns) throws IOException {
    	Profile prof = profiles.selectProfile("Select a profile to delete:");
    	if (prof != null)
    		deleteProfile(prof, profiles, conns);
    	else throw new IOException ("\nError: Profile is null");
		
    }
			
		


}
