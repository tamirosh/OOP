package UI;

import java.util.List;

import BusinessLayer.Empty;
import BusinessLayer.Enemy;
import BusinessLayer.Player;
import BusinessLayer.Position;
import BusinessLayer.Tile;

public class GameManager {

    //fields:
    Board board;
    Player player;
    List<Enemy> enemies;
    boolean playerIsAlive = true;

    //constructor:
    public GameManager(Player p, int lines, int columns, List<Tile> tilesList, List<Enemy> enemies) {
        this.board = new Board(lines, columns, tilesList);
        this.player = p;
        this.enemies = enemies;
    }

    //methods:
    public String turn(Turns turn) {
        String toReturn = "";
        Tile moveTo = player;
        if(turn.equals(Turns.a))
            moveTo = board.getTileFromBoard(player.getPosition().getx() , player.getPosition().gety() - 1);
        else if(turn.equals(Turns.w))
            moveTo = board.getTileFromBoard(player.getPosition().getx() - 1, player.getPosition().gety());
        else if(turn.equals(Turns.s))
            moveTo = board.getTileFromBoard(player.getPosition().getx() + 1, player.getPosition().gety());
        else if(turn.equals(Turns.d))
            moveTo = board.getTileFromBoard(player.getPosition().getx(), player.getPosition().gety() + 1);
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).GameTick();
        player.GameTick();
        if(!turn.equals(Turns.e) && !turn.equals(Turns.q)) {
            toReturn += player._contactWith(moveTo);
            board.Update2Positions(player.getPosition(), moveTo.getPosition());
        }
        if(turn.equals(Turns.e))
        {
            toReturn += player.attack(enemies);
            board.arrangeBoard();
        }
        if (player.getTypeTile() == 'X') //checks if the player died
        {
            toReturn += "\r\n";
            toReturn += "you lost in the game :(" + "\n";
            toReturn += board.PrintBoard();
            return toReturn;
        }
        for (int i = 0; i < enemies.size(); i++) //checks if some enemies died
        {
            Enemy thisEnemy = enemies.get(i);
            if(thisEnemy.getHealthAmount() == 0)
            {
                toReturn += thisEnemy.getName() + " is dead." + "\n";
                int x = thisEnemy.getPosition().getx();
                int y = thisEnemy.getPosition().gety();
                this.board.setIndex(x, y, new Empty(new Position(x,y)));
                enemies.remove(i);
            }
        }
        for (int i = 0; i < enemies.size(); i++) //the enemies turn
        {
            Position firstPosition = enemies.get(i).getPosition();
            Position positionToContact = enemies.get(i).act(player.getPosition());
            if(positionToContact != null)
            {
                toReturn += enemies.get(i)._contactWith(board.getTileFromBoard(positionToContact.getx(), positionToContact.gety()));
                board.Update2Positions(enemies.get(i).getPosition(), board.getTileFromBoard(positionToContact.getx(), positionToContact.gety()).getPosition());
            }
        }
        toReturn += "\r\n";
        toReturn += "\n";
        toReturn += board.PrintBoard();
        toReturn += "\n";
        toReturn += "\n";
        toReturn += player.getState();
        toReturn += "\n";
        return toReturn;
    }

    public boolean playerIsAlive() {
        return this.playerIsAlive;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String printBoard() {
        return this.board.PrintBoard();
    }

    public int enemiesNum() {
        return enemies.size();
    }
}
