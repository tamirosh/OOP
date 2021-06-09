package BusinessLayer;

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
	
	public abstract void gameTick();
	
	public void addExperience(int experiencePoints) {
		this.experience += experiencePoints;
		if(this.experience >= this.maxExperience)
			playerLevelUp();
	}
	
	protected abstract void playerLevelUp();
	
	protected void updateMaxExperience() {
		this.maxExperience = this.playerLevel * maxExperienceVariable;
	}
	
	public abstract void startAttack();
	
}
