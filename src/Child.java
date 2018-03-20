<<<<<<< HEAD

public class Child extends Profile {	
	
	private Profile parent1;
	private Profile parent2;

	public Child (String name, String status, int age, Profile parent1, Profile parent2) {
		super(name, status, age);
		this.parent1 = parent1;
		this.parent2 = parent2;
		
	}
	
	public Child (String name, int age, Profile parent1, Profile parent2) {
		super(name, age);
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	
	
	

}
=======

public class Child extends Profile {	
	
	private Profile parent1;
	private Profile parent2;

	public Child (String name, String status, int age, Profile parent1, Profile parent2) {
		super(name, status, age);
		this.parent1 = parent1;
		this.parent2 = parent2;
		
	}
	
	public Child (String name, int age, Profile parent1, Profile parent2) {
		super(name, age);
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	
	
	

}
>>>>>>> e9c1847a423b5097e63beba66d56749ec811e688
