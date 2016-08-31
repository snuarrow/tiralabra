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
public class HexSet {
    
    public boolean[][] memory;
    
    public HexSet(int slotsx, int slotsy)
    {
        memory = new boolean[slotsx][slotsy];
        for (boolean[] m : memory) {
            for (boolean n : m) {
                n = false;
            }
        }
    }
    
    public boolean contains(Node node)
    {
        return memory[node.x][node.y];
        
    }
    
    public void add(Node node)
    {
        memory[node.x][node.y] = true;
    }
}
