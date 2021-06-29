package BusinessLayer;

import java.util.Random;

public class Monster  extends Enemy
{
    private int visionRange;

    public Monster(char tileType,Position position,String name,Health monsterHealth,int attackPoints, int defensePoints, int experienceValue,int visionRange) {
        super(tileType, position, name, monsterHealth, attackPoints, defensePoints, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public Position act(Position playerPosition) {
        if (this.isPlayerInRange(playerPosition))
            return this.movement(playerPosition);
        return this.randomMove();
    }

    public boolean isPlayerInRange(Position pos) {
        boolean ans;
        ans = this.position.getDistance(pos)<=this.visionRange;
        return ans;
    }

    public Position movement(final Position playerPosition) {
        Position move = new Position(this.getPosition().getx(), this.getPosition().gety());
        int dx = this.position.getx() - playerPosition.getx();
        int dy = this.position.gety() - playerPosition.gety();
        if (Math.abs(dx) > Math.abs(dy))
        {
            if (dx > 0)
                move.moveUp();
            else
                move.moveDown();
        }
        else if (dy > 0)
            move.moveLeft();
        else
            move.moveRight();
        return move;
    }

    public Position randomMove() {
        Position move = new Position(this.getPosition().getx(), this.getPosition().gety());
        Random rand = new Random();
        int randomMovement = rand.nextInt(4);
        if(randomMovement == 0)
            move.moveDown();
        else if(randomMovement==1)
            move.moveUp();
        else if(randomMovement==2)
            move.moveLeft();
        else
            move.moveRight();
        return move;
    }

    @Override
    public void GameTick() {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return "" + getTypeTile();
    }

}
