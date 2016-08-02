/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

/**
 *
 * @author hexvaara
 */
public class Node {
    
    public Node previous;
    public byte x;
    public byte y;
    public byte color;
    
    public Node(int x, int y, int color, Node previous)
    {
        this.previous = previous;
        this.x = (byte)x;
        this.y = (byte)y;
        this.color = (byte)color;
    }
}
