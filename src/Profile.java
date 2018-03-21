//import java.util.ArrayList;

public class Profile {
	
	private String name;
	private String image;
	private String status;
	private int age;
	private int ID;
	
	public Profile (String name, String status, int age) {
		this.name = name;
		this.image = "default.png";
		this.status = status;
		this.age = age;
	}
	
	public Profile (String name, int age) {
		this(name, null, age);
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getID() {
		return this.ID;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("Your name has been changed");
	}
	
	public void setStatus(String status) {
		this.status = status;
		System.out.println("Your status has been changed");

	}
	
	public void setImage(String image) {
		this.image = image;
		System.out.println("Your name has been changed");
	}
	
	public void setID(int ID) {
		this.ID=ID;
	}
	
	
	
	
	
}
