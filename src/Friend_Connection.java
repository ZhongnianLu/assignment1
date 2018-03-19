import java.util.ArrayList;

/*
 This class is a subclass of abstract class Connection and represent friend relationship. 
  
 
 */

public  class Friend_Connection extends Connection{

	public Friend_Connection(Profile person1, Profile person2) {
	    super(person1, person2);
		
	}

	public boolean check(ArrayList<Connection> c_list) {

		if(getPerson1().getAge()<16||getPerson2().getAge()<16) {
			return false;
		}else return true;
		
		
	}
	
	

	
	
	
	
	
}
