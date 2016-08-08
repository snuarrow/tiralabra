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
public class Comparators {
    public int compareIntegers(int a, int b)
    {
        if (a < b) return a;
        return b;
    }
    public Node compareSquareDistanceOfNodes(Node a, Node b, Node goal)
    {
        double aXdist = Math.abs(a.x-goal.x);
        double aYdist = Math.abs(a.y-goal.y);
        double bXdist = Math.abs(b.x-goal.x);
        double bYdist = Math.abs(b.y-goal.y);
        
        double aDist = Math.sqrt(aXdist*aXdist+aYdist*aYdist);
        double bDist = Math.sqrt(bXdist*bXdist+bYdist*bYdist);
    
        if (aDist < bDist) return a;
        return b;
    }
}
