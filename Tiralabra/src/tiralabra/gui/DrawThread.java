/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author hexvaara
 */
public class DrawThread extends Thread {
    
    private Graphics g;
    
    public DrawThread(Graphics g)
    {
        this.g = g;
        this.g.setColor(Color.blue);
        
    }
    
    @Override
    public void run()
    {
        
    }
    
    public void draw(int x)
    {
        for (int i = 0; i < 1200; i++) {
            g.drawRect(i, x, 1, 1);
        }
    }
}
