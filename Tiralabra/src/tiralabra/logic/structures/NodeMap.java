/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import tiralabra.logic.Node;

/**
 *
 * @author hexvaara
 */
public class NodeMap {
    
    Object[] memory;
    
    public NodeMap(Node initKeyClass, Node initValueClass)
    {
        
    }
    
    public NodeMap(Node initKeyClass, Double initValueClass)
    {
         
    }
    
    public void put(Node key, Node value)
    {
        if (memory.length == 0)
        {
            memory = new Object[1];
            memory[0] = new Node[2];
            
        }
        else enlarge();
        
        
        
        
    }
    
    private void enlarge()
    {
        int size = memory.length;
        
        Object[] temp = new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = memory[i];
        }
        memory = new Node[size+1];
        
        for (int i = 0; i < size; i++) {
            memory[i] = temp[i];
        }
    }
}
