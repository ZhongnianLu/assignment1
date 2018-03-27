import java.util.ArrayList;


/*
 * Author: Zhongnian Lu s3512993
 * 
 * This class contains a list of all connections.
 * 
 * This class contains all methods to add different types of new connection.
 * 
 * This class helps search and display functions in Menu class. 
 * 
 */

public class ConnectionManager {
	

	//Access list of connections
	private ArrayList<Connection> c_list = new ArrayList<Connection>();
	
	
	//Provide an instance of Profile manager to access profile list
	ProfileManager Pmanager;	
	
	
	// direct add a new connection into the list, only for import database
    public void addConnection(Connection target){
		
    	c_list.add(target);
	}
	
	
	//Method to create a friend connection and store it in the list
	public boolean addFriendConnection(int ID_1,int ID_2){
		
		ArrayList<Profile> tem_pList = Pmanager.get_Plist();
		
		Profile person1 = null;
		Profile person2 = null;
		
		//A boolean to return whether the adding function is successed.
		boolean success = false;
		
		//create a boolean to check repeat
		boolean repeat = false;
		
		//get profiles from profile list with same ID as parameter 
		for(int i = 0;i < tem_pList.size();i++) {
			
			if((tem_pList.get(i).getID() == ID_1||tem_pList.get(i).getID() == ID_2)
					&&repeat == false) {
			
				person1 = tem_pList.get(i);
				repeat = true;	
			}
			
			if((tem_pList.get(i).getID() == ID_1||tem_pList.get(i).getID() == ID_2)
					&&repeat == true) {
				
				person2 = tem_pList.get(i);
			}
			
	    }
		
		
		//create a new connection with selected profiles
		Friend_Connection addConnect=new Friend_Connection(person1,person2);
		
		
		//check whether the friend connection is valid by calling age check method 
		if(addConnect.check(c_list) == true
				&& addConnect.repeat_check(c_list) == false) {
		
			c_list.add(addConnect);
		
		    success = true;
		}
		
		return success;
	
	}
	
	
	
	// add new parent connection by passing three IDs including parents and child
    boolean addParentConnection(int ID_1,int ID_2,int ID_child){
	
    	boolean success=false;
    	
	    ArrayList<Profile> tem_pList=Pmanager.get_Plist();
		
		Profile person1 = null;
		Profile person2 = null;
		Profile child = null;
		 
		boolean repeat = false;
		
		
		//get parents' and child's profiles from the profile list by checking their IDs 
		for(int i=0;i<tem_pList.size();i++) {
			
			if((tem_pList.get(i).getID() == ID_1||tem_pList.get(i).getID() == ID_2)
					&& repeat == false) {
				
				person1 = tem_pList.get(i);
				repeat = true;	
			}
			
			if((tem_pList.get(i).getID() == ID_1||tem_pList.get(i).getID() == ID_2)
					&& repeat == true) {
				
				person2 = tem_pList.get(i);
			}
			
			if(tem_pList.get(i).getID() == ID_child) {
			
				child = tem_pList.get(i);
			}
		}
		
		
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Parent_Connection addConnect = new Parent_Connection(person1,person2,child);
		
		if(addConnect.check(c_list) == true 
				&& addConnect.repeat_check(c_list) == false) {
			
			c_list.add(addConnect);
			success = true;
		}
		
		return success;
    }

    
    
	// add new couple connection by passing two IDs
    boolean addCoupleConnection(int ID_1,int ID_2){
    	
    	//Access a temporary profile list from profile manager class 
	    ArrayList<Profile> tem_pList = Pmanager.get_Plist();
		
		Profile person1 = null;
		Profile person2 = null;
		boolean repeat = false;
		
		// Return a boolean to indicate whether connection is successful added.
		boolean success = false;
		
		//get parents' and child's profiles from the profile list by checking their IDs 
		for(int i=0;i<tem_pList.size();i++) {
			
			if((tem_pList.get(i).getID() == ID_1||tem_pList.get(i).getID() == ID_2)
					&& repeat == false) {
				
				person1 = tem_pList.get(i);
				repeat = true;	
			}
			
			if((tem_pList.get(i).getID() == ID_1||tem_pList.get(i).getID() == ID_2)
					&& repeat == true) {
			
				person2 = tem_pList.get(i);
			}
		};
			
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Couple_Connection addConnect = new Couple_Connection(person1,person2);
		
		if((addConnect.check(c_list) == true) 
				&& addConnect.repeat_check(c_list) == false ){
		
			c_list.add(addConnect);
			success = true;
		}
		
		return success;
	}

    
    
    //access connection list
    public ArrayList<Connection> get_Clist(){
	
    	return c_list;
	}
	
    
    
    //Method to create a array list of profiles connected to a target person
    public ArrayList<Profile> search(Profile target) {  	

		ArrayList<Profile> contain = new ArrayList<Profile>();
		
		for(int i=0;i<c_list.size();i++) {
			
		    Connection tem = c_list.get(i);	
			
		    //Use check tool created in connection to make sure target person is in the connection.
		    if(tem.in(target) == true) {
	
		        if((profile_repeat(tem.getPerson1(), contain) == false)
				    && tem.getPerson1().getID() != target.getID()) {
			
 		 	      contain.add(tem.getPerson1());	
		
		        }
		
		        if((profile_repeat(tem.getPerson2(), contain) == false) 
				    && tem.getPerson2().getID() != target.getID()) {
			
			       contain.add(tem.getPerson2());	
		
		        }
		
		
		        //rule of check parent connection is different.
		        if(tem instanceof Parent_Connection) {
		    	
				    if((profile_repeat(tem.getChild(), contain) == false)
					    &&tem.getChild().getID() != target.getID()) {
				
				     contain.add(c_list.get(i).getChild());
			        }
		        }
		   }
	   }
		
		return contain;
    }
    
    
    
    //Method to create a connection list of profiles connected to a target person
    public ArrayList<Connection> search_clist(Profile target) {  	

		ArrayList<Connection> contain = new ArrayList<Connection>();
				
		for(int i=0;i<c_list.size();i++) {
		
		    Connection tem = c_list.get(i);	
			
		    if(tem.in(target) == true) {
			 
		 	   contain.add(tem);
		    }
	     
	    }
		
		return contain;
		
    }

    
    
    // Checking tool to help search method above: identify if a profile already included in the result of search
    public boolean profile_repeat(Profile target, 
    		                      ArrayList<Profile> check_plist) {
    
    	boolean repeat=false;
    	
    	for(int i = 0;i < check_plist.size();i++) {
    		
    		if(check_plist.get(i).getID() == target.getID()) {
    			
    			repeat = true;
    		}
    	
    	}
    	
    	return repeat;
    }


    
    //Method only for importing data from database.
	public void importList(ArrayList<Connection> readConnections) {
	
		int len = readConnections.size();
		
		for (int i = 0; i < len; i++) {
			
      		 this.addConnection(readConnections.get(i));
		
		}
		
	}

}
    
	
    
    
    