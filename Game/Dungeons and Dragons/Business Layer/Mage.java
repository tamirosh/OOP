package BusinessLayer;

public class Mage extends Player{
		
		//fields:
		private int manaPool;           //holds the maximal value of mana
		private int currentMana;        //current amount of mana
		private final int manaCost;     //ability cost
		private int spellPower;         //ability scale factor
		private int hitsCount;          //maximal number of times a single cast of the ability can hit
		private final int abilityRange; //ability range
		private int hitsInAttack = 0;   //counts how many hits have done in current attack
		
		//constructor:
		public Mage(Position position, String name, Health health, int attackPoints, int defencePoints, int manaPool, int manaCost, int spellPower, int hitsCount, int abilityRange) {
			super(position, name, health, attackPoints, defencePoints, "Blizzard");
			this.manaPool = manaPool;
			this.currentMana = manaPool / 4;
			this.manaCost = manaCost;
			this.spellPower = spellPower;
			this.hitsCount = hitsCount;
			this.abilityRange = abilityRange;
		}
		
		//methods:
		@Override
		protected void playerLevelUp() {
			this.playerLevel += 1;
			this.experience -= this.getMaxExperience();
			this.updateMaxExperience();
			this.addManaPool(this.getPlayerLevel() * 25);
			this.setCurrentMana(Math.min((this.getCurrentMana() + (this.getManaPool() / 4)), this.getManaPool()));
			this.setSpellPower(this.getSpellPower() + 10 * this.getPlayerLevel());
		}
		
		@Override
		public void gameTick() {
			this.setCurrentMana(Math.min(this.getManaPool(), this.getCurrentMana() + this.getPlayerLevel()));
		}
		
		public void startAttack() {
			this.decreaseManaPoints(this.manaCost);
			this.hitsInAttack = 0;
			
		}
		
		@Override
		public String attack(Unit unitAttacked) {
			unitAttacked.HealthAmountGoDown(this.getSpellPower());
			this.addOneToHitsInAttack();
			String toReturn = "" + this.getName() + " hited " + unitAttacked.getName() + "\r\n";;
			toReturn += unitAttacked.attack(this);
			return toReturn;
		}

		@Override
		public String toString() {
			return getName();
		}
		
		private void addManaPool(int manaPointsToAdd) {
			this.manaPool += manaPointsToAdd;
		}
		
		/*private void addManaPoints(int manaPointsToAdd) {
			this.currentMana += manaPointsToAdd;
		}*/
		
		private void decreaseManaPoints(int manaPointsToDecrease) {
			this.currentMana -= manaPointsToDecrease;
		}
		
		public int getManaPool() {
			return this.manaPool;
		}
		
		public int getCurrentMana() {
			return this.currentMana;
		}
		
		protected void setCurrentMana(int manaPointsToSet) {
			this.currentMana -= manaPointsToSet;
		}
		
		public int getSpellPower() {
			return this.spellPower;
		}
		
		protected void setSpellPower(int newSpellPower) {
			this.spellPower = newSpellPower;
		}
		
		public int getHitsCount() {
			return this.hitsCount;
		}
		
		public int getAbilityRange() {
			return this.abilityRange;
		}
		
		private void addOneToHitsInAttack() {
			this.hitsInAttack++;
		}
		
		public int getHitsInAttack() {
			return this.hitsInAttack;
		}
			
}
