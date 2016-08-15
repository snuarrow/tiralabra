/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Maze Generator.
 * not fully implemented yet
 * 
 * @author hexvaara
 */
public class MazeGenerator {
    
    private byte[][] amaze;
    private HashSet<Node> walls; 
    private HashSet<Node> maze;
    private NodeMap nodemap;
    
    public MazeGenerator()
    {
        walls = new HashSet<>();
        amaze = new byte[20][20];
        for (byte[] amaze1 : amaze) {
            for (byte b : amaze1) {
                b = 1;
            }
        }
    }
    
    public byte[][] iterate()
    {
        
        
        
        return amaze;
    }
}
