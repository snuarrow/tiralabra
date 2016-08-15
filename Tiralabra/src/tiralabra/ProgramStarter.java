/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.awt.Color;
import javax.swing.JFrame;
import jdk.nashorn.internal.objects.Global;
import tiralabra.gui.MainFrame;
import tiralabra.logic.Board;

/**
 * For starting and closing GUI.
 * 
 * @author hexvaara
 */
public class ProgramStarter {
    JFrame f;
    //Global global;
    
    public ProgramStarter()
    {
        //this.global = global;
    }
    
    public void startFrame()
    {
        //Board b = new Board((byte)20,(byte)20);
        //b.randomizeObjects(30);
        //b.setStart(0, 0);
        //b.setGoal(19, 19);
        //System.out.println(b.toString());
        
        byte[][] bytemap = 
        {
            {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        
        
        f = new JFrame();
        MainFrame m = new MainFrame(this, bytemap);
        m.setBackground(Color.LIGHT_GRAY);
        f.add(m);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        f.setLocationRelativeTo(null);
    }
    public void closeFrame()
    {
       f.setVisible(false);
       f.dispose();
    }
}
