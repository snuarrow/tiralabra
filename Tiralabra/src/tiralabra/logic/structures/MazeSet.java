/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import java.util.ArrayList;
import tiralabra.logic.Node;

/**
 *
 * @author hexvaara
 */
public class MazeSet {
    
    public int id;
    public ArrayList<Node> set;
    
    public MazeSet(int id)
    {
        this.id = id;
        this.set = new ArrayList<>();
    }
}
