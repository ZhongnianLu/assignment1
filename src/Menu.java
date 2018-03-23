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
	          addProfile();
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
		
		int option = 0; //initial option
			try{System.out.println();
				printInfo(title);
				System.out.println("-----------------------------");
				//String [] options =  {"Create Profile", "Select Profile", "Add Connection", "Check Connection", "Find Relatives", "Delete Profile", "Exit"};
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
	
	public static void addProfile() throws IOException{
		try {
				Profile person = profiles.askInfo();
				profiles.addProfile(person);
				
				if (person.getAge() > 0 && person.getAge() < 16) {					//if profile holder is a dependent
					ProfileManager tempList = new ProfileManager(profiles.getAdults());
						
					System.out.println("\nYou are a dependent. Please select your parents");
					Profile parent1 = tempList.selectProfile("--First Parent--"); //select first parent profile object
					if (parent1 != null) {
					
						tempList.removeProfile(parent1);   //removes the selected profile from list
						Profile parent2 = tempList.selectProfile("--Second Parent--");
						if (parent2 != null) {
						
							if(conns.addParentConnection(parent1.getID(), parent2.getID(), person.getID())) System.out.println("\nProfile created");
							else {
								profiles.removeProfile(person);
								throw new IOException("\nError: Parents must be connected");
							}
						}
					}
				} else System.out.println("\nProfile created");
		}
		catch (IOException ie){System.out.println(ie.getMessage());}
		catch(InputMismatchException ex) {System.out.println("Error: Input must be an integer.");}

		
	}
		
 	public static void addConnection(ProfileManager profiles, ConnectionManager conns) throws IOException{
 		ProfileManager tempList = new ProfileManager();
 		tempList.importList(profiles.get_Plist());
		
 		try {
	 		System.out.println("\nPlease select the profiles to connect");
			Profile person1 = tempList.selectProfile("--First Profile--"); //select first parent profile object
			if (person1 == null) throw new IOException ("");
			
			tempList.removeProfile(person1);   //removes the selected profile from list
			
			Profile person2 = tempList.selectProfile("--Second Profile--");
			if (person2 == null) throw new IOException ("");
			
			
			if (person1.getAge() < 16 || person2.getAge() < 16) {
				 if (person1.getAge() < 16 && person2.getAge() < 16 && (diffParents(person1, person2, conns))) {
					if(conns.addFriendConnection(person1.getID(), person2.getID())) {
						System.out.println(person1.getName() + " is now friends with " + person2.getName());
					}
					else throw new IOException ("\nError: Cannot connect, adult is not dependent's parent");
				 }
			}
			else {connectionMenu(person1, person2, conns, profiles);}
 		}catch (IOException ie){System.out.println(ie.getMessage());}
		
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
 		 			same = false;
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
					System.out.println(pers1.getName() + " is now friends with " + pers2.getName());
				}else option = 0;
		    else if ( option == 2)
		    	if(conns.addCoupleConnection(pers1.getID(),  pers2.getID())) {
		    		System.out.println(pers1.getName() + " and  " + pers2.getName() + " are now a couple");
		    	}else throw new IOException("\nError: One or more persons are already coupled");
		    else if ( option == 3) {
		    	createParent(pers1, pers2, profiles, conns);
		    }
			break;
			}while(option != 0);
	 		
 	}
 	
 	public static void createParent(Profile pers1, Profile pers2, ProfileManager profiles, ConnectionManager conns) throws IOException {
 		boolean done = false;
 		do {
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
	 		if (parent2 == null) done = true;
	 		
	 		if(parent1.getAge() > child.getAge() && parent2.getAge() > child.getAge()) {
	 			if(conns.addParentConnection(parent1.getID(), parent2.getID(), child.getID()))
	 				System.out.println("\nProfile created");
	 			else throw new IOException("\nError: Parents must be connected");
	 		}else throw new IOException("\nError: Parents must be older than child");
 		}while(done == false);
 	}
 	
 	static int[] numArray(int num) {
 	    
 	   int[] a = new int[num];
 	    for (int i = 0; i < num-1; ++i) {
 	    	 a[i] = i+1;
 	    }
 	    a[num-1] = 0;
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
		System.out.println("=============================");
 		printInfo(Integer.toString(prof.getAge()));
 		
 		System.out.println("=============================\n");
 		printInfo("Friends :\n");
 		ArrayList<Profile> friends = conns.search(prof);
 		int count = 0;
 		for (int i = 0; i < friends.size(); i++) {
 			printInfo(friends.get(i).getName() + "   ID: " + friends.get(i).getID());
 			count++;
 		}
 		if (count == 0) printInfo(prof.getName() + " has no friends");
 		profileMenu(prof, profiles, conns);
 	}
 	
 	public static void profileMenu(Profile prof, ProfileManager profiles, ConnectionManager conns) throws IOException {
 		int option;
		do{
			ArrayList<String> options = new ArrayList<String>(Arrays.asList("Update Name", "Update Image", "Update Status", "Exit to Main Menu"));
			
			option = display_Menu("Profile Menu", options);
			if ( option == 1 )
		          updateInfo(1, prof, profiles, conns);
		    else if ( option == 2)
		    	  updateInfo(2, prof, profiles, conns);
		    else if( option == 3)
		    	  updateInfo(3, prof, profiles, conns);    
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
		boolean done = false;
		try {
			ProfileManager tempList = new ProfileManager();
	 		tempList.importList(profiles.get_Plist());
				
	 		System.out.println("\nPlease select the profiles to check");
			String title = "--First Profile--";
			Profile person1 = tempList.selectProfile(title); //select first parent profile object
			if (person1 == null) throw new IOException("");
			
			tempList.removeProfile(person1);   //removes the selected profile from list
			Profile person2 = tempList.selectProfile("--Second Profile--");
			if (person2 == null) throw new IOException("");
			
			ArrayList<Profile> friends = conns.search(person1);
			boolean connected = false;
			
			for (int i = 0; i < friends.size(); i++) {
				Profile temp = friends.get(i);
				if (temp.equals(person2))
					connected = true;
			}
			
			if (connected) 
				System.out.println(person1.getName() + " is now friends with " + person2.getName());
			else
				System.out.println(person1.getName() + " is not friends with " + person2.getName());
			done = true;
		}catch (IOException ie){System.out.println(ie.getMessage());}
		
		
	}
	
	public static void findRelative(ProfileManager profiles, ConnectionManager conns) throws IOException {
		try{
			//boolean done = false;
			//do {
			System.out.println();
		
			Profile prof = profiles.selectProfile("Please select the profile");
			if (prof == null) throw new IOException("");//done = true;
				
			ArrayList<Connection> family = getRelations(prof, conns);
			
			for (int i = 0; i < family.size(); i++) {
				Connection con = family.get(i);
				if(prof.equals(con.getChild()))
					System.out.println(prof.getName() + " is a child of " + con.getPerson1().getName() + " and " + con.getPerson2().getName());
				else 
					System.out.println(prof.getName() + " is a parent of " + con.getChild().getName());
			}
			//}while(done == false);
			
		}catch (IOException ie){System.out.println(ie.getMessage());}
		
			
	}
	
    public static void deleteProfile(ProfileManager profiles, ConnectionManager conns) throws IOException {
    	try {
    	Profile prof = profiles.selectProfile("Select a profile to delete:");
    	boolean deleted = true;
    	if (prof != null) {
    	
    		ArrayList<Connection> friends = conns.search_clist(prof);
        	if(friends != null) {
        		for(int i = 0; i < friends.size(); i++) {
        			if(conns.get_Clist().remove(friends.get(i)) == false)
        				deleted = false;
        		}
        	}
    	
    	}else throw new IOException ("");
		
    	if(deleted) {
    		profiles.removeProfile(prof);
        	System.out.println("The profile has been deleted");
    	} else throw new IOException("\nError: Unable to delete all friends. Deletion cancelled.");
    }catch (IOException ie){System.out.println(ie.getMessage());}
	
    }
			
		


}
