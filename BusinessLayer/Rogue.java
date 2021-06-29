package BusinessLayer;

import java.util.LinkedList;
import java.util.List;

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
    protected void playerLevelUp() {
        this.playerLevel += 1;
        this.experience -= this.getMaxExperience();
        this.updateMaxExperience();
        this.HealthPoolGoUp(this.getPlayerLevel() * 10);
        this.setHealthAmount(this.getHealthPool());
        this.addAttackPoints(this.getPlayerLevel() * 4);
        this.addDefencePoints(this.getPlayerLevel());
        this.addAttackPoints(this.getPlayerLevel() * 3);
        this.setEnergy(100);
    }

    @Override
    public String attack(List<Enemy> enemiesAttacked) {
        if(this.getCurrentEnergy() < this.getSpecialAbilityCost())
            return "there is no enough energy to use the special ability" + "\r\n";
        this.setEnergy(this.getCurrentEnergy() - this.getSpecialAbilityCost());
        List<Enemy> enemiesList = findEnemy(enemiesAttacked);
        if (enemiesList == null || enemiesList.isEmpty())
            return "there's no available enemy to attack" + "\r\n";
        String toReturn = "";
        for (int i = 0; i < enemiesList.size(); i++)
        {
            Enemy unitAttacked = enemiesList.get(i);
            int enemyDefence = unitAttacked.acceptContact(this);
            int attackPower = this.getAttackPoints() - enemyDefence;
            if (attackPower > 0)
                unitAttacked.HealthAmountGoDown(attackPower);
            toReturn += "" + this.getName() + " hit " + unitAttacked.getName() + " with his special ability: " + this.getSpecialAbilityName() + " on " + unitAttacked.getName() + " by " + this.getAttackPoints() + " points." + "\r\n";
            toReturn += unitAttacked.getName() + " defended itself by " + enemyDefence + " points. Now its state is:" + "\r\n";
            toReturn += unitAttacked.getState();
        }
        return toReturn;
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

    @Override
    public void GameTick() {
        this.setEnergy(Math.min(this.getCurrentEnergy() + 10, 100));
    }

    private List<Enemy> findEnemy(List<Enemy> enemiesAttacked) {
        if(enemiesAttacked.isEmpty() || enemiesAttacked == null)
            return null;
        LinkedList<Enemy> availableEnemies = new LinkedList<Enemy>();
        for(int i = 0; i < enemiesAttacked.size(); i++)
        {
            if(this.getPosition().getDistance(enemiesAttacked.get(i).getPosition()) < 2 && enemiesAttacked.get(i).getHealthAmount() != 0)
                availableEnemies.add(enemiesAttacked.get(i));
        }
        if(availableEnemies.isEmpty())
            return null;
        return availableEnemies;
    }

    @Override
    public String getState() {
        if (this.getHealthAmount() != 0)
            return "name: " + this.getName() + " health: " +  this.getHealthAmount() + "/" + this.getHealthPool() + " attack points: " + this.getAttackPoints() +
                    " defence points: " + this.getDefencePoints() + " special ability cost: " + this.getSpecialAbilityCost() + "\r\n";
        else
            return this.getName() + " is dead" + "\r\n";
    }

}