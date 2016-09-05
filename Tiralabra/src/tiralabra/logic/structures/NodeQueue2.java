package tiralabra.logic.structures;

import java.util.HashMap;

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
    private int startNulls;
    
    public NodeQueue2()
    {
        startNulls = 0;
        addCount = 0;
        nullCount = 0;
        
        memory = new Node[10];
        for (Node m : memory) {
            m = null;
        }
    }
    
    public Node get(int index)
    {
        
        int counter = 0;
        for (int i = startNulls; i < addCount; i++) {
            if (memory[i] != null)
            {
                //System.out.println("i were here");
                if (index == counter) 
                {    
                    return memory[i];
                }
                counter++;
            }
        }
        
        //System.out.println("startnulls:"+startNulls+"nullcount:"+nullCount+"addcount:"+addCount+" index: "+index+" size:"+size());
        //System.out.println("i returned null");
        
        return null;
    }
    
    public Node poll()
    {
        for (int i = startNulls; i < addCount; i++) {
            if (memory[i] != null)
            {
                Node r = memory[i];
                removeIndex(i);
                startNulls++;
                return r;
            }
        }
        return null;
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
        //System.out.println("add called");
        if (addCount == memory.length) reallocMemory();
        memory[addCount] = input;
        addCount += 1;
    }
    
    //needed for astar
    
    public void remove(int index)
    {
        int ii = 0;
        for (int i = startNulls; i < addCount; i++) {
            if (memory[i] != null)
            {
                if (ii == index)
                {
                    removeIndex(i);
                    return;
                }
                ii++;
            }
        }
    }
    
    public boolean remove(Node input)
    {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] != null && memory[i].equals(input)) 
            {
                
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
        if (this.memory == null) return true;
        return size() == 0;
    }
    
    public int length()
    {
        if (this.memory == null) return 0;
        return addCount-nullCount;
    }
    
    public int size()
    {
        return length();
    }
    

    
    private void removeIndex(int index)
    {
        memory[index] = null;
        nullCount += 1;
    }
    
    private void reallocMemory()
    {
        //System.out.println("realloc: addcount:"+addCount+" nullcount:"+nullCount+" memory.length:"+memory.length);
        bestIndex = -1;
        Node[] temp = new Node[(addCount-nullCount)*2];
        int tempindex = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == null) continue;
            
            temp[tempindex++] = memory[i];
            
            addCount = tempindex;
        }
        startNulls = 0;
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
