package tiralabra.logic.structures;

/**
 * Node for 2 dimensional map.
 * colors as numeral values:
 * 0 white
 * 1 barrier
 * 2 visited
 * 3 goal
 * 4 start
 * 
 * @author hexvaara
 */
public class Node {
    
    public int totalWeight;
    public Node previous;
    public int x;
    public int y;
    public byte color;
    
    public Node(int x, int y, int color, Node previous, int weight)
    {
        this.totalWeight = weight;
        this.previous = previous;
        this.x = x;
        this.y = y;
        this.color = (byte)color;
    }
    
    public boolean isGoal()
    {
        return color == 3;
    }
    
    public Node(int x, int y)
    {
        this.x = (byte)x;
        this.y = (byte)y;
        this.previous = null;
        this.color = 0;
    }
    
    public Node(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = (byte) color;
    }
    
    public boolean equals(Node compare)
    {
        return this.x == compare.x && this.y == compare.y;
    }
    
    public double distance(Node venue)
    {
        double xDist = Math.abs(this.x-venue.x);
        double yDist = Math.abs(this.y-venue.y);
        return Math.sqrt((xDist*xDist) + (yDist*yDist));
    }
    
    @Override
    public String toString()
    {
        return "x:"+x+" y:"+y+" color:"+color;
    }
}
