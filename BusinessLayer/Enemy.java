package BusinessLayer;

public abstract class Enemy extends Unit
{
    protected boolean isPlayerInRange;
    protected int experienceValue;
    
    public Enemy(char tileType,Position position,String name,Health enemyHealth,int attackPoints,int defensePoints,int experienceValue) {
        super(tileType, position, name, enemyHealth, attackPoints, defensePoints);
        this.experienceValue = experienceValue;
    }
    
    public int getExperienceValue() {
        return this.experienceValue;
    }

    @Override
	public String contactWith(Player p) {
    	int thisRoll = (int)(Math.random() * this.attackPoints);
		int attackedRoll = p.acceptContact(this);
		String answer = "" + this.getName() + " tried to step on " + p.getName() + "." + "/r/n";
		answer += "" + this.getName() + " rolled " + thisRoll + " and " + p.getName() + " rolled " + attackedRoll + "." + "/r/n";
		if (thisRoll > attackedRoll)
		{
			p.HealthAmountGoDown((thisRoll-attackedRoll));
			answer += "" + p.getName() + " was damaged by " + (thisRoll-attackedRoll) + " helth points." + "/r/n";
			
		}
		else
			answer += "there's no damage.";
    	return answer;
    }
	
	@Override
	public String contactWith(Enemy e) {
		return "";
    }
	
	public abstract Position act(Position playerPosition);

}
