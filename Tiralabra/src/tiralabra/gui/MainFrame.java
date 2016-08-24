/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import tiralabra.ProgramStarter;
import tiralabra.logic.Astar3;
import tiralabra.logic.GameOfLife;
import tiralabra.logic.MazeGenerator;

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
    private MazeGenerator mazegenerator;
    private GameOfLife gol;
    private int pixelsize;
    private int slotsX;
    private int slotsY;
    
    int delay = 1;
    
    private byte[][] bytemap;
    
    public MainFrame(ProgramStarter gamestarter, byte[][] bytemap, int pixelsize, int slotsX, int slotsY)
    {
        this.pixelsize = pixelsize;
        this.slotsX = slotsX;
        this.slotsY = slotsY;
        
        
        this.mazegenerator = new MazeGenerator(slotsX,slotsY);
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
    public void in_program(Graphics2D g)
    {
        byte x = 0;
        byte y = 0;
        
        for (int i = 0; i < slotsX; i++) {
            for (int j = 0; j < slotsY; j++) {
                if (bytemap[j][i] == 1) drawObstacle(g, i, j);
                if (bytemap[j][i] == 2) drawRed(g, i, j);
                if (bytemap[j][i] == 3) drawBlue(g, i, j);
                if (bytemap[j][i] == 4) drawGreen(g, i, j);
            }
        }
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
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        in_program(g2);
    }
    

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        iteration();
    }
    
    boolean initastar = false;
    boolean initgol = true;
    
    private void iteration()
    {
        repaint();
        
        //bytemap = astar.iterate();
        
        //bytemap = gol.iterate();
        
        
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
                delay = 100;
            } 
            
        }
        
        
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
