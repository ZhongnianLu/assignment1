import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Author: Jake Mott s3349405
 * 
 * This class contains the menu methods and functionality 
 * of the different options of MiniNet
 * 
 */

public class Menu {
	
	/* Initiating the main manager classes and the data source class */
	public static Database data = new Database(); 
	public static ProfileManager profiles = new ProfileManager();  
	public static ConnectionManager conns = new ConnectionManager(); 
	
	public void getMenu() throws IOException {
		
		/* Imports the profiles and connections from the data object data source */
		profiles.importList(data.readProfiles());
		conns.importList(data.readConnections()); 
		conns.Pmanager = profiles;
			
		int option;
		do{
		  	ArrayList<String> options = new ArrayList<String>(Arrays.asList("Create Profile",
					"Select Profile", "Add Connection", "Check Connection", "Find Relatives",
					"Delete Profile", "Exit"));
			
			option = display_Menu("MiniNet Main Menu", options);
			
			if ( option == 1 ) {			
		        addProfile();
			} else if ( option == 2) {
		    	Profile prof = profiles.selectProfile("Please select a profile:");
		 		if (prof != null)
		 		printProfile(prof);
		    } else if( option == 3) {	    	
		    	addConnection();
		    } else if ( option == 4) {
		    	checkConnection();
		    } else if ( option == 5) {	    	
		    	findRelative();
		    } else if (option == 6) {
		    	deleteProfile();
		    }
			
		}while(option != 0);

	}
	
	/* Intakes a menu title and options for the menu to display, returns int option number */
	public static int display_Menu(String title, ArrayList<String> options) {
		
		int option = 0; //initial option
		try{
			System.out.println();
			printInfo(title);
			System.out.println("-----------------------------");
			int length = options.size();
			
			/* Create numbers for menu */
			int [] pids =  numArray(options.size()); 
			int i = 0;
			
			/* prints the menu with equal spacing */
			for( String s : options ) { 
				System.out.printf("%-27s %d%n", s, pids[i++]);
			}
	
			Scanner scan = new Scanner(System.in);
			System.out.println("-----------------------------");
			printInfo("Your Choice :");
			option = scan.nextInt();
			if (option >= 0 && option < length) {
				return option;
			} else {
				throw new IOException("\nError: Input invalid, must "
					+ "input corresponding number for selection.");}
		}  
		catch (IOException io) {
			System.out.println(io.getMessage());
		}
		catch (InputMismatchException e) {System.out.println("\nError: " + "Input must be a number");
		}
		
		return option;
	}
	
	/* Method for creating an integer array to match any number of options given 
 	 * and assigns zero to last index */
 	static int[] numArray(int num) {
 		
 	    int[] a = new int[num];
 	    
 	    for (int i = 0; i < num-1; ++i) {
 	    	a[i] = i+1;
 	    }
 	    
 	    a[num-1] = 0;
 	    return a;
 	}
	
	/* Creates profile and any creates any necessary connections */
	public static void addProfile() throws IOException {
		
		try {
			Profile person = profiles.askInfo();
			profiles.addProfile(person);
			
			/* If the person is a dependent */
			if (person.getAge() > 0 && person.getAge() < 16) {				
				/* create temporary ProgramManager object containing a list of adults */
				ProfileManager tempList = new ProfileManager(profiles.getAdults());
					
				System.out.println("\nYou are a dependent. Please select your parents");
				Profile parent1 = tempList.selectProfile("--First Parent--"); 
				if (parent1 == null) {
					throw new IOException("Error: Progrfile is null. Exiting to main menu.");
				}
				
				/* removes already selected parent from adult list */
				tempList.removeProfile(parent1);  
				Profile parent2 = tempList.selectProfile("--Second Parent--");
				if (parent2 == null) {
					throw new IOException("Error: Progrfile is null. Exiting to main menu.");
				}
				
				if(conns.addParentConnection(parent1.getID(),parent2.getID(), person.getID())) {
					System.out.println("\nProfile created");
				} else {
					profiles.removeProfile(person);
					throw new IOException("\nError: Parents must be connected");
				}
			} else {
				System.out.println("\nProfile created");
			}
		}
		catch (IOException ie) {
			System.out.println(ie.getMessage());
		}
		catch(InputMismatchException ex) {
			System.out.println("Error: Input must be an integer.");
		}

		
	}
		
