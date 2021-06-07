
public abstract class Tile {
	protected char typeTile;
	protected Position position;
	
	public Tile(char typeTile, Position position) {
		this.typeTile = typeTile;
		this.position = position;
	}
	
	public char getTypeTile() {
		return typeTile;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public abstract String toString();
}
