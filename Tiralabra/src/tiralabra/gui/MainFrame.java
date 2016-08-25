/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import tiralabra.ProgramStarter;
import tiralabra.communications.CommunicationBus;
import tiralabra.logic.Astar3;
import tiralabra.logic.GameOfLife;
import tiralabra.logic.MazeGenerator;
import tiralabra.logic.MazeGenerator2;

/**
 * Graphical user interface
 * 
 * @author hexvaara
 */

public class MainFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private final Timer t;
    //private Board board;
    private Astar3 astar;
    private MazeGenerator2 mazegenerator;
    private GameOfLife gol;
    private final int pixelsize;
    private final int slotsX;
    private final int slotsY;
    private CommunicationBus bus;
    
    int delay = 1;
    
    private int[][] bytemap;
    
    public MainFrame(ProgramStarter gamestarter, int pixelsize, int slotsX, int slotsY)
    {
        this.pixelsize = pixelsize;
        this.slotsX = slotsX;
        this.slotsY = slotsY;
        
        bus = new CommunicationBus();
        
        this.mazegenerator = new MazeGenerator2(slotsX,slotsY, bus );
        //this.mazegenerator.start();
        this.bytemap = mazegenerator.getStartFrame();
        //this.gol = new GameOfLife(slotsX,slotsY,bytemap);
        this.astar = new Astar3(bytemap);
        
        addMouseListener(this);
        addMouseMotionListener(this);
        t = new Timer(delay, this);
        t.start();
    }

    
    
    /**
     * renderöi aloitusvalikon näkymän
     * @param g 
     */
    public void in_program(Graphics2D g) throws InterruptedException
    {
        //drawPixel(g,400,400,1);
        
        
        
        drawPixel(g,bus.x(),bus.y(),bus.c());
        /*
        for (int i = 0; i < slotsX; i++) {
            for (int j = 0; j < slotsY; j++) {
                
                
                
                if (bytemap[i][j] == 1) drawObstacle(g, i, j);
                if (bytemap[i][j] == 2) drawRed(g, i, j);
                if (bytemap[i][j] == 3) drawBlue(g, i, j);
                if (bytemap[i][j] == 4) drawGreen(g, i, j);
            }
        }
        */
    }
    
    private void drawPixel(Graphics2D g, int x, int y, int c)
    {
        
        if(c == 0) g.setColor(Color.white);
        if(c == 1) g.setColor(Color.black);
        if(c == 2) g.setColor(Color.red);
        if(c == 3) g.setColor(Color.blue);
        if(c == 4) g.setColor(Color.green);
        
        
        g.fillRect(x, y, 1, 1);
    }
    
    private void drawObstacle(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(x*pixelsize, y*pixelsize, pixelsize, pixelsize);
        
    }
    private void drawRed(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.RED);
        g.fillRect(x*pixelsize, y*pixelsize, pixelsize, pixelsize);
    }
    private void drawBlue(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.BLUE);
        g.fillRect(x*pixelsize, y*pixelsize, pixelsize, pixelsize);
    }
    private void drawGreen(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.GREEN);
        g.fillRect(x*pixelsize, y*pixelsize, pixelsize, pixelsize);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        //super.paintImmediately(500, 500, 200, 100);
        super.paintChildren(g);
        Graphics2D g2 = (Graphics2D) g;
        
        try {
            in_program(g2);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        repaint();
        
        mazegenerator.iterate();
        
    }
    
    public void doWait()
    {
        synchronized(bus)
        {
            try
            {
                bus.wait();
            } catch (InterruptedException e) {}
        }
    }
    
    private void iteration()
    {
        
        
        repaint();
        
        
        
        
        //mazegenerator.iterate();
        
        //doWait();
        
        //bytemap = astar.iterate();
        
        //bytemap = gol.iterate();
        
        /*
        if (!mazegenerator.isFinished()) bytemap = mazegenerator.iterate();
        else if (initastar == false) 
        {
            initastar = true;
            bytemap[1][1] = 4;
            bytemap[slotsX-2][slotsY-2] =3;
            astar = new Astar3(bytemap);
            bytemap = astar.iterate();
            delay = 2000;
            return;
        } else if (initastar == true)
        {
            if (!astar.isFinished())
            {
                bytemap = astar.iterate();
                delay = 10;
            } 
            
        }
        */
        
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        //iteration();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
