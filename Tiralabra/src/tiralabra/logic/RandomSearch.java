/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import tiralabra.logic.structures.NodeMap;

/**
 *
 * @author hexvaara
 */
public class RandomSearch {
    
    private HashMap<Node, Node> from;
    private NodeMap nodemap;
    private ArrayList<Node> visited;
    private boolean finished = false;
    
    public RandomSearch(int[][] bytemap)
    {
        this.from = new HashMap<>();
        this.visited = new ArrayList<>();
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        visited.add(nodemap.getStart());
    }
    
    public int[][] iterate()
    {
        for (int i = 0; i < 100; i++) {
            
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
