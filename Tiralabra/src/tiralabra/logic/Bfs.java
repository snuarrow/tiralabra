package tiralabra.logic;

import tiralabra.logic.structures.Node;
import tiralabra.logic.structures.HexMap2;
import tiralabra.logic.structures.NodeMap;
import tiralabra.logic.structures.NodeQueue2;

/**
 * Basic breadth first algorithm
 * 
 * @author hexvaara
 */
public class Bfs {
    
    private final NodeMap nodemap;
    private final NodeQueue2 list;
    private final HexMap2 from;
    private boolean finished = false;
    private final int buffersize = 100;
    
    public Bfs(int[][] bytemap)
    {
        int xsize = bytemap.length;
        int ysize = bytemap[0].length;
        this.list = new NodeQueue2();
        
        this.from = new HexMap2(xsize, ysize);
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        list.add(nodemap.getStart());
        from.put(nodemap.getStart(), null);
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
            if (!list.isEmpty() && !finished)
            {
                Node current = list.poll();
                for (Node n : nodemap.getCrossNeighbours(current))
                {
                    if (n.color == 3)
                    {
                        from.put(n, current);
                        drawRoute(n);
                        break;
                    }
                    if (n.color == 0) 
                    {
                        nodemap.changeColor(n, 2);
                        from.put(n, current);
                        list.add(n);
                    }
                }
            }
        }
        return nodemap.getIntMap();
    }
    
    private void drawRoute(Node current)
    {
        if (current != null)
        {
            Node f = from.get(current);
            nodemap.changeColor(current, 4);
            drawRoute(f);    
        } else finished = true;
    }
    
    public boolean isFinished()
    {
        return finished;
    }
    
}
