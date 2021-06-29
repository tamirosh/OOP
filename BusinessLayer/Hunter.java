package BusinessLayer;

import java.util.List;

public class Hunter extends Player{

    //fields:
    private int range;       // shooting range, received as a constructor argument.
    private int arrowCount;  //  Integer, current amount of arrows.
    private int ticksCount;

    //constructor:
    public Hunter(Position position, String name, Health health, int attackPoints, int defencePoints, int range) {
        super(position, name, health, attackPoints, defencePoints, "Shoot");
        this.range = range;
        this.arrowCount = this.getPlayerLevel() * 10;
        this.ticksCount = 0;
    }

    //methods:
    protected int getArrowCount() {
        return this.arrowCount;
    }

    protected int getRange() {
        return this.range;
    }

    protected int getTicksCount() {
        return this.ticksCount;
    }

    @Override
    protected void playerLevelUp() {
        this.experience -= this.getMaxExperience();
        this.playerLevel += 1;
        this.updateMaxExperience();
        this.HealthPoolGoUp(this.getPlayerLevel() * 10);
        this.setHealthAmount(this.getHealthPool());
        this.addAttackPoints(this.getPlayerLevel() * 4);
        this.addDefencePoints(this.getPlayerLevel());
    }

    @Override
    public String attack(List<Enemy> enemiesAttacked) {
        Enemy enemyToAttack = this.findClosetEnemy(enemiesAttacked);
        if (enemyToAttack == null)
            return "" + this.getName() + " tried to use his special ability \"" + this.getSpecialAbilityName() + "\", but there is no one in his ability range";
        else
        {
            enemyToAttack.HealthAmountGoDown(this.getAttackPoints());
            return "" + this.getName() + " used is special ability: \"" + this.getSpecialAbilityName() + "\" on " + enemyToAttack.getName() + " and hit him by " + this.getAttackPoints() + " points" + "\r\n";
        }
    }

    @Override
    public void GameTick() {
        if(this.getTicksCount() == 10)
        {
            this.arrowCount += this.getPlayerLevel();
            this.ticksCount = 0;
        }
        else
            this.ticksCount += 1;
    }

    private Enemy findClosetEnemy(List<Enemy> enemies) {
        Enemy theCloset = null;
        double minRange = 0;
        if (enemies == null)
            return null;
        for (int i = 0; i < enemies.size(); i++)
        {
            double enemyRange = this.getPosition().getDistance(enemies.get(i).getPosition());
            if (enemyRange <= this.getRange())
            {
                if(minRange == 0 || enemyRange < minRange)
                {
                    theCloset = enemies.get(i);
                    minRange = enemyRange;
                }
            }
        }
        return theCloset;
    }

    @Override
    public String getState() {
        if (this.getHealthAmount() != 0)
            return "name: " + this.getName() + " health: " +  this.getHealthAmount() + "/" + this.getHealthPool() +
                    " attack points: " + this.getAttackPoints() + " defece points: " + this.getDefencePoints() + " arrow count: " + this.getArrowCount() + "\r\n";
        else
            return this.getName() + " is dead" + "\r\n";
    }
}
