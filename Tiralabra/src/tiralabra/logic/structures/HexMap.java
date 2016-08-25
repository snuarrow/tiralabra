/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import tiralabra.logic.Node;

/**
 * Not ready yet
 * 
 * 
 * @author hexvaara
 */
public class HexMap {
    
    Tuple[] memory;
    
    public HexMap()
    {
        
    }
    
    
    public void put(Node key, Object value)
    {
        if (containsKey(key))
        {
            for (int i = 0; i < memory.length; i++) {
                if (memory[i]._1.hashCode() == key.hashCode())
                {
                    memory[i]._2 = value;
                }
            }
            
            
        } else
        {
            if (memory == null || memory.length == 0)
            {
                memory = new Tuple[1];
                memory[0] = new Tuple(key, value);
            }
            else enlarge();
        
            memory[memory.length -1] = new Tuple(key,value);
        }
    }
    
    public boolean containsKey(Node key)
    {
        if (memory == null || memory.length == 0) return false;
        for (Tuple slot : memory) {
            if (slot._1.hashCode() == key.hashCode()) return true;
        }
        return false;
    }
    
    public Object get(Node key)
    {
        if (memory == null || memory.length == 0) return null;
        for (Tuple slot : memory) {
            if (slot._1.hashCode() == key.hashCode()) return slot._2;
        }
        return null;
    }
    
    private void enlarge()
    {
        int size = memory.length;
        
        Tuple[] temp = new Tuple[size];
        for (int i = 0; i < size; i++) {
            temp[i] = memory[i];
        }
        memory = new Tuple[size+1];
        
        for (int i = 0; i < size; i++) {
            memory[i] = temp[i];
        }
    }
}