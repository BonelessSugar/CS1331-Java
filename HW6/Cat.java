public class Cat extends Pet {
	
	private int miceCaught;
	
	public Cat(String name, double health, int painLevel, int miceCaught) {
		super(name, health, painLevel);
		if (miceCaught < 0) {
			this.miceCaught = 0;
		}
		else {
			this.miceCaught = miceCaught;
		}
	}
	public Cat(String name, double health, int painLevel) {
		this(name, health, painLevel, 0);
	}
	
	public double getMiceCaught() {
		return miceCaught;
	}
	
	@Override
	public int treat() {
		double timeTreat = 0;
		if (this.miceCaught < 4) {
			timeTreat = (getPainLevel()*2)/getHealth();
		}
		else if (this.miceCaught < 7) {
			timeTreat = getPainLevel()/getHealth();
		}
		else {
			timeTreat = getPainLevel()/(getHealth()*2);
		}
		//treating after, my choice.
		this.heal();
		return (int)Math.ceil(timeTreat);
	}
	
	public void speak() {
		super.speak();
		String outString = "meow";
		for (int i = 1; i < this.miceCaught; i++) {
			outString = outString.concat(" meow");
		}
		if (getPainLevel() > 5) {
			outString = outString.toUpperCase();
		}
		System.out.println(outString);
	}
	
	public boolean equals(Object o) {
		if(super.equals(o)) {
			Cat cat = (Cat) o;
			return this.miceCaught == cat.miceCaught;
		}
		return false;
	}
}
