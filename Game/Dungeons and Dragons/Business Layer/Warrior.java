package BusinessLayer;

public class Warrior extends Player{
	
	//fields:
	private final int abilityCooldown;
	private int remainingCooldown;
	
	//constructor:
	public Warrior(Position position, String name, Health health, int attackPoints, int defencePoints, final int abilityCooldown) {
		super(position, name, health, attackPoints, defencePoints, "Avenger’s Shield");
		this.remainingCooldown = 0;
		this.abilityCooldown = abilityCooldown;
	}
	
	//methods:
	@Override
	protected void playerLevelUp() {
		this.playerLevel += 1;
		this.experience -= this.getMaxExperience();
		this.updateMaxExperience();
		this.remainingCooldown = 0;
		this.HealthAmountGoUp(this.playerLevel * 5);
		this.addAttackPoints(this.playerLevel * 2);
		this.addDefencePoints(this.playerLevel * 2);
	}
	
	public int getAbilityCooldown() {
		return this.abilityCooldown;
	}
	
	public int getRemainingCooldown() {
		return this.remainingCooldown;
	}
	
	public String toString() {
		return getName();
	}
	
	public void gameTick() {
		if (this.remainingCooldown > 0)
			this.remainingCooldown--;
	}
	
	@Override
	public void startAttack() {
	}
	
	@Override
	public String attack(Unit unitAttacked) {
		if (this.remainingCooldown == 0) 
		{
			unitAttacked.HealthAmountGoDown(this.getHealthPool()/10);
			this.HealthAmountGoUp(this.getDefencePoints() * 10);
			this.remainingCooldown = this.abilityCooldown;
			return "" + this.getName() + " used is special ability: " + this.getSpecialAbilityName() + " on " + unitAttacked.getName() + "\r\n";
		}
		else
			return "cannot use the special ability, just " + this.remainingCooldown + "turns until you could use it" + "\r\n";
	}
	
}
