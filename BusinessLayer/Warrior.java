package BusinessLayer;

import java.util.LinkedList;
import java.util.List;

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
    public int getAbilityCooldown() {
        return this.abilityCooldown;
    }

    public int getRemainingCooldown() {
        return this.remainingCooldown;
    }

    @Override
    protected void playerLevelUp() {
        this.playerLevel += 1;
        this.experience -= this.getMaxExperience();
        this.updateMaxExperience();
        this.HealthPoolGoUp(this.getPlayerLevel() * 10);
        this.remainingCooldown = 0;
        this.HealthAmountGoUp(this.playerLevel * 5);
        this.addAttackPoints(this.playerLevel * 2);
        this.addDefencePoints(this.playerLevel * 2);
    }


    @Override
    public String attack(List<Enemy> enemiesAttacked) {
        if (this.remainingCooldown == 0)
        {
            Enemy enemyAttacked = findEnemy(enemiesAttacked);
            if (enemyAttacked == null)
                return "there's no available enemy to attack" + "\r\n";
            int enemyDefence = enemyAttacked.acceptContact(this);
            int attackPower = (this.getHealthPool() / 10) - enemyDefence;
            if (attackPower > 0)
                enemyAttacked.HealthAmountGoDown(attackPower);
            this.HealthAmountGoUp(this.getDefencePoints() * 10);
            this.remainingCooldown = this.abilityCooldown;
            return "" + this.getName() + " used is special ability: " + this.getSpecialAbilityName() + " on " + enemyAttacked.getName() + " by " + attackPower + " points." + "\r\n" +
                    "now its state is: " + "\r\n" + enemyAttacked.getState();
        }
        else
            return "cannot use the special ability, just " + this.remainingCooldown + " turns until you could use it" + "\r\n";
    }

    @Override
    public void GameTick() {
        if (this.remainingCooldown > 0)
            this.remainingCooldown--;
    }

    private Enemy findEnemy(List<Enemy> enemiesAttacked) {
        if(enemiesAttacked.isEmpty() || enemiesAttacked == null)
            return null;
        LinkedList<Enemy> availableEnemies = new LinkedList<Enemy>();
        for(int i = 0; i < enemiesAttacked.size(); i++)
        {
            if(this.getPosition().getDistance(enemiesAttacked.get(i).getPosition()) < 3)
                availableEnemies.add(enemiesAttacked.get(i));
        }
        if(availableEnemies.isEmpty())
            return null;
        return enemiesAttacked.get((int)(Math.random() * (availableEnemies.size() - 1)));
    }

    @Override
    public String getState() {
        if (this.getHealthAmount() != 0)
            return "name: " + this.getName() + " health: " +  this.getHealthAmount() + "/" + this.getHealthPool() +
                    " attack points: " + this.getAttackPoints() + " defece points: " + this.getDefencePoints() + " cooldown: " + this.getRemainingCooldown() + "\r\n";
        else
            return this.getName() + " is dead" + "\r\n";
    }

}