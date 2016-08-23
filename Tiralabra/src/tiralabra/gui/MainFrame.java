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
import tiralabra.logic.Bfs;
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
    private Bfs bfs;
    private Astar3 astar;
    private MazeGenerator mazegenerator;
    
    private byte[][] bytemap;
    
    public MainFrame(ProgramStarter gamestarter, byte[][] bytemap)
    {
        
        //this.board = b;

        this.bytemap = bytemap;
        
        this.mazegenerator = new MazeGenerator();
        //this.bfs = new Bfs(this.board);
        //bfs.setStart(0, 0);
        
        
        
        this.astar = new Astar3(bytemap);
        
        
        
        
        
        
        
        addMouseListener(this);
        addMouseMotionListener(this);
        t = new Timer(delay, this);
        t.start();
    }

    int delay = 10;
    
    /**
     * renderöi aloitusvalikon näkymän
     * @param g 
     */
    public void start_menu(Graphics2D g)
    {
        byte x = 0;
        byte y = 0;
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                
                
                if (bytemap[j][i] == 1) drawObstacle(g, i, j);
                if (bytemap[j][i] == 2) drawRed(g, i, j);
                if (bytemap[j][i] == 3) drawBlue(g, i, j);
                if (bytemap[j][i] == 4) drawGreen(g, i, j);
                
                
                
                //g.setPaint();
                
            }
           
        }
        
        
    }
    
    private void drawObstacle(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(x*50, y*50, 50, 50);
        
    }
    private void drawRed(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.RED);
        g.fillRect(x*50, y*50, 50, 50);
    }
    private void drawBlue(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.BLUE);
        g.fillRect(x*50, y*50, 50, 50);
    }
    private void drawGreen(Graphics2D g, int x, int y)
    {
        g.setColor(java.awt.Color.GREEN);
        g.fillRect(x*50, y*50, 50, 50);
    }
    // muuttujat show_picture funktiolle
    private int show_picture_state = 0; // 0 = in zoom period, 1 = fully zoomed
    private int upper_left_x;
    private int upper_left_y;
    private int width;
    private int height;
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        start_menu(g2);
                
    }
    

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        iteration();
    }
    
    boolean initastar = false;
    
    private void iteration()
    {
        repaint();
        //bfs.iterate();
        //bytemap = astar.iterate();
        if (!mazegenerator.isFinished()) bytemap = mazegenerator.iterate();
        else if (initastar == false) 
        {
            initastar = true;
            bytemap[1][1] = 4;
            bytemap[18][18] =3;
            astar = new Astar3(bytemap);
            bytemap = astar.iterate();
            delay = 2000;
            return;
        } else if (initastar == true)
        {
            bytemap = astar.iterate();
            delay = 100;
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
