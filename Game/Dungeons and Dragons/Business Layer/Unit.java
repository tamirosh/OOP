
public abstract class Unit extends Tile{
	
	//fields:
	private String Name;
	private Health health;
	private int attackPoints;
	private int defencePoints;
	
	//constructor:
	public Unit(char typeTile, Position position, String Name, Health helth, int attackPoints, int defencePoints) {
		super(typeTile, position);
		this.Name = Name;
		this.health = health;
		this.attackPoints = attackPoints;
		this.defencePoints = defencePoints;
	}
	
	//methods:
	public String getName() {
		return Name;
	}
	
	public int getHealthPool() {
		return this.healthPool;
	}
	
	public int getHealthAmount() {
		return this.healthAmount;
	}
	
	public int getAttackPoints() {
		return this.attackPoints;
	}
	
	public int getDefencePoints() {
		return this.defencePoints;
	}
	
	public void subtractAttackPoints(int points) {
		this.attackPoints -= points;
	}
	
	public void addAttackPoints(int points) {
		this.attackPoints += points;
	}
	
	public void subtractDefencePoints(int points) {
		this.defencePoints -= points;
	}
	
	public void addDefencePoints(int points) {
		this.defencePoints += points;
	}
	
	public void subtractHealthAmount(int healthPoints) {
		this.healthAmount -= healthPoints;
	}
	
	public void addHealthAmount(int healthPoints) {
		this.healthAmount += healthPoints;
	}
	
	public void updateHealthPool (int newHealthPool) {
		this.healthPool = newHealthPool;
	}
	
	public abstract String toString();
	
	public abstract String attack(Unit unit);
	
}
