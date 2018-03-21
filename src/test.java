/*
 * This class is a test class with a main method to test all methods 
 * 
 * 
 */
public class test {

	public static void main(String[] args) {
		
		//create five new instances of profiles
		Profile p1=new Adult("A","Work",1,101);//0
		Profile p2=new Adult("B","Work",2,102);//1
		Profile p3=new Adult("C","Work",4,103);//2
		Profile p4=new Adult("D","Work",7,104);//3
		Profile p5=new Adult("E","Home",27,105);//4
		Profile p6=new Adult("F","Home",24,106);//5
		Profile p7=new Adult("G","Work",29,102);//6
		Profile p8=new Adult("H","Work",29,102);//6


		
		


		//create new instance of Profile manager
		ProfileManager test_MP=new ProfileManager();
		
		
		//add all created profiles in profile list
		test_MP.addProfile(p1);
		
		test_MP.addProfile(p2);

		test_MP.addProfile(p3);
		
		test_MP.addProfile(p4);
		
		test_MP.addProfile(p5);
		
		test_MP.addProfile(p6);
		
		test_MP.addProfile(p7);
		
		test_MP.addProfile(p8);



		
		
		
		//Test: check whether profiles are added into the list successfully
		System.out.println("Test add profile:  "+test_MP.get_Plist().get(1).getID());
		
		
		
		// create new ConnectionManager object to access all connections and methods
		ConnectionManager test_MC=new ConnectionManager();

		
		
		// input all profiles from profile manager to connection manager
		test_MC.Pmanager=test_MP;
		
		
		
		/*
		 * Profile p1=new Adult("A","Work",1,101);//0
		Profile p2=new Adult("B","Work",1,102);//1
		Profile p3=new Adult("C","Work",14,103);//2
		Profile p4=new Adult("D","Work",16,104);//3
		Profile p5=new Adult("E","Home",27,105);//4
		Profile p6=new Adult("F","Home",24,106);//5
		Profile p7=new Adult("G","Work",29,102);//6
				Profile p8=new Adult("H","Work",29,102);//7

		

		 * 
		 */	
		//try to add new connections 
		test_MC.addFriendConnection(004, 005);
		
		test_MC.addFriendConnection(005, 007);

		
		test_MC.addCoupleConnection(004, 005);
		
		test_MC.addCoupleConnection(006, 007);
		
		test_MC.addParentConnection(004, 005, 1);
		
		test_MC.addParentConnection(006, 007, 2);
		
		test_MC.addFriendConnection(003, 002);

		

		
		
		
		//Test: whether friend connection is added in connection list
	/*	try {
	    
		System.out.println("Test add connection:  "+test_MC.get_Clist().get(3).getPerson1().getName());
		System.out.println("Test add connection:  "+test_MC.get_Clist().get(3).getPerson2().getName());
		System.out.println("Test add connection:  "+test_MC.get_Clist().get(3).getChild().getName());

		}catch(IndexOutOfBoundsException e) {
			System.out.println("Not valid");
			
		}
		
		*/
		
        try {
			
			for(int i=0;i<test_MP.get_Plist().size();i++) {
				
		System.out.println(i+test_MP.get_Plist().get(i).getName());
	
	
}
			
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Not valid");
				
			}
		
		try {
			
			for(int i=0;i<test_MC.get_Clist().size();i++) {
				
				System.out.println(i+test_MC.get_Clist().get(i).getPerson1().getName()+test_MC.get_Clist().get(i).getPerson2().getName());
	
	
}
			
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Not valid");
				
			}
		
		
		
	
		
		
		
	}

}
