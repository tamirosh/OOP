
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
        if(tilesList.size() != lines * columns) {
            System.out.println(tilesList.size());
            System.out.println(lines);
            System.out.println(columns);
            throw new IllegalArgumentException("The tiles list need to iclude number of tiles that equals to the number that need to create the board");
        }
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

    public void arrangeBoard() {
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
                if (!(board[thisPosition.getx()][thisPosition.gety()] == null)) {
                    throw new IllegalArgumentException("two tiles moved to the same position: (" + thisPosition.getx() + "," + thisPosition.gety() + ")");
                }
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
                if(board[line][column] == null)
                    answer += " ";
                else
                    answer += board[line][column].getTypeTile();
            }
            answer += "\n";
        }
        answer += "\r\n";
        answer += "\r\n";
        return answer;
    }

    public void setIndex(int x, int y, Tile t) {
        if(x > 0 && x <= getColumnsNumber() && y > 0 && y <= getLinesNumber() && t != null)
            board[x][y] = t;
    }

    public void Update2Positions(Position p1, Position p2) {
        Position truePosition1 = board[p1.getx()][p1.gety()].getPosition();
        Position truePosition2 = board[p2.getx()][p2.gety()].getPosition();
        if(board[p1.getx()][p1.gety()].getPosition() == p2 && board[p2.getx()][p2.gety()].getPosition() == p1) {
            Tile appendix = getTileFromBoard(p1.getx(),p1.gety());
            board[p1.getx()][p1.gety()] = getTileFromBoard(p2.getx(),p2.gety());
            board[p2.getx()][p2.gety()] = appendix;
        }
    }

}