/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.lang.reflect.InvocationTargetException;
import tiralabra.logic.Astar3;
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
        
        //ProgramStarter ps = new ProgramStarter();
        //ps.startFrame();
    
        new BufferedStrategyTest();
       
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
