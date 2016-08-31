/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import java.util.ArrayList;
import java.util.HashMap;
import tiralabra.logic.Node;

/**
 *
 * @author hexvaara
 */
public class NodeQueue2 {
    
    //private int[] nullcount;
    private Node[] memory;
    private int addCount;
    //private int addedCount;
    private int nullCount;
    
    public NodeQueue2()
    {
        
        addCount = 0;
        nullCount = 0;
        
        memory = new Node[10];
        for (Node m : memory) {
            m = null;
        }
                
    }
    
    
    // needed for astar
    public Node[] getContent()
    {
        Node[] temp = new Node[addCount-nullCount];
        int ti = 0;
        
        for (int i = 0; i < addCount; i++) {
            if (memory[i] != null)
            {
                temp[ti] = memory[i];
                ti++;
            }
        }
        
        return temp;
    }
    
    // needed for astar
    public void add(Node input)
    {
        if (addCount == memory.length) reallocMemory();
        memory[addCount] = input;
        addCount += 1;
    }
    
    //needed for astar
    public boolean remove(Node input)
    {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] != null && memory[i].equals(input)) 
            {
                nullCount += 1;
                removeIndex(i);
                return true;
            }
        }
        return false;
    }
    
    //needed for astar
    public boolean contains(Node input)
    {
        if (input == null) return false;
        for (int i = 0; i < addCount; i++) {
            if (memory[i] != null && memory[i].equals(input)) return true;
        }
        
        return false;
    }
    
    //needed for astar
    public boolean isEmpty()
    {
        return addCount-nullCount == 0;
    }
    
    public int length()
    {
        if (this.memory == null) return 0;
        return addCount-nullCount;
    }
    

    
    private void removeIndex(int index)
    {
        memory[index] = null;
    }
    
    private void reallocMemory()
    {
        bestIndex = -1;
        Node[] temp = new Node[(addCount-nullCount)*2];
        int tempindex = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == null) continue;
            
            temp[tempindex++] = memory[i];
            
            addCount = tempindex;
            
            
            
        }
        nullCount = 0;
        memory = temp;
    }
    
    public Node lowestFscoreInOpenSet(HashMap fScore)
    {
        Node best = null;
        double bestFScore = Integer.MAX_VALUE;
        for (Node nodeInOpenSet : memory) {
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
    }
    
    
    @Override
    public String toString()
    {
        if (memory == null || memory[0] == null) return "empty queue";
        
        String returnvalue = "";
        for (Node node : memory) {
            returnvalue += node + "\n";
        }
        return returnvalue;
    }
    
    int bestIndex = -1;
    double bestDistance = Double.MAX_VALUE;
    
    public void addPriority(Node current, Node goal)
    {
        double currentDistance = current.distance(goal);
        if (currentDistance < bestDistance)
        {
            bestDistance = currentDistance;
            bestIndex = addCount;
            add(current);
            //System.out.println("added");
        }
    }
    
    public Node popClosest(Node goal)
    {
        if (bestIndex != -1)
        {
            //System.out.println("popif");
            Node r = memory[bestIndex];
            memory[bestIndex] = null;
            bestIndex = -1;
            return r;
        }
        else
        {
            //System.out.println("popelse");
            double bestD = Double.MAX_VALUE;
            int bestI = 0;
            
            for (int i = 0; i < addCount; i++) {
                if (memory[i] != null && memory[i].distance(goal) < bestD)
                {
                    bestI = i;
                    bestD = memory[i].distance(goal);
                }
            }
            
            Node r = memory[bestI];
            memory[bestI] = null;
            return r;
        }
    }
}
