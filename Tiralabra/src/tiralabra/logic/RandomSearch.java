package tiralabra.logic;

import tiralabra.logic.structures.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import tiralabra.logic.structures.NodeMap;

/**
 * Breadth first search which picks nodes in random order.
 * law of big numbers makes this resemble traditional BFS.
 * not in current demo and thats why does not use own data structures.
 * 
 * @author hexvaara
 */
public class RandomSearch {
    
    private HashMap<Node, Node> from;
    private NodeMap nodemap;
    private ArrayList<Node> visited;
    private boolean finished = false;
    private int buffersize = 100;
    
    public RandomSearch(int[][] bytemap)
    {
        this.from = new HashMap<>();
        this.visited = new ArrayList<>();
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        visited.add(nodemap.getStart());
    }
    
    /**
     * iterates "buffer size" amount of iterations.
     * because algorithm is much faster than graphics pipeline.
     * 
     * @return 
     */
    public int[][] iterate()
    {
        for (int i = 0; i < buffersize; i++) {
            
        int randomIndex = new Random().nextInt(visited.size());
        Node current = visited.get(randomIndex);
        visited.remove(randomIndex);
        
        for (Node n : nodemap.getCrossNeighbours(current))
        {
            if (n.color == 3)
            {
                drawRoute(current);
                finished = true;
                break;
            }
            if (n.color == 0)
            {
                visited.add(n);
                nodemap.changeColor(n, 2);
                from.put(n, current);
            }
        }
        
        }
        return nodemap.getIntMap();
    }
    
    private void drawRoute(Node current)
    {
        if (current != null)
        {
            //System.out.println(current);
            Node f = from.get(current);
            nodemap.changeColor(current, 4);
            drawRoute(f);    
        }
    }
    
    public boolean isFinished()
    {
        return finished;
    }
}
