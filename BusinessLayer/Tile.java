package BusinessLayer;

public abstract class Tile implements TileVisitor, TileVisited {
	
	//fields:
	protected char typeTile;
	protected Position position;
	
	//constructor:
	public Tile(char typeTile, Position position) {
		this.typeTile = typeTile;
		this.position = position; 
	}
	
	//methods:
	public char getTypeTile() {
		return typeTile;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public abstract String toString();
	
	public String contactWith(Wall w) {
    	return "";
    }
	
	public String contactWith(Empty e) {
    	this.switchPositions(e);
    	return "";
    }
	
	public String contactWith(Tile t) {
		return "";
	}
	
	public void switchPositions(Tile anotherTile) {
		Position appendix = new Position(this.getPosition().getx(), this.getPosition().gety());
    	this.position = new Position(anotherTile.getPosition().getx(), anotherTile.getPosition().gety());
    	anotherTile.position = appendix;
	}
}
