package BusinessLayer;

import java.util.List;

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
		this.board = new Tile[lines][columns];
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
	
}
