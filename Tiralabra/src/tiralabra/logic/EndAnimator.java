/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.ArrayList;
import java.util.Random;
import tiralabra.logic.structures.NodeMap;

/**
 *
 * @author hexvaara
 */
public class EndAnimator {
    
    private NodeMap nodemap;
    private boolean finished;
    private ArrayList<Node> unprocessed;
    
    public EndAnimator(int[][] map)
    {
        this.unprocessed = new ArrayList<>();
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
    
    public int[][] iterate()
    {
        Random r = new Random();
        for (int i = 0; i < 5000; i++) {
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
