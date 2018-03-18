import java.util.ArrayList;


/*
 * This class contains a list of all connections.
 * 
 * This class contains all methods to add different types of new connection.
 * 
 * This class contains all methods to check whether new connection is valid or not: age check for friend & parent check for child
 */

public class ConnectionManager {

	//Access list of connections
	ArrayList<Connection> c_list=new ArrayList<Connection>();
	
	//Provide an instance of Profile manager to access profile list
	ProfileManager Pmanager;
	
	
	//Method to create a friend connection
	void addFriendConnection(int ID_1,int ID_2){
		
		
		ArrayList<Profile> tem_pList=Pmanager.get_Plist();
		
		Profile person1 = null;
		Profile person2 = null;
		
		//create a boolean to check repeat
		boolean repeat=false;
		
		//get profiles from profile list with same ID as parameter 
		for(int i=0;i<tem_pList.size();i++) {
			if(tem_pList.get(i).getID()==ID_1||tem_pList.get(i).getID()==ID_2&&repeat==false) {
				person1=tem_pList.get(i);
				repeat=true;	
			}
			
			if(tem_pList.get(i).getID()==ID_1||tem_pList.get(i).getID()==ID_2&&repeat==true) {
				person2=tem_pList.get(i);
			}
			
		};
		
		//create a new connection with selected profiles
		
		Friend_Connection addConnect=new Friend_Connection(person1,person2);
		
		//check whether the friend connection is valid by calling age check method 
		if(age_Check(addConnect)==true) {c_list.add(addConnect);
		}

		else System.out.println("One of person in the connection is under valid age.");;
		}
	
	
	
	
	
	
	// add new parent connection by passing three IDs including parents and child
    void addParentConnection(int ID_1,int ID_2,int ID_child){
	
	    ArrayList<Profile> tem_pList=Pmanager.get_Plist();
		
		
		Profile person1 = null;
		Profile person2 = null;
		Profile child=null;
		boolean repeat=false;
		
		
		//get parents' and child's profiles from the profile list by checking their IDs 
		for(int i=0;i<tem_pList.size();i++) {
			if(tem_pList.get(i).getID()==ID_1||tem_pList.get(i).getID()==ID_2&&repeat==false) {
				person1=tem_pList.get(i);
				repeat=true;	
			}
			
			if(tem_pList.get(i).getID()==ID_1||tem_pList.get(i).getID()==ID_2&&repeat==true) {
				person2=tem_pList.get(i);
			}
			
			if(tem_pList.get(i).getID()==ID_child) {
				child=tem_pList.get(i);
			}
			
			
		};
		
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Parent_Connection addConnect=new Parent_Connection(person1,person2,child);
		
		if(parent_Check(addConnect)==true&&age_Check(addConnect)==true) {c_list.add(addConnect);
		}
}

    
    
    
    void addCoupleConnection(int ID_1,int ID_2){
    	
	    ArrayList<Profile> tem_pList=Pmanager.get_Plist();
		
		
		Profile person1 = null;
		Profile person2 = null;
		boolean repeat=false;
		
		
		//get parents' and child's profiles from the profile list by checking their IDs 
		for(int i=0;i<tem_pList.size();i++) {
			if(tem_pList.get(i).getID()==ID_1||tem_pList.get(i).getID()==ID_2&&repeat==false) {
				person1=tem_pList.get(i);
				repeat=true;	
			}
			
			if(tem_pList.get(i).getID()==ID_1||tem_pList.get(i).getID()==ID_2&&repeat==true) {
				person2=tem_pList.get(i);
			}
						
     		};
		
		//check whether the parent connection is valid by calling parent check method passing IDs of parents 
		Couple_Connection addConnect=new Couple_Connection(person1,person2);
		
		if(couple_Check(addConnect)==false&&age_Check(addConnect)==true) {c_list.add(addConnect);
		}

		
	}

    
    
    
    
    
    //access connection list
    public ArrayList<Connection> get_Clist(){
		return c_list;
	}
	
	
    
    
    // method to check age: comparing each age with age limitation ( years old)
	public boolean age_Check(Connection addConnect) {
		if(addConnect.getPerson1().getAge()<16||addConnect.getPerson2().getAge()<16) {
			return false;
		}else return true;
		
	}
	
	
	
	
	
	
	// method to check whether the parents in the family connection has a friend connection
	public boolean parent_Check(Connection addConnect) {
		
		boolean parent_check=false;
		
		
		for(int i=0;i<c_list.size();i++) {
			
			Profile person_x;
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			boolean connection_check=false;
			
			person_x=c_list.get(i).getPerson1();
			
			person_y=c_list.get(i).getPerson2();
			
			
			if(person_x.getID()==addConnect.getPerson1().getID()&&person_y.getID()==addConnect.getPerson2().getID()) {
				connection_check=true;
			}
			
			if(person_x.getID()==addConnect.getPerson2().getID()&&person_y.getID()==addConnect.getPerson1().getID()) {
				connection_check=true;
			}
			
			//check whether the connection we found is a friend connection
			if(c_list.get(i) instanceof Couple_Connection&&connection_check==true) {
				parent_check=true;
			}
		}
		
		return parent_check;
	}
	

	
	
	
	public boolean couple_Check(Connection addConnect) {
		
		boolean couple_repeat=false;
		
		
		
		for(int i=0;i<c_list.size();i++) {
			
			Profile person_x;
			Profile person_y;
			
			//create a boolean to help find target connection that contains two IDs we inputed 
			
			person_x=c_list.get(i).getPerson1();
			
			person_y=c_list.get(i).getPerson2();
			
			if(c_list.get(i) instanceof Couple_Connection) {
			if(person_x.getID()==addConnect.getPerson1().getID()||person_y.getID()==addConnect.getPerson2().getID()) {
				couple_repeat=true;
			}
			
			if(person_x.getID()==addConnect.getPerson2().getID()||person_y.getID()==addConnect.getPerson1().getID()) {
				couple_repeat=true;
			}
			}
			}
		
		
		return couple_repeat;
	}
}
