import java.util.ArrayList;

/*
 This class is a subclass of abstract class Connection and represent friend relationship. 
  
 
 */

public  class Friend_Connection extends Connection{

	public Friend_Connection(Profile person1, Profile person2) {
	    super(person1, person2);
		
	}

	public boolean check(ArrayList<Connection> c_list) {

		boolean success=false;

		
		//check normal friend
		
		if(getPerson1().getAge()>=16&&getPerson2().getAge()>=16) {
			success=true;
		};
		

		//check dependent friend
		
		if(getPerson1().getAge()<16&&getPerson2().getAge()<16&&getPerson1().getAge()>2&&getPerson2().getAge()>2) {
			if(getPerson1().getAge()>getPerson2().getAge()) {
				if(getPerson1().getAge()-getPerson2().getAge()<=3) {
					success=true;
					
				}
			}
			
			if(getPerson1().getAge()<getPerson2().getAge()) {
				if(getPerson2().getAge()-getPerson1().getAge()<=3) {
					success=true;
					
				}
			}
		};
		
		return success;
		
		
	}

	/*@Override
	public ArrayList<Profile> search(Profile target) {  	

		ArrayList<Profile> contain=new ArrayList<Profile>();
		
		
		    if(getProfileInside().get())
		
			if(getPerson1().getID()==target.getID()||getPerson2().getID()==target.getID()) {
				screen+="\n<Friend>  "+getPerson1().getName()+" --- "+getPerson2().getName();
			}
		
		return contain;
		
		
    }*/
	
	}
	
	
	

