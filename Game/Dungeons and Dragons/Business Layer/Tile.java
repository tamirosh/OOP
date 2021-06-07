
public abstract class Tile {
	
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
}
