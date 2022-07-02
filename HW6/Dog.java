public class Dog extends Pet {
	
	private double droolRate;

	public Dog(String name, double health, int painLevel, double droolRate) {
		super(name, health, painLevel);
		if (droolRate <= 0.0) {
			this.droolRate = 0.5;
		}
		else {
			this.droolRate = droolRate;
		}
	}
	public Dog(String name, double health, int painLevel) {
		this(name, health, painLevel, 5.0);
	}

	public double getDroolRate() {
		return this.droolRate;
	}
	@Override
	public int treat() {
		double timeTreat = 0;
		if (this.droolRate < 3.5) {
			timeTreat = (getPainLevel()*2)/getHealth();
		}
		else if (this.droolRate < 7.5) {
			timeTreat = getPainLevel()/getHealth();
		}
		else {
			timeTreat = getPainLevel()/(getHealth()*2);
		}
		//I chose to heal after treatment time
		this.heal();
		return (int)Math.ceil(timeTreat);
	}
	public void speak() {
		super.speak();
		String outString = "bark";
		for (int i = 1; i < getPainLevel(); i++) {
			outString = outString.concat(" bark");
		}
		if (getPainLevel() > 5) {
			outString = outString.toUpperCase();
		}
		System.out.println(outString);
	}
	public boolean equals(Object o) {
		if(super.equals(o)) {
			Dog dog = (Dog) o;
			return this.droolRate == dog.droolRate;
		}
		return false;
		
	}

}
