package tiralabra.logic.structures;

/**
 * Set like structure for nodes.
 * O(1) timed operations for contains and add.
 * Uses screen height * screen width * sizeOf(boolean) / pixelSize memory.
 * 
 * this is fast.
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
