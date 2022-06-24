package edx_Admist_Us;
//concrete subclass?
//this == RedAstronaut, i get it :)
//have to create freeze() and sabotage() here because implements Imposter
//Arrays import is used for .sort()
import java.util.Arrays;
public class RedAstronaut extends Player implements Imposter{
	
	private String skill;
	
	public RedAstronaut(String name, int susLevel, String skill) {
		super(name, susLevel);
		this.skill = skill.toLowerCase();
	}
	
	public RedAstronaut(String name) {
		this(name, 15, "experienced");
	}
	
	public void emergencyMeeting() {
		//frozen ppl cant call meetings
		if (!this.isFrozen()) {
			//generates list of non-frozen players
			Player[] players = Player.getPlayers();
			//sorts them by highest sus level
			Arrays.sort(players);
			//if only one highest sus player
			//can also be written as:
			//if (players[players.length - 1].compareTo(this) != 0)
			//because overwrote compareTo method
			if (players[players.length - 1].getSusLevel() 
					!= players[players.length - 2].getSusLevel()) {
				//if not meeting-caller
				if (players[players.length - 1] != this) {
					//freezes highest sus
					players[players.length - 1].setFrozen(true);
				}
				//if meeting caller is highest sus
				else {
					//if only one 2nd highest sus player
					if (players[players.length - 2].getSusLevel() 
							!= players[players.length - 3].getSusLevel()) {
						//freezes 2nd highest sus
						players[players.length - 2].setFrozen(true);
					}
				}
			}
		}
		gameOver();
	}
	public void freeze(Player p) {
		//we are trying to freeze a crewmate as an imposter
		//frozen imposters cannot freeze
		if (!this.isFrozen()) {
			//it is not possible to freeze another imposter
			if (p instanceof Crewmate) {
				//can only freeze non-frozen players
				if (!p.isFrozen()) {
					//freeze is successful if this.susLevel < p.susLevel
					if (this.getSusLevel() < p.getSusLevel()) {
						p.setFrozen(true);
					}
					//if unsuccessful, susLevel doubles
					else {
						this.setSusLevel(this.getSusLevel() * 2);
					}
				}
			}
		}
		gameOver();
	}
	
	public void sabotage(Player p) {
		//frozen imposters cannot sabo
		//can only sabo crewmates
		//frozen crewmates cannot be sabo'd
		if (!this.isFrozen() && p instanceof Crewmate && !p.isFrozen()) {
			//if impo susLevel < 20, increase p susLevel by 50%
			if (this.getSusLevel() < 20) {
				//need to cast as int bc susLevel has to be int:
				//1*1.5 = 1.5, so forces it down to 1
				p.setSusLevel((int)(p.getSusLevel() * 1.5));
			}
			//if impo susLevel >= 20, increase p susLevel by 25%
			else {
				p.setSusLevel((int)(p.getSusLevel() * 1.25));
			}
		}
	}
	
	public boolean equals(Object o) {
		//two reds are equal if
		if (o instanceof RedAstronaut) {
			//not sure why I have to do this and can't just use o
			RedAstronaut red = (RedAstronaut) o;
			//both have same name
			//frozen
			//susLevel
			//and skill
			return this.getName().equals(red.getName()) 
					&& this.isFrozen() == red.isFrozen()
                    && this.getSusLevel() == red.getSusLevel()
                    && this.skill == red.skill;
		}
		return false;
	}
	
	public String toString() {
		String frozenString = isFrozen() ? "frozen" : "not frozen";
        String returnString = "My name is " + this.getName() + 
        		", and I have a susLevel of " + this.getSusLevel() + 
        		". I am currently " + frozenString + 
        		". I am an " + this.skill + " player!";
        //if susLevel > 15, return all capital letters.
        if (this.getSusLevel() > 15) {
            return returnString.toUpperCase();
        } else {
            return returnString;
        }
	}
}
