package BusinessLayer;

import java.util.LinkedList;
import java.util.List;

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
		public void GameTick() {
			this.setCurrentMana(Math.min(this.getManaPool(), this.getCurrentMana() + this.getPlayerLevel()));
		}

		@Override
		public String attack(List<Enemy> enemiesAttacked) {
			if(this.getCurrentMana() < this.getManaCost())
				return "there are no enough mana points to use the special ability" + "/r/n";
			this.decreaseManaPoints(this.manaCost);
			this.hitsInAttack = 0;
			String toReturn = "";
			while (this.getHitsInAttack() < this.getHitsCount())
			{
				Enemy unitAttacked = findEnemy(enemiesAttacked);
				if (unitAttacked == null)
				{
					toReturn += "there's no enemies to attack" + "/r/n";
					return toReturn;
				}
				int enemyDefence = unitAttacked.acceptContact(this);
				int attackPower = this.getSpellPower() - enemyDefence;
				if (attackPower > 0)
					unitAttacked.HealthAmountGoDown(attackPower);
				this.addOneToHitsInAttack();
				toReturn += "" + this.getName() + " hited " + unitAttacked.getName() + " with his special ability: " + this.getSpecialAbilityName() + " on " + unitAttacked.getName() + " by " + this.getSpellPower() + " points." + "\r\n";
				toReturn += unitAttacked.getName() + " defended itself by " + enemyDefence + " points. Now its state is:" + "/r/n";
				toReturn += unitAttacked.getState();
			}
			return toReturn;
		}

		private void addManaPool(int manaPointsToAdd) {
			this.manaPool += manaPointsToAdd;
		}

		private void decreaseManaPoints(int manaPointsToDecrease) {
			this.currentMana -= manaPointsToDecrease;
		}

		public int getManaPool() {
			return this.manaPool;
		}
		
		public int getManaCost() {
			return this.manaCost;
		}

		public int getCurrentMana() {
			return this.currentMana;
		}

		protected void setCurrentMana(int manaPointsToSet) {
			this.currentMana = manaPointsToSet;
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
		
		private Enemy findEnemy(List<Enemy> enemiesAttacked) {
			if(enemiesAttacked.isEmpty() || enemiesAttacked == null)
				return null;
			LinkedList<Enemy> availableEnemies = new LinkedList<Enemy>();
			for(int i = 0; i < enemiesAttacked.size(); i++)
			{
				if(this.getPosition().getDistance(enemiesAttacked.get(i).getPosition()) < getAbilityRange() && enemiesAttacked.get(i).getHealthAmount() != 0)
					availableEnemies.add(enemiesAttacked.get(i));
			}
			if(availableEnemies.isEmpty())
				return null;
			return enemiesAttacked.get((int)(Math.random() * (availableEnemies.size() - 1)));
		}

}