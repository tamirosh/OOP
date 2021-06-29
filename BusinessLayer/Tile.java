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

    public String _contactWith(Tile t) {
        if(t.getClass() == (new Empty(null)).getClass())
            return this.contactWith((Empty) t);
        else if(t.getClass() == (new Wall(null)).getClass())
            return this.contactWith((Wall) t);
        else if(t.getClass() == (new Trap('h',null, "name", null, 1,1,1,1,1)).getClass() ||
                t.getClass() == new Monster('h',null, "name", null, 1,1,1,1).getClass())
            return this.contactWith((Enemy) t);
        else
            return this.contactWith((Player) t);
    }

    public void switchPositions(Tile anotherTile) {
        Position appendix = new Position(this.getPosition().getx(), this.getPosition().gety());
        this.position = new Position(anotherTile.getPosition().getx(), anotherTile.getPosition().gety());
        anotherTile.position = appendix;
    }
}
