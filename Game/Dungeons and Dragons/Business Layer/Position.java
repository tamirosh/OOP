
package BusinessLayer;

public class Position
{
    //fields:
    private int x;
    private int y;
    
    //constructor:
    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }
    
    //methods:
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
    
    public void setPosition(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
