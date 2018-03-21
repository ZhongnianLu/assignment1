
public class Child extends Profile {	
	
	private Profile parent1;
	private Profile parent2;

	public Child (String name, String status, int age) {
		super(name, status, age);
	}
	
	
	public Child (String name, int age, Profile parent1, Profile parent2) {
		super(name, age);
	}
	
	
	

}
