/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.lang.reflect.InvocationTargetException;
import tiralabra.gui.MainFrame2;

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
        
        //ProgramStarter ps = new ProgramStarter();
        //ps.startFrame();
    
        /*
        int[] testi = new int[30139904];
        int index = 0;
        
        int highestHashCode = Integer.MIN_VALUE;
        int lowestHashCode = Integer.MAX_VALUE;
        
        HashSet<Integer> codes = new HashSet<>();
        HashSet<Node> nodes = new HashSet<>();
        
        for (int i = 0; i < 3000; i++) {
            for (int j = 0; j < 1500; j++) {
                for (int k = 0; k < 5; k++) {
                    Node n = new Node(i,j,k);
                    //System.out.println(n.code());
                    //if (codes.contains(n.code())) System.out.println("FAIL");
                    //else codes.add(n.code());
                    
                    if (nodes.contains(n)) System.out.println("FAIL");
                    else nodes.add(n);
                    
                    if (n.code() > highestHashCode) highestHashCode = n.code();
                    if (n.code() < lowestHashCode) lowestHashCode = n.code();
                }
            }
            System.out.println(i);
        }
        
        System.out.println(highestHashCode);
        System.out.println(lowestHashCode);
        */
        
        /*
        ArrayList<Integer> asd = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            asd.add(i);
        }
        
        
        asd.stream().filter(i -> i > 2).forEach(i -> System.out.println(i));
        */
        
        new BufferedStrategyTest();
        //new MainFrame2(6);
        
    }
    
    public static void printByteMap(byte[][] bytemap)
    {
        String asd = "";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                asd += bytemap[i][j];
            }
            asd += "\n";
        }
        System.out.println(asd);
        
    }
    
    
    
}
