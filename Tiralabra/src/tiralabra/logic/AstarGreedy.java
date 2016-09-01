/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import tiralabra.logic.structures.HexMap2;
import tiralabra.logic.structures.NodeMap;
import tiralabra.logic.structures.NodeQueue2;

/**
 *
 * @author hexvaara
 */
public class AstarGreedy {
    
    private NodeMap nodemap;
    private NodeQueue2 queue;
    private HexMap2 from;
    private boolean finished = false;
    
    public AstarGreedy(int[][] bytemap)
    {
        this.from = new HexMap2(bytemap.length,bytemap[0].length);
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        this.queue = new NodeQueue2();
        //queue.addPriority(nodemap.getStart(), nodemap.getGoal());
        queue.add(nodemap.getStart());
        from.put(nodemap.getStart(), null);
    }
    
    public int[][] iteration()
    {
        for (int i = 0; i < 100; i++) {
            
        
        
        Node current = queue.popClosest(nodemap.getGoal());
        //System.out.println(current);
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
                    //queue.addPriority(n, nodemap.getGoal());
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
            //System.out.println(current);
            Node f = from.get(current);
            nodemap.changeColor(current, 4);
            drawRoute(f);    
        }
    }
    
}
