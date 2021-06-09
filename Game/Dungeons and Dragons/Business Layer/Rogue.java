package BusinessLayer;

public class Rogue extends Player{
	
	//fields:
	private int Energy;
	private final int specialAbilityCost;
	
	//constructor:
	public Rogue(Position position, String name, Health health, int attackPoints, int defencePoints, int specialAbilityCost) {
		super(position, name, health, attackPoints, defencePoints, "Fan of Knives");
		this.setEnergy(100);
		this.specialAbilityCost = specialAbilityCost;
	}

	@Override
	public void gameTick() {
		this.setEnergy(Math.min(this.getCurrentEnergy() + 10, 100));
	}

	@Override
	protected void playerLevelUp() {
		this.playerLevel += 1;
		this.experience -= this.getMaxExperience();
		this.updateMaxExperience();
		this.addAttackPoints(this.getPlayerLevel() * 3);
	}
	
	public void startAttack() {
		this.setEnergy(this.getCurrentEnergy() - this.getSpecialAbilityCost());
		
	}

	@Override
	public String attack(Unit unitAttacked) {
		unitAttacked.HealthAmountGoDown(this.getAttackPoints());
		String toReturn = "" + this.getName() + " attacked " + unitAttacked.getName() + " with his special ability: " + this.getSpecialAbilityName() + "\r\n";;
		toReturn += unitAttacked.attack(this);
		return toReturn;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	protected void setEnergy(int energy) {
		if (energy >= 0)
			this.Energy = energy;
	}
	
	protected int getCurrentEnergy() {
		return this.Energy;
	}
	
	protected int getSpecialAbilityCost() {
		return this.specialAbilityCost;
	}
}
