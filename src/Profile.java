
public  class Profile {
	
	 private String name;
	 private String status;
	 private int age;
	 private int ID;
     
	 
	 
    public Profile(String name,  String status, int age, int ID) {
    	this.name=name;
    	this.status=status;
    	this.age=age;
    	this.ID=ID;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public int getAge() {
    	return age;
    }
    
    public int getID() {
    	return ID;
    }
    

}
