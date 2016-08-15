package tiralabra.logic;

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
    public byte x;
    public byte y;
    public byte color;
    
    public Node(int x, int y, int color, Node previous, int weight)
    {
        this.totalWeight = weight;
        this.previous = previous;
        this.x = (byte)x;
        this.y = (byte)y;
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
    
    
    
    
//    public Node compareSquareDistanceOfNodes(Node a, Node b, Node goal)
//    {
//        double aXdist = Math.abs(a.x-goal.x);
//        double aYdist = Math.abs(a.y-goal.y);
//        double bXdist = Math.abs(b.x-goal.x);
//        double bYdist = Math.abs(b.y-goal.y);
//        
//        double aDist = Math.sqrt(aXdist*aXdist+aYdist*aYdist);
//        double bDist = Math.sqrt(bXdist*bXdist+bYdist*bYdist);
//    
//        if (aDist < bDist) return a;
//        return b;
//    }
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