	/* Select two profiles from a list and create necessary connections automatically or through a menu */
 	public static void addConnection() throws IOException {
 		ProfileManager tempList = new ProfileManager();
 		tempList.importList(profiles.get_Plist());
		
 		try {
 			/* select the parents through list then check age */
	 		System.out.println("\nPlease select the profiles to connect");
			Profile person1 = tempList.selectProfile("--First Profile--"); 
			if (person1 == null) {
				throw new IOException ("");
			}
			
			tempList.removeProfile(person1);   
			
			Profile person2 = tempList.selectProfile("--Second Profile--");
			if (person2 == null) {
				throw new IOException ("");
			}
			
			/* If a dependents then check if they have the same parents */
			if (person1.getAge() < 16 && person2.getAge() < 16 && diffParents(person1, person2)) {
					if(conns.addFriendConnection(person1.getID(), person2.getID())) {
						System.out.println(person1.getName() + " is now friends with " + person2.getName());
					}
			} else if (person1.getAge() >= 16 && person2.getAge() >= 16){
				connectionMenu(person1, person2);
			} else if (person1.getAge() >= 16 || person2.getAge() >= 16){
				throw new IOException ("\nError: Cannot connect, adult not dependent's parent.");
			} else {
				throw new IOException ("\nError: Cannot connect, have same parents");
			}
 		} catch (IOException ie){
 			System.out.println(ie.getMessage());
 		}
	}
 	
 		
 	public static void connectionMenu(Profile pers1, Profile pers2)throws IOException{
 		int option; // integer for option selection from menu
		do{
			ArrayList<String> options = new ArrayList<String>(Arrays.asList(
					"Friend", "Couple","Parent", "Exit to Main Menu"));
			
			option = display_Menu("Select Connection Type", options);
			/* 1 selects friend connection */
			if ( option == 1 ) {
				if(conns.addFriendConnection(pers1.getID(), pers2.getID())) {
					System.out.println(pers1.getName() + " is now friends with " + pers2.getName());
				}else throw new IOException(pers1.getName() + " and " + pers2.getName() + " are already friends");
			
			/* 2 selects a couple connection */
			} else if ( option == 2) {
		    	if(conns.addCoupleConnection(pers1.getID(),  pers2.getID())) {
		    		System.out.println(pers1.getName() + " and  " + pers2.getName() + " are now a couple");
		    	} else throw new IOException("\nError: One or more persons are already coupled");
			
		    /* 3 selects a parent connection */
			} else if ( option == 3) {
		    	createParent(pers1, pers2);
		    }
			
			break;
			}while(option != 0);
	 		
 	}

 	/* creates new parent connections checking for age conditions */
 	public static void createParent(Profile pers1, Profile pers2) throws IOException {
 				
 		Scanner scan = new Scanner(System.in);
		ProfileManager pers = new ProfileManager();
		pers.importList(new ArrayList<Profile>(Arrays.asList(pers1, pers2)));
		
		/* Selects the child from the 2 given persons */
 		Profile child = pers.selectProfile("Who the child?");
 		
 		/* Assigns the other profile as a parent */
 		Profile parent1;
 		if(pers1.equals(child)) {
 			parent1 = pers2;
 		} else parent1 = pers1;
 		
 		/* Select other parent from remaining adults */
 		Profile parent2;
 		System.out.println();
 		pers.importList(profiles.getAdults());
 		pers.removeProfile(parent1);
 		parent2 = pers.selectProfile("Please select the other parent");
 		
 		if (parent2 == null) {
 			throw new IOException("Error: Profile is null");
 		}
 		
 		/* Checks ages to make sure adults are older than the child, then creates parent connection */
 		if(parent1.getAge() > child.getAge() && parent2.getAge() > child.getAge()) {
 			if(conns.addParentConnection(parent1.getID(), parent2.getID(), child.getID())) {
 				System.out.println("\nProfile created");
 			} else throw new IOException("\nError: Parents must be connected");
 		} else throw new IOException("\nError: Parents must be older than child");
 		
 	}
 	
