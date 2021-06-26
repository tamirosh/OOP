package BusinessLayer;

import java.util.List;

public abstract class Player extends Unit {
	
	//fields:
	protected int experience;
	protected int maxExperience;
	protected int playerLevel;
	protected String specialAbilityName;
	protected final int initialExperience = 0;
	protected final int initialPlayerLevel = 1;
	protected final int maxExperienceVariable = 50; //the number for calculate the "maxExperience" variable
	
	//constructor:
	public Player(Position position, String name, Health health, int attackPoints, int defencePoints, String specialAbilityName) {
		super('@', position, name, health, attackPoints, defencePoints);
		experience = initialExperience;
		playerLevel = initialPlayerLevel;
		updateMaxExperience();
		this.specialAbilityName = specialAbilityName;
	}
	
	//methods:
	public int getExperience() {
		return this.experience;
	}
	
	public int getMaxExperience() {
		return this.maxExperience;
	}
	
	public int getPlayerLevel() {
		return this.playerLevel;
	}
	
	public String getSpecialAbilityName() {
		return this.specialAbilityName;
	}
	
	public void addExperience(int experiencePoints) {
		this.experience += experiencePoints;
		if(this.experience >= this.maxExperience)
			playerLevelUp();
	}
	
	protected void updateMaxExperience() {
		this.maxExperience = this.playerLevel * maxExperienceVariable;
	}
    
    @Override
	public String contactWith(Player p) {
    	return ""; //not used
    }
	
	@Override
	public String contactWith(Enemy e) {
		int thisRoll = (int)(Math.random() * this.attackPoints);
		int attackedRoll = e.acceptContact(this);
		String answer = "" + this.getName() + " tried to step on " + e.getName() + "." + "/r/n";
		answer += "" + this.getName() + " rolled " + thisRoll + " and " + e.getName() + " rolled " + attackedRoll + "." + "/r/n";
		if (thisRoll > attackedRoll)
		{
			e.HealthAmountGoDown((thisRoll-attackedRoll));
			answer += "" + e.getName() + " was damaged by " + (thisRoll-attackedRoll) + " helth points.";
			if (e.getHealthAmount() == 0)
				this.switchPositions(e);
		}
		else
			answer += "there's no damage.";
    	return answer;
    }
	
	@Override
	public String contactWith(Empty e) {
    	this.switchPositions(e);
    	return "the player moved";
    }
	
	@Override
	public String contactWith(Wall w) {
    	return "the player can't go into wall";
    }
	
	@Override
	public void HealthAmountGoDown(int healthDecreased) {
		this.health.HealthAmountGoDown(healthDecreased);
		if (this.getHealthAmount() == 0)
			this.typeTile = 'X';
	}
	
	@Override
	public String toString() {
		return "" + getTypeTile();
	}
	
	public void setPosition(Position p) {
		this.position = p;
	}
	
	protected abstract void playerLevelUp();
    
    protected abstract String attack(List<Enemy> enemiesAttacked); // using special ability
    
}