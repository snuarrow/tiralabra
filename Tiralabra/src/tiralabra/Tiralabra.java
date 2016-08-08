/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import tiralabra.logic.Comparators;
import tiralabra.logic.HexPriorityQueue;
import tiralabra.logic.Node;

/**
 * Main class
 * 
 * @author hexvaara
 */
public class Tiralabra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // TODO code application logic here
        
        ProgramStarter ps = new ProgramStarter();
        ps.startFrame();
    
        //Class[] parameterTypes = new Class[3];
        //parameterTypes[0] = Node.class;
        //parameterTypes[1] = Node.class;
        //parameterTypes[2] = Node.class;
        //HexPriorityQueue h = new HexPriorityQueue("", Tiralabra.class.getMethod("asd", parameterTypes));
        
        //Method method1 = Comparators.class.getMethod("compareSquareDistanceOfNodes", parameterTypes);
        
        //Tiralabra tr = new Tiralabra();
        
        //HexPriorityQueue h = new HexPriorityQueue(int.class, new Comparators(), method1);
        //HexPriorityQueue hh = new HexPriorityQueue(Node.class, new Node(5,5));
        //hh.add(new Node(0,0));
        //hh.add(new Node(4,4));
        //hh.add(new Node(2,2));
        //hh.add(new Node(3,3));
        //hh.add(new Node(1,1));
        
        //System.out.println(hh.pop());
    }
    
    
    
}
