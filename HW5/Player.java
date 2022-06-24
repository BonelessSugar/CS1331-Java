package edx_Admist_Us;
//basically all of this class was already provided
//abstract class?
public abstract class Player implements Comparable<Player>{
	
	private String name;
	private int susLevel;
	private boolean frozen;
	private static Player[] players;
	
	public Player(String name, int susLevel) {
		this.name = name;
		if (susLevel < 0) {
			//figure out why they use this.susLevel
			this.susLevel = 0;
		}
		else {
			this.susLevel = susLevel;
		}
		if (players == null) {
			players = new Player[1];
			players[0] = this;
		}
		else {
			int length = players.length + 1;
            Player[] temp = players;
            players = new Player[length];
            for (int i = 0; i < temp.length; i++) {
                players[i] = temp[i];
            }
            players[players.length - 1] = this;
		}
	}
	
	public abstract void emergencyMeeting();
	
	//they told me to put @Override there to override the compareTo method
	@Override
		public int compareTo(Player p) {
		if (this.susLevel < p.susLevel) {
            return -1;
        } else if (this.susLevel > p.susLevel) {
            return 1;
        } else {
            return 0;
        }
	}
	
	//generated for me starts here
	
	public boolean equals(Object o) {
        if (o instanceof Player) {
            Player player = (Player) o;
            return this.name.equals(player.name) && this.frozen == player.frozen
                    && this.susLevel == player.susLevel;
        }
        return false;
    }
	
	public String toString() {
        String frozenString = frozen ? "frozen" : "not frozen";
        return "My name is " + this.name + ", and I have a susLevel of "
                + this.susLevel + ". I am currently " + frozenString + ".";
    }
	
	public boolean gameOver() {
        int impostorCount = 0;
        int crewmateCount = 0;
        for (Player p : players) {
        	//had to change Impostor to Imposter
            if (p instanceof Imposter && !p.frozen) {
                impostorCount++;
            } else if (p instanceof Crewmate && !p.frozen) {
                crewmateCount++;
            }
        }
        if (impostorCount == 0) {
            System.out.println("Crewmates Win!");
            return true;
        } else if (impostorCount >= crewmateCount) {
            System.out.println("Impostors Win!");
            return true;
        }
        return false;
    }
	
	public String getName() {
        return name;
    }
	
	public int getSusLevel() {
        return susLevel;
    }
	
	public void setSusLevel(int susLevel) {
        if (susLevel >= 0) {
            this.susLevel = susLevel;
        } else {
            this.susLevel = 0;
        }
    }
	
	public boolean isFrozen() {
        return frozen;
    }
	
	public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
	
	public static Player[] getPlayers() {
        return players;
    }
}
