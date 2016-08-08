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
    
    public Node previous;
    public byte x;
    public byte y;
    public byte color;
    
    public Node(int x, int y, int color, Node previous)
    {
        this.previous = previous;
        this.x = (byte)x;
        this.y = (byte)y;
        this.color = (byte)color;
    }
    public Node(int x, int y)
    {
        this.x = (byte)x;
        this.y = (byte)y;
        this.previous = null;
        this.color = 0;
    }
    
    @Override
    public String toString()
    {
        return "x:"+x+" y:"+y+" color:"+color;
    }
}
