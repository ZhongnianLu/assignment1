import java.util.ArrayList;

/*
 This class is a subclass of abstract class Connection and represent friend relationship. 
  
 
 */

public  class Friend_Connection extends Connection{

	//Constructor inheritance from super class
	public Friend_Connection(Profile person1, Profile person2) {
	    
		super(person1, person2);
	
	}

	
	
	//Override check method from super class. Friend connections include dependent friend and adult friend.
	public boolean check(ArrayList<Connection> c_list) {

		boolean success=false;
	
		//check normal friend
		if(getPerson1().getAge() >= 16 && getPerson2().getAge() >= 16) {
			
			success=true;
			
		};
		
		//check dependent friend
		if(getPerson1().getAge() < 16 && getPerson2().getAge() < 16
				&& getPerson1().getAge() > 2 && getPerson2().getAge() > 2) {
			
			if(getPerson1().getAge() > getPerson2().getAge()) {
				
				if(getPerson1().getAge() - getPerson2().getAge() <= 3) {
				
					success=true;
				}
			}
			
			if(getPerson1().getAge() < getPerson2().getAge()) {
				
				if(getPerson2().getAge() - getPerson1().getAge() <= 3) {
					
					success=true;
					
				}
			}
		};
		
		return success;
		
	}

	}
	
	
	

