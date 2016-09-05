    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

/**
 *
 * @author hexvaara
 */
public class NodeQueue {
    
    private Node[] memory;
    
    public NodeQueue()
    {
        
    }
    
    // needed for astar
    public Node[] getContent()
    {
        return memory;
    }
    
    
    //needed for astar
    public boolean remove(Node input)
    {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i].equals(input)) 
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
        if (memory.length == 0) return false;
        for (Node slot : memory) {
            if (slot.equals(input)) return true;
        }
        return false;
    }
    
    //needed for astar
    public boolean isEmpty()
    {
        return this.length() == 0;
    }
    
    private void enlarge()
    {
        int size = memory.length;
        
        Node[] temp = new Node[size];
        for (int i = 0; i < size; i++) {
            temp[i] = memory[i];
        }
        memory = new Node[size+1];
        
        for (int i = 0; i < size; i++) {
            memory[i] = temp[i];
        }
    }
    private void ensmall()
    {
        Node[] temp = new Node[memory.length-1];
        for (int i = 0; i < memory.length-1; i++) {
            temp[i] = memory[i];
        }
        memory = new Node[memory.length-1];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = temp[i];
        }
    }
    
    
    public int length()
    {
        if (this.memory == null) return 0;
        return memory.length;
    }
    
    // needed for astar
    public void add(Node input)
    {
        if (this.memory == null) 
        {
            this.memory = new Node[1];
            this.memory[0] = input;
        }
        else 
        {
            enlarge();
            memory[memory.length-1] = input;
        }
    }

    public Node popClosest(Node goal)
    {
        if (memory.length == 0) return null;
        
        double smallestDistance = Double.MAX_VALUE;
        int indexOfBest = 0;
        Node best = null;
        
        for (int i = 0; i < memory.length; i++) {
            if (memory[i].distance(goal) < smallestDistance)
            {
                smallestDistance = memory[i].distance(goal);
                best = memory[i];
                indexOfBest = i;
            }
        }
        removeIndex(indexOfBest);
        //ensmall();
        return best;
    }
    public Node popFirst()
    {
        if (memory == null || memory.length == 0) return null;
        Node temp = memory[0];
        removeIndex(0);
        //ensmall();
        return temp;
    }
    
    private void removeIndex(int index)
    {
        for (int i = index; i < memory.length-1; i++) {
            memory[i] = memory[i+1];
        }
        ensmall();
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
}
