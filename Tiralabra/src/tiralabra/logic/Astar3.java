/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.HashMap;
import tiralabra.logic.structures.NodeMap;
import tiralabra.logic.structures.HexMap2;
import tiralabra.logic.structures.HexMap3;
import tiralabra.logic.structures.HexSet;
import tiralabra.logic.structures.NodeQueue2;

/**
 * A* shortest path finding algorithm.
 * working final version. self implemented data structures still missing.
 * 
 * @author hexvaara
 */
public class Astar3 {
    
    NodeMap nodemap;
    //HashSet<Node> closedSet;
    //HashSet<Node> openSet;
    HexSet closedSet;
    NodeQueue2 openSet;
    //HashMap<Node, Node> cameFrom;
    HexMap2 cameFrom;
    //HashMap<Node, Double> gScore;
    HexMap3 gScore;
    //HashMap<Node, Double> fScore;
    HexMap3 fScore;
    
    public Astar3(int[][] bytemap)
    {
        int xsize = bytemap.length;
        int ysize = bytemap[0].length;
        
        this.nodemap = new NodeMap(bytemap.length,bytemap[0].length, bytemap);
        //this.closedSet = new HashSet<>();
        //this.openSet = new HashSet<>();
        this.closedSet = new HexSet(bytemap.length,bytemap[0].length);
        this.openSet = new NodeQueue2();
        this.openSet.add(nodemap.getStart());
        
        //this.cameFrom = new HashMap<>();
        this.cameFrom = new HexMap2(xsize, ysize);
        //this.gScore = new HashMap<>();
        this.gScore = new HexMap3(xsize, ysize);
        this.gScore.put(nodemap.getStart(), 0.0);
        //this.fScore = new HashMap<>();
        this.fScore = new HexMap3(xsize, ysize);
        this.fScore.put(nodemap.getStart(), heuristic_cost_estimate(nodemap.getStart(),nodemap.getGoal()));
    }
    
    public boolean isFinished()
    {
        return finished;
    }
    
    public int[][] iterate()
    {
        //for (int i = 0; i < 10; i++) {
        
        for (int i = 0; i < 100; i++) {
            
        
        
        if (!openSet.isEmpty() && !finished)
        {
            Node current = lowestFScoreInOpenSet();
            if (current.isGoal()) reconstruct_path(current);
                    
            openSet.remove(current);
            nodemap.changeColor(current, 2);
            closedSet.add(current);
            
            for (Node neighbor : nodemap.getFreeNeighbours(current)) {
                if (closedSet.contains(neighbor)) continue;
                
                double tentative_gscore = (Double)gScore.get(current) + current.distance(neighbor);
                
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
                else if (tentative_gscore >= (Double)gScore.get(neighbor)) continue;
                
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentative_gscore);
                fScore.put(neighbor, tentative_gscore + heuristic_cost_estimate(neighbor, nodemap.getGoal()));
            }
            
            
            //return nodemap.getIntMap();
        } else break;
        
        }
        return nodemap.getIntMap();
    }
    
    private boolean finished = false;
    
    private void reconstruct_path(Node current)
    {
        while (cameFrom.containsKey(current))
        {
            nodemap.changeColor(current, 4);
            current = (Node) cameFrom.get(current);
        }
        finished = true;
    }
    
    private Node lowestFScoreInOpenSet()
    {
        
        Node best = null;
        double bestFScore = Integer.MAX_VALUE;
        for (Node nodeInOpenSet : openSet.getContent()) {
            if (nodeInOpenSet == null) continue;
            if (fScore.containsKey(nodeInOpenSet))
            {
                double currentFScore = (Double) fScore.get(nodeInOpenSet);
                if (currentFScore < bestFScore)
                {
                    bestFScore = currentFScore;
                    best = nodeInOpenSet;
                }
            }
            
        }
        return best;
        
        
        //return openSet.lowestFscoreInOpenSet(fScore);
    }
    
    private double heuristic_cost_estimate(Node from, Node to)
    {
        return from.distance(to);
    }
}
