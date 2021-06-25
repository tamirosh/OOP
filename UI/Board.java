  
package UI;

import java.util.LinkedList;
import java.util.List;

import BusinessLayer.Position;
import BusinessLayer.Tile;

public class Board {
	
	//fields:
	private Tile[][] board;
	private final int lines;
	private final int columns;
	
	//constructor:
	public Board(int lines, int columns, List<Tile> tilesList) {
		if(tilesList.size() != lines * columns)
			throw new IllegalArgumentException("The tiles list need to iclude number of tiles that equals to the number that need to create the board");
		this.lines = lines;
		this.columns = columns;
		this.board = new Tile[lines+1][columns+1];
		for(int line = 1; line <= getLinesNumber(); line++)
		{
			for(int column = 1; column <= getColumnsNumber(); column++) 
			{
				board[line][column] = tilesList.remove(0);
				if (board[line][column] == null)
					throw new IllegalArgumentException("The tiles list cannot include null tiles");
			}
		}
	}
	
	public Tile getTileFromBoard(int line, int column) {
		return board[line][column];
	}
	
	public int getLinesNumber() {
		return this.lines;
	}
	
	public int getColumnsNumber() {
		return this.columns;
	}
	
	private void arrangeBoard() {
		List<Tile> positions = new LinkedList<Tile>();
		for (int line = 1; line <= getLinesNumber(); line++)
		{
			for (int column = 1; column <= getColumnsNumber(); column++)
			{
				Position thisPosition = board[line][column].getPosition();
				if(thisPosition.getx() != line  || thisPosition.gety() != column)
				{
					positions.add(board[thisPosition.getx()][thisPosition.gety()]);
					board[thisPosition.getx()][thisPosition.gety()] = board[line][column];
					board[line][column] = null;
				
				}
			}
		}
		if(!positions.isEmpty()) 
		{
			for (int position = 0; position < positions.size(); position++)
			{
				Position thisPosition = positions.get(0).getPosition();
				if (board[thisPosition.getx()][thisPosition.gety()] == null)
					throw new IllegalArgumentException("two tiles moved to the same position");
				board[thisPosition.getx()][thisPosition.gety()] = positions.remove(0);
			}
		}
		
	}
	
	public String PrintBoard() {
		arrangeBoard();
		String answer = "";
		for (int line = 1; line <= getLinesNumber(); line++)
		{
			for (int column = 1; column <= getColumnsNumber(); column++)
			{
				answer += board[line][column].getTypeTile();
			}
			answer += "/r/n";
		}
		answer += "/r/n";
		answer += "/r/n";
		return answer;
	}
	
	public void setIndex(int x, int y, Tile t) {
		if(x > 0 && x <= getColumnsNumber() && y > 0 && y <= getLinesNumber() && t != null)
			board[x][y] = t;
	}
	
}