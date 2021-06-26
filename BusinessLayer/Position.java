package BusinessLayer;

public class Position
{
    private int x;
    private int y;
    
    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getx() {
        return this.x;
    }
    
    public int gety() {
        return this.y;
    }
    
    public void moveUp() {
        y++;
    }
    
    public void moveDown() {
       y--;
    }
    
    public void moveRight() {
        x++;
    }
    
    public void moveLeft() {
        x--;
    }
    
    public void setPosition(int x,int y) 
    {
        this.x = x;
        this.y = y;
    }
    public double getDistance(Position p)
    {
    	return Math.sqrt(Math.pow(p.getx() - this.x, 2.0) + Math.pow(p.gety() - this.y, 2.0));
    }
}