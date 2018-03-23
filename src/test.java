import java.util.ArrayList;

/*
 * This class is a test class with a main method to test all methods 
 * 
 * 
 */
public class test {

	public static void main(String[] args) {
		
		//create five new instances of profiles
		Profile p1=new Profile("A","Work",1);//0
		Profile p2=new Profile("B","Work",2);//1
		Profile p3=new Profile("C","Work",4);//2
		Profile p4=new Profile("D","Work",7);//3
		Profile p5=new Profile("E","Home",27);//4
		Profile p6=new Profile("F","Home",24);//5
		Profile p7=new Profile("G","Work",29);//6
		Profile p8=new Profile("H","Work",29);//7


		
		


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
		
		test_MC.Pmanager=test_MP;
		

		
		if(test_MC.addFriendConnection(5,6)==true) {
			System.out.println("Success");
		}else{System.out.println("Fails");};

		
		
		// input all profiles from profile manager to connection manager
		
		
		
		/*
		 * 
		Profile p1=new Adult("A","Work",1);//0
		Profile p2=new Adult("B","Work",2);//1
		Profile p3=new Adult("C","Work",4);//2
		Profile p4=new Adult("D","Work",7);//3
		Profile p5=new Adult("E","Home",27);//4
		Profile p6=new Adult("F","Home",24);//5
		Profile p7=new Adult("G","Work",29);//6
		Profile p8=new Adult("H","Work",29);//7

		

		 * 
		 */	
		//try to add new connections 
	

		
		test_MC.addCoupleConnection(005, 006);
		
		test_MC.addCoupleConnection(7, 8);
		
		test_MC.addParentConnection(006, 005, 1);
		
		test_MC.addParentConnection(006, 005, 2);

		

		
		
		
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
		
		ArrayList<Profile> search_list=test_MC.search(p1);
		
		System.out.println(test_MC.get_Clist().get(0).in(p3));
		
		for(int i=0;i<search_list.size();i++) {
	
			System.out.print(search_list.get(i).getName());
			
			
		
		}
		}
		
	
		
		
		
	}


