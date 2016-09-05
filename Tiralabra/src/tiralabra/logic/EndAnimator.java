package tiralabra.logic;

import tiralabra.logic.structures.Node;
import java.util.ArrayList;
import java.util.Random;
import tiralabra.logic.structures.NodeMap;
import tiralabra.logic.structures.NodeQueue2;

/**
 * Only for decoration purposes for smooth impression.
 * apparently does not use own list structure, my list is too slow.
 * 
 * @author hexvaara
 */
public class EndAnimator {
    
    private NodeMap nodemap;
    private boolean finished;
    private ArrayList<Node> unprocessed;
    //private NodeQueue2 unprocessed;
    private final int buffersize = 5000;
    
    public EndAnimator(int[][] map)
    {
        this.unprocessed = new ArrayList<>();
        //unprocessed = new NodeQueue2();
        finished = false;
        nodemap = new NodeMap(map.length, map[0].length, map);
        
        for (Node n : nodemap.getAllNodes())
        {
            unprocessed.add(n);
        }
    }
    
    public boolean isFinished()
    {
        return finished;
    }
    
    /**
     * iterates "buffer size" amount of iterations.
     * because algorithm is much faster than graphics pipeline.
     * 
     * @return 
     */
    public int[][] iterate()
    {
        Random r = new Random();
        for (int i = 0; i < buffersize; i++) {
            if (unprocessed.isEmpty()) 
            {
                finished = true;
                break;
            }
            int randomIndex = r.nextInt(unprocessed.size());
            nodemap.changeColor(unprocessed.get(randomIndex), 1);
            unprocessed.remove(randomIndex);
        }
        return nodemap.getIntMap();
    }
}
