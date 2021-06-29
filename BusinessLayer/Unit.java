package BusinessLayer;
public abstract class Unit extends Tile {

    //fields:
    protected String Name;
    protected Health health;
    protected int attackPoints;
    protected int defencePoints;

    //constructor:
    public Unit(char typeTile, Position position, String Name, Health health, int attackPoints, int defencePoints) {
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
        return this.health.getHealthPool();
    }

    public int getHealthAmount() {
        return this.health.getHealthAmount();
    }

    public void HealthPoolGoUp(int healthAdded) {
        this.health.HealthPoolGoUp(healthAdded);
    }

    public void HealthAmountGoUp(int healthAdded) {
        this.health.HealthAmountGoUp(healthAdded);
    }

    public void HealthAmountGoDown(int healthDecreased) {
        this.health.HealthAmountGoDown(healthDecreased);
    }

    public void setHealthAmount(int points) {
        this.health.setHealthAmount(points);
    }

    public int getAttackPoints() {
        return this.attackPoints;
    }

    public int getDefencePoints() {
        return this.defencePoints;
    }

    protected void subtractAttackPoints(int points) {
        this.attackPoints -= points;
    }

    protected void addAttackPoints(int points) {
        this.attackPoints += points;
    }

    protected void subtractDefencePoints(int points) {
        this.defencePoints -= points;
    }

    protected void addDefencePoints(int points) {
        this.defencePoints += points;
    }

    public String getState() {
        if (this.getHealthAmount() != 0)
            return "name: " + this.getName() + " health: " +  this.getHealthAmount() + "/" + this.getHealthPool() +
                    " attack points: " + this.getAttackPoints() + " defece points: " + this.getDefencePoints() + "\r\n";
        else
            return this.getName() + " is dead" + "\r\n";
    }

    @Override
    public int acceptContact(TileVisitor t) {
        return (int)(Math.random() * getDefencePoints());
    }

    @Override
    public String contactWith(Wall w) {
        return "";
    }

    @Override
    public String contactWith(Empty e) {
        this.switchPositions(e);
        return "";
    }

    public abstract void GameTick();
}