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
public class HexMap2 {
    
    private Node[][] memory;
    
    public HexMap2(int slotsx, int slotsy)
    {
        this.memory = new Node[slotsx][slotsy];
        
        for (Node[] m0 : memory) {
            for (Node m1 : m0) {
                m1 = null;
            }
        }
    }
    
    public void put(Node key, Node value)
    {
        memory[key.x][key.y] = value;
    }
    
    public boolean containsKey(Node key)
    {
        return get(key) != null;
    }
    
    public Node get(Node key)
    {
        return memory[key.x][key.y];
    }
}
