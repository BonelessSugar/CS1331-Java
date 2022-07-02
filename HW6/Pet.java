public abstract class Pet {
	
	private String name;
	private double health;
	//0.0 - 1.0
	private int painLevel;
	// 1 - 10
	
	public Pet(String name, double health, int painLevel) {
		this.name = name;
		if (health > 1.0) {
			this.health = 1.0;
		}
		else if (health < 0.0) {
			this.health = 0.0;
		}
		else {
			this.health = health;
		}
		if (painLevel > 10) {
			this.painLevel = 10;
		}
		else if (painLevel < 1) {
			this.painLevel = 1;
		}
		else {
			this.painLevel = painLevel;
		}
	}
	
	public String getName() {
		return this.name;
	}
	public double getHealth() {
		return this.health;
	}
	public int getPainLevel() {
		return this.painLevel;
	}
	
	public abstract int treat();
	
	public void speak() {
		String speakStr = "Hello! My name is " + name;
		if (painLevel > 5) {
			speakStr = speakStr.toUpperCase();
		}
		System.out.println(speakStr);
	}
	
	//Two Pet objects are equal if their names are the same.
	public boolean equals(Object o) {
		//casts input object as Pet
		Pet pet = (Pet) o;
		//checks if current pet equals input pet's name
		return this.name.equals(pet.name);
	}
	protected void heal() {
		this.health = 1.0;
		this.painLevel = 1;
	}
}
