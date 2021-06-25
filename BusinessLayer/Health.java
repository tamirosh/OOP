package BusinessLayer;

public class Health {
    protected int healthPool;
    protected int healthAmount;
    
    public Health(int healthPool) {
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
    }
    public int getHealthPool() {
        return this.healthPool;
    }
    public int getHealthAmount() {
        return this.healthAmount;
    }
    public int healthLevelUp(int level) {
        this.healthPool =this.healthPool+ 10 * level;
        this.healthAmount = this.healthPool;
        return 10 * level;
    }
    public void HealthPoolGoUp(int healthAdded) {
        this.healthPool =this.healthPool+ healthAdded;
    }
    public void HealthAmountGoDown(final int healthLost) {
        this.healthAmount -= healthLost;
        if (this.healthAmount < 0)
        {
            this.healthAmount = 0;
        }
    }
    public void HealthAmountGoUp(final int healthAdded) {
    	int sum= healthAmount + healthAdded;
        if (sum > this.healthPool)
        {
            this.healthAmount = this.healthPool;
        }
        else
        {
            this.healthAmount = sum;
        }
    }
    
    

}