 	/* Prints a profile's details */
 	public static void printProfile(Profile prof) throws IOException {
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
 		
 		/* prints out list of friends names */
 		System.out.println("=============================\n");
 		printInfo("Friends :\n");
 		ArrayList<Profile> friends = conns.search(prof);
 		int count = 0;  //count for number of names printed
 		for (int i = 0; i < friends.size(); i++) {
 			printInfo(friends.get(i).getName());
 			count++;
 		}
 		
 		if (count == 0) {
 			printInfo(prof.getName() + " has no friends");
 		}
 		
 		profileMenu(prof);
 	}
 	
 	/* menu for profiles allowing access to update the info */
 	public static void profileMenu(Profile prof) throws IOException {
 		int option;
		do{
			ArrayList<String> options = new ArrayList<String>(Arrays.asList(
					"Update Name", "Update Image", "Update Status", "Exit to Main Menu"));
			
			/* 1 is name, 2 is image and 3 is status */
			option = display_Menu("Profile Menu", options);
			if ( option == 1 ) {
				updateInfo(1, prof);
			} else if ( option == 2) {
				updateInfo(2, prof);
			} else if( option == 3) {
				updateInfo(3, prof); 
			}
			
			break;
			
		}while(option != 0);
	 		
 	}
 	
 	/* method to update the profile info */
 	public static void updateInfo(int choice, Profile prof) throws IOException {
 		String info; // to get info type
 		if (choice == 1) {
 			info = "name";
 		} else if (choice == 2) {
 			info = "image";
 		} else {
 			info = "status";
 		}
	 		
  		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a new " + info +  " : ");
		String str = scan.nextLine();
		
		/* set the selected info to the input string  */
		if (choice == 1) {
 			prof.setName(str);
 		} else if (choice == 2) {
 			prof.setImage(str);
 		} else {
 			prof.setStatus(str);
 		}
		
		printProfile(prof);
 	}
 	 	
 	/* method to print a string center aligned within 29 spaces */
 	public static void printInfo(String string) {
 		int nlength = string.length();
 		
 		/* if string can fit on one line, calc margins and print */
 		if (nlength <= 29) {
 			int rem = (29-nlength)/2;
 			
 			for (int i = 0; i < rem; i++) {
 				System.out.print(" ");
 			}
 			System.out.print(string + "\n");
 		} else {
 		
 		/* If on multiple lines, split into line widths
 		 * and check if part ends in the middle of a word
 		 *  print part with "-" if does */
 			
 			ArrayList<String> splitS = getParts(string, 28);
 			int splitLen = splitS.size();
 			
 			for (int i = 0; i < (splitLen-1); i++) {
 				String part = splitS.get(i);
 				
 				if (part.endsWith(" ")) {
 					System.out.println(part);
 				} else {
 					System.out.println(part + "-");
 				}
 				
 			}
 			
 			/* Print the end part as normal, calc margins */
 			String part = splitS.get(splitLen-1);
 			int pLen = part.length();
 			int rem = (29-pLen)/2;
 			
 			for (int i = 0; i < rem; i++) {
 				System.out.print(" ");
 			}
 			
 			System.out.print(part + "\n");
 			}
 	}
 	
 	/* method to split a string into equal parts of a given length */
 	private static ArrayList<String> getParts(String string, int splitLength) {
 		
        ArrayList<String> parts = new ArrayList<String>();
        int sLength = string.length();
        
        for (int i=0; i<sLength; i+=splitLength) {
            parts.add(string.substring(i, Math.min(sLength, i + splitLength)));
        }
        
        return parts;
    }
	
 	/* Method for selection 2 profiles and check if they are connected */
	public static void checkConnection() throws IOException {
		try {
			ProfileManager tempList = new ProfileManager();
	 		tempList.importList(profiles.get_Plist());
				
	 		/* Selects the profiles, removing first person for the second selection */
	 		System.out.println("\nPlease select the profiles to check");
			String title = "--First Profile--";
			Profile person1 = tempList.selectProfile(title);
			if (person1 == null) {
				throw new IOException("");
			}
			
			tempList.removeProfile(person1);
			Profile person2 = tempList.selectProfile("--Second Profile--");
			if (person2 == null) {
				throw new IOException("");
			}
			
			/* Get list of person1 friends, then check each one to see if person 2 is in it */
			ArrayList<Profile> friends = conns.search(person1);
			boolean connected = false;
			
			for (int i = 0; i < friends.size(); i++) {
				Profile temp = friends.get(i);
				
				if (temp.equals(person2)) {
					connected = true;
				}
				
			}
			
			if (connected) {
				System.out.println(person1.getName() + " is now friends with " + person2.getName());
			}else {
				System.out.println(person1.getName() + " is not friends with " + person2.getName());
			}
			
		}catch (IOException ie){System.out.println(ie.getMessage());}		
	}
	
