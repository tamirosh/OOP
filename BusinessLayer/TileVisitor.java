package BusinessLayer;

public interface TileVisitor
{
    String contactWith(Player p);
    
    String contactWith(Enemy en);
    
    String contactWith(Empty em);
    
    String contactWith(Wall w);
}
