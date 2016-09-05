package tiralabra.logic;

import tiralabra.logic.structures.Node;
import tiralabra.logic.structures.HexMap2;
import tiralabra.logic.structures.NodeMap;
import tiralabra.logic.structures.NodeQueue2;

/**
 * Greedy A* algorithm.
 * heuristics by shortest estimated Euclidean distance to goal node.
 * !!Not guaranteed to find shortest route, although this runs really quick.
 * 
 * @author hexvaara
 */
public class AstarGreedy {
    
    private NodeMap nodemap;
    private NodeQueue2 queue;
    private HexMap2 from;
    private boolean finished = false;
    private final int buffersize = 100;
    
    public AstarGreedy(int[][] bytemap)
    {
        this.from = new HexMap2(bytemap.length,bytemap[0].length);
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        this.queue = new NodeQueue2();
        queue.add(nodemap.getStart());
        from.put(nodemap.getStart(), null);
    }
    
    /**
     * iterates "buffer size" amount of iterations.
     * because algorithm is much faster than graphics pipeline.
     * 
     * @return 
     */
    public int[][] iteration()
    {
        for (int i = 0; i < buffersize; i++) {
            Node current = queue.popClosest(nodemap.getGoal());
            {
                for (Node n : nodemap.getCrossNeighbours(current))
                {
                    if (n.color == 3)
                    {
                        System.out.println("finished");
                        finished = true;
                        drawRoute(current);
                        return nodemap.getIntMap();
                    }
                    if (n.color == 0)
                    {
                        from.put(n, current);
                        queue.add(n);
                        nodemap.changeColor(n, 2);
                    }
                }
            }
        }
        return nodemap.getIntMap();
    }
    
    public boolean isFinished()
    {
        return finished;
    }
    
    private void drawRoute(Node current)
    {
        if (current != null)
        {
            Node f = from.get(current);
            nodemap.changeColor(current, 4);
            drawRoute(f);    
        }
    }
}
