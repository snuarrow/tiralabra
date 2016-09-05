package tiralabra.logic.structures;

/**
 * Map structure for node double combinations.
 * O(1) timed operations for put, contains and get.
 * uses screen height * screen width * 64B / pixelSize memory.
 * 
 * this is fast.
 * 
 * @author hexvaara
 */
public class HexMap3 {
    private Double[][] memory;
    
    public HexMap3(int xsize, int ysize)
    {
        this.memory = new Double[xsize][ysize];
        
        for (Double[] memory1 : memory) {
            for (Double memory11 : memory1) {
                memory11 = Double.MIN_VALUE;
            }
        }
    }
    
    public void put(Node key, double value)
    {
        memory[key.x][key.y] = value;
    }
    
    public boolean containsKey(Node key)
    {
        return get(key) != Double.MIN_VALUE;
    }
    
    public double get(Node key)
    {
        return memory[key.x][key.y];
    }
}
