package tiralabra.logic;

import tiralabra.logic.structures.Node;
import java.util.Stack;
import tiralabra.logic.structures.HexMap2;
import tiralabra.logic.structures.NodeMap;

/**
 * Basic Depth first algorithm.
 * not really good in cyclic graph as in this demo.
 * @author hexvaara
 */
public class Dfs {
    
    private NodeMap nodemap;
    private Stack<Node> list;
    private HexMap2 from;
    private boolean finished = false;
    private int buffersize = 100;
    
    public Dfs(int[][] bytemap)
    {
        this.list = new Stack<>();
        this.from = new HexMap2(bytemap.length, bytemap[0].length);
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
            Node current = list.pop();
            for (Node n : nodemap.getCrossNeighbours(current))
                {
                    if (n.color == 3)
                    {
                        from.put(n, current);
                        finished = true;
                        drawRoute(n);
                        break;
                    }
                    if (n.color == 0) 
                    {
                        nodemap.changeColor(n, 2);
                        from.put(n, current);
                        list.push(n);
                        //list.add(n);
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
        }
    }
    
    public boolean isFinished()
    {
        return finished;
    }
}
