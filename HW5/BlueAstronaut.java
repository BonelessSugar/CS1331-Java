package edx_Admist_Us;
//concrete subclass?
//this == BlueAstronaut
//have to create freeze() and sabotage() here because implements Imposter
//Arrays import is used for .sort()
import java.util.Arrays;
public class BlueAstronaut extends Player implements Crewmate{
	
	private int numTasks;
	private int taskSpeed;
	
	public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
		super(name, susLevel);
		this.numTasks = numTasks;
		try {
			if(taskSpeed > 0)
				this.taskSpeed = taskSpeed;
		}
		catch (Exception e) {
		}
	}
	
	public BlueAstronaut(String name) {
		this(name, 15, 6, 10);
	}
	
	//very similar to RedAstro emergencyMeeting()
	public void emergencyMeeting() {
		//frozen ppl cant call meetings
		if (!this.isFrozen()) {
			//generates list of non-frozen players
			Player[] players = Player.getPlayers();
			//sorts them by highest sus level
			Arrays.sort(players);
			//if only one highest sus player
			if (players[players.length - 1].getSusLevel() 
					!= players[players.length - 2].getSusLevel()) {
				players[players.length - 1].setFrozen(true);
			}
		}
		gameOver();
	}
	
	public void completeTask() {
		//only can happen once
		if(this.numTasks != 0) {
			//frozen crew cannot complete tasks
			if(!this.isFrozen()) {
				if(this.taskSpeed > 20) {
					this.numTasks -= 2;
				} 
				else {
					this.numTasks -= 1;
				}
				//numTasks have to be 0 or greater
				if(this.numTasks < 0) {
					this.numTasks = 0;
				}
				if(this.numTasks == 0) {
					System.out.println("I have completed all my tasks");
					this.setSusLevel((int)(this.getSusLevel() / 2));
				}
			}
		}
	}
	
	public boolean equals(Object o) {
		//two blues are equal if
		if (o instanceof BlueAstronaut) {
			//not sure why I have to do this and can't just use o
			BlueAstronaut blue = (BlueAstronaut) o;
			//both have same name
			//frozen
			//susLevel
			//numTasks
			//and taskSpeed
			return this.getName().equals(blue.getName()) 
					&& this.isFrozen() == blue.isFrozen()
                    && this.getSusLevel() == blue.getSusLevel()
                    && this.numTasks == blue.numTasks
                    && this.taskSpeed == blue.taskSpeed;
		}
		return false;
	}
	
	public String toString() {
		String frozenString = isFrozen() ? "frozen" : "not frozen";
        String returnString = "My name is " + this.getName() + 
        		", and I have a susLevel of " + this.getSusLevel() + 
        		". I am currently " + frozenString + 
        		". I have  " + this.numTasks + " left over.";
        //if susLevel > 15, return all capital letters.
        if (this.getSusLevel() > 15) {
            return returnString.toUpperCase();
        } else {
            return returnString;
        }
	}
	
}
