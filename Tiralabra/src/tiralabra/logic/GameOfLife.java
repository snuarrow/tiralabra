/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import tiralabra.logic.structures.NodeMap;
import java.util.ArrayList;

/**
 *
 * @author hexvaara
 */
public class GameOfLife {
    NodeMap nodemap;
    
    public GameOfLife(int xsize, int ysize, int[][] bytemap)
    {
        this.nodemap = new NodeMap(xsize, ysize, bytemap);
    }
    
    public int[][] iterate()
    {
        NodeMap tempmap = new NodeMap(20, 20, nodemap.getIntMap());
        
        //System.out.println(nodemap.getAllNodes().size());
        System.out.println("iterate");
        
        for (Node n : nodemap.getAllNodes())
        {
            int count = 0;
            for (Node node : nodemap.getNeighbours(n))
            {
                if (node.color == 1) count ++;
            }
            System.out.println(count);
            
            if (n.color == 1)
            {
                if (count < 2) tempmap.changeColor(n, 0);
                if (count > 3) tempmap.changeColor(n, 0);
            }
            if (n.color != 1)
            {
                if (count == 3) tempmap.changeColor(n, 1);
            }
        }
        /*
        int count = 0;
        for (Node node : nodemap.getNeighbours(nodemap.getNode(1, 0)))
        {
            if (node.color == 1) count ++;
        }
        
        System.out.println(count);
        */
        /*
        for (Node n : nodemap.getAllNodes())
        {
            ArrayList<Node> neighboursOfN = nodemap.getNeighbours(n);
            //System.out.println(ne.size());
            int count = 0;
            for (Node nn : neighboursOfN)
            {
                if (nn.color == 1) count += 1;
            }
            
            System.out.println(count);
            
            if (count < 2) tempmap.changeColor(n, 0);
            if (count > 4) tempmap.changeColor(n, 0);
            if (n.color == 0 && count == 3) tempmap.changeColor(n, 1);
        }
        */
                
        nodemap = new NodeMap(20,20, tempmap.getIntMap());
        
        return nodemap.getIntMap();
    }
}
