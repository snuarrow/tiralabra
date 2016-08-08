package tiralabra.logic;

/**
 * Comparators for algorithms.
 * 
 * 
 * 
 * @author hexvaara
 */
public class Comparators {
    public int compareIntegers(int a, int b)
    {
        if (a < b) return a;
        return b;
    }
    
    /**
     * for astar algorithm
     * 
     * @param current
     * @param other
     * @param goal
     * @return best
     */
    public Node compareSquareDistanceOfNodes(Node a, Node b, Node goal)
    {
        double aXdist = Math.abs(a.x-goal.x);
        double aYdist = Math.abs(a.y-goal.y);
        double bXdist = Math.abs(b.x-goal.x);
        double bYdist = Math.abs(b.y-goal.y);
        
        double aDist = Math.sqrt(aXdist*aXdist+aYdist*aYdist);
        double bDist = Math.sqrt(bXdist*bXdist+bYdist*bYdist);
    
        if (aDist < bDist) return a;
        return b;
    }
}
