/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import tiralabra.logic.structures.NodeMap;

/**
 *
 * @author hexvaara
 */
public class Bfs {
    
    private NodeMap nodemap;
    private Queue<Node> list;
    private HashMap<Node, Node> from;
    private boolean finished = false;
    
    public Bfs(int[][] bytemap)
    {
        this.list = new LinkedList<>();
        this.from = new HashMap<>();
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        list.add(nodemap.getStart());
        from.put(nodemap.getStart(), null);
    }
    
    public int[][] iterate()
    {
        for (int i = 0; i < 100; i++) {
            
        
        if (!list.isEmpty() && !finished)
        {
            Node current = list.poll();
            //nodemap.changeColor(current, 2);
        
            
             
            
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
