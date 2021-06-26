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
			moveTo = board.getTileFromBoard(player.getPosition().getx() - 1, player.getPosition().gety());
		else if(turn.equals(Turns.w))
			moveTo = board.getTileFromBoard(player.getPosition().getx(), player.getPosition().gety() + 1);
		if(turn.equals(Turns.s))
			moveTo = board.getTileFromBoard(player.getPosition().getx(), player.getPosition().gety() - 1);
		if(turn.equals(Turns.d))
			moveTo = board.getTileFromBoard(player.getPosition().getx() + 1, player.getPosition().gety());
		for (int i = 0; i < enemies.size(); i++)
			enemies.get(i).GameTick();
		if(!turn.equals(Turns.e) && !turn.equals(Turns.s))
			toReturn += player.contactWith(moveTo);
		if (player.getTypeTile() == 'X')
		{
			toReturn += "/r/n";
			toReturn += "you lost in the game :(" + "/r/n";
			toReturn += board.PrintBoard();
			return toReturn;
		}
		for (int i = 0; i < enemies.size(); i++)
		{
			Enemy thisEnemy = enemies.get(i);
			if(thisEnemy.getHealthAmount() == 0)
			{
				toReturn += thisEnemy.getName() + " is dead." + "/r/n";
				int x = thisEnemy.getPosition().getx();
				int y = thisEnemy.getPosition().gety();
				this.board.setIndex(x, y, new Empty(new Position(x,y)));
				enemies.remove(i);
			}
		}
		for (int i = 0; i < enemies.size(); i++)
		{
			Position positionToContact = enemies.get(i).act(player.getPosition());
			if(positionToContact != null)
				toReturn += enemies.get(i).contactWith(board.getTileFromBoard(positionToContact.getx(), positionToContact.gety()));
		}
		toReturn += "/r/n";
		toReturn += "/r/n";
		toReturn += board.PrintBoard();
		toReturn += "/r/n";
		toReturn += "/r/n";
		toReturn += player.getState();
		toReturn += "/r/n";
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