	/* Searches for parent connections and iterates over list to check if any parents match between profiles */
 	public static boolean diffParents(Profile pers1, Profile pers2) {
 		
 		boolean same = false; //boolean to return whether the persons have same parents
 		Profile parent1 = null;
 		Profile parent2 = null; 
 		Profile parent3 = null;
 		Profile parent4 = null;
 		
 		/* get list of family connections to check against */
 		ArrayList<Connection> c1 = getRelations(pers1);
 		ArrayList<Connection> c2 = getRelations(pers2);
 		
 		/* Iterate through checking if any parents match, returning true if they don't */
 		for(int i = 0; i < c1.size(); i++) {
 			parent1 = c1.get(i).getPerson1();
			parent2 = c1.get(i).getPerson2();
			
 			for(int j = 0; j < c2.size(); j++) {
				parent3 = c1.get(j).getPerson1();
				parent4 = c1.get(j).getPerson2();
				
 				if (parent1.equals(parent3) || parent1.equals(parent4)
 					|| parent2.equals(parent3) || parent2.equals(parent4)) {
 					 					 					
 		 			same = true;
 		 			break;
 				}
 			}
 		}
 		return same;
 	}
 	
 	/* Input profile and iterates through their connections, returning a list of parent connections */
 	public static ArrayList<Connection> getRelations(Profile prof){
 		
 		ArrayList<Connection> friends = conns.search_clist(prof);
 		
 		/* Create new list of connections and only add parent connections to it */
 		ArrayList<Connection> relations = new ArrayList<Connection>();		
 		for(int i = 0; i < friends.size(); i++) {
 			
 			if (friends.get(i) instanceof Parent_Connection) {
 				relations.add(friends.get(i));
 			}
 			
 		}
 		return relations;
 	}
 	
	/* Selects a profile and prints all their parents or children */
	public static void findRelative() throws IOException {
		
		try{
			System.out.println();
			Profile prof = profiles.selectProfile("Please select the profile");
			if (prof == null) {
				throw new IOException("");
			}
				
			ArrayList<Connection> family = getRelations(prof);
			
			/* Iterates through all family, checking whether a child or parent and prints a statement */
			for (int i = 0; i < family.size(); i++) {
				Connection con = family.get(i);
				
				if(prof.equals(con.getChild())) {
					System.out.println(prof.getName() + " is a child of " 
					+ con.getPerson1().getName() + " and " + con.getPerson2().getName());
				} else {
					System.out.println(prof.getName() + " is a parent of " + con.getChild().getName());
				}
			}
		}catch (IOException ie){System.out.println(ie.getMessage());}		
	}
	
	/* Selects profile, deletes connections and then removes profile from list */
    public static void deleteProfile() throws IOException {
    	try {
	    	Profile prof = profiles.selectProfile("Select a profile to delete:");
	    	boolean deleted = true; //boolean to know if all connections have been deleted
	    	
	    	if (prof == null) {
	    		throw new IOException ("");
	    	}
	    	
			/* Gets list of friends. If not null, removes connections */
			ArrayList<Connection> friends = conns.search_clist(prof);
			
	    	if(friends != null) {
	    		for(int i = 0; i < friends.size(); i++) {
	    			if(conns.get_Clist().remove(friends.get(i)) == false)
	    				deleted = false;
	    		}
	    	}
			
	    	/* If all connections deleted, then proceed with removing the profile */
	    	if(deleted) {
	    		profiles.removeProfile(prof);
	        	System.out.println("The profile has been deleted");
	    	} else {
	    		throw new IOException("\nError: Unable to delete all friends. Deletion cancelled.");
	    	}
	    	
    	}catch (IOException ie){System.out.println(ie.getMessage());}
    }
			
		


}