/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A* shortest path finding algorithm.
 * working final version. self implemented data structures still missing.
 * 
 * @author hexvaara
 */
public class Astar3 {
    
    NodeMap nodemap;
    ArrayList<Node> closedSet;
    ArrayList<Node> openSet;
    HashMap<Node, Node> cameFrom;
    HashMap<Node, Double> gScore;
    HashMap<Node, Double> fScore;
    
    public Astar3(byte[][] bytemap)
    {
        this.nodemap = new NodeMap(20,20, bytemap);
        this.closedSet = new ArrayList<>();
        this.openSet = new ArrayList<>();
        this.openSet.add(nodemap.getStart());
        this.cameFrom = new HashMap<>();
        this.gScore = new HashMap<>();
        this.gScore.put(nodemap.getStart(), 0.0);
        this.fScore = new HashMap<>();
        this.fScore.put(nodemap.getStart(), heuristic_cost_estimate(nodemap.getStart(),nodemap.getGoal()));
    }
    
    public byte[][] iterate()
    {
        if (!openSet.isEmpty() && !finished)
        {
            Node current = lowestFScoreInOpenSet();
            if (current.isGoal()) reconstruct_path(current);
                    
            openSet.remove(current);
            nodemap.changeColor(current, 2);
            closedSet.add(current);
            
            for (Node neighbor : nodemap.getFreeNeighbours(current)) {
                if (closedSet.contains(neighbor)) continue;
                
                double tentative_gscore = gScore.get(current) + current.distance(neighbor);
                
                if (!openSet.contains(neighbor)) openSet.add(neighbor);
                else if (tentative_gscore >= gScore.get(neighbor)) continue;
                
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentative_gscore);
                fScore.put(neighbor, tentative_gscore + heuristic_cost_estimate(neighbor, nodemap.getGoal()));
            }
            
            
            return nodemap.getByteMap();
        }
        
        return nodemap.getByteMap();
    }
    
    private boolean finished = false;
    
    private void reconstruct_path(Node current)
    {
        while (cameFrom.containsKey(current))
        {
            nodemap.changeColor(current, 4);
            current = cameFrom.get(current);
        }
        finished = true;
    }
    
    private Node lowestFScoreInOpenSet()
    {
        Node best = null;
        double bestFScore = Integer.MAX_VALUE;
        for (Node nodeInOpenSet : openSet) {
            if (fScore.containsKey(nodeInOpenSet))
            {
                double currentFScore = fScore.get(nodeInOpenSet);
                if (currentFScore < bestFScore)
                {
                    bestFScore = currentFScore;
                    best = nodeInOpenSet;
                }
            }
        }
        return best;
    }
    
    private double heuristic_cost_estimate(Node from, Node to)
    {
        return from.distance(to);
    }
}
