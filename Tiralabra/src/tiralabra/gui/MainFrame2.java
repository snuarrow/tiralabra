/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.JFrame;
import tiralabra.logic.Astar3;
import tiralabra.logic.MazeGenerator2;

/**
 *
 * @author hexvaara
 */
public class MainFrame2 extends JFrame implements Runnable, WindowListener {

    private Thread graphicsThread;
    private boolean running = false;
    private BufferStrategy strategy;
    private int xslots, yslots;
    private int[][] picture;
    private int[][][] partials;
    private int pixelsize;
    private int slots = 4;
    private int slotsize;
    
    private Graphics g;
    
    private MazeGenerator2 mazegenerator2;
    private Astar3 astar3;
    
    public MainFrame2(int pixelsize)
    {
        
        super("Tiralabra MainFrame2");
        
        this.pixelsize = pixelsize;
        
        
        
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        System.out.println(screenSize.height);
        System.out.println(screenSize.width);
        
        this.xslots = ((screenSize.height-20)/pixelsize);//-29;
        this.yslots = (screenSize.width/pixelsize);//-48;
        this.slotsize = yslots/slots;
        
        System.out.println(xslots+" "+yslots);
        
        setSize(screenSize.width,screenSize.height);
        
        this.picture = new int[xslots][yslots];
        this.partials = new int[slots][xslots][yslots/slots];
        
        
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        //setSize(yslots*pixelsize,xslots*pixelsize);
        //setResizable(true);
        
        //setUndecorated(true);
        setVisible(true);
        
        createBufferStrategy(5);
        strategy = getBufferStrategy();
        
        running = true;
        graphicsThread = new Thread(this);
        
        
        g = strategy.getDrawGraphics();
        
        initMazeGen();
        
        graphicsThread.start();
    }
    
    
    private void initMazeGen()
    {
        this.mazegenerator2 = new MazeGenerator2(xslots,yslots);
        
        partials[0] = mazegenerator2.getStartFrame();
        
    }
    
    private void iterateMazeGen()
    {
        
    }
    
    
    
    
    
    private void initAstar()
    {
        this.astar3 = new Astar3(partials[0]);
    }
    
    
    private void drawPixel(int x, int y, int color)
    {
        if (color == 0) g.setColor(Color.white);
        else if (color == 1) g.setColor(Color.black);
        else if (color == 2) g.setColor(Color.red);
        else if (color == 3) g.setColor(Color.blue);
        else if (color == 4) g.setColor(Color.green);
        
        g.fillRect(y*pixelsize, x*pixelsize, pixelsize, pixelsize);
    }
    
    
    public void runIterations()
    {
        
    }
    
    
    int a = 0;
    public void drawBorders()
    {
        
        
    }
    
    
    public void render()
    {
        Random r = new Random();
        for (int i = 0; i < xslots; i++) {
            for (int j = 0; j < slotsize; j++) {
                drawPixel(i,j, picture[i][j]);
                drawPixel(i,j+slotsize, picture[i][j]);
                drawPixel(i,j+2*slotsize, picture[i][j]);
                drawPixel(i,j+3*slotsize, picture[i][j]);
            }
        }
    }
    
    
    private int programstate = 0;
    
    @Override
    public void run() {
        while (running)
        {
            
            
            if (programstate == 0 && !mazegenerator2.isFinished()) this.picture = mazegenerator2.iterate();
            else
            {
                programstate = 1;
                picture[0][slotsize/2] = 4;
                picture[xslots-1][slotsize/2] = 3;
                astar3 = new Astar3(picture);
            }
            
            if (programstate == 1 && !astar3.isFinished()) 
            {
                System.out.println("astar");
                picture = astar3.iterate();
            }
            
            
            
            Graphics g = strategy.getDrawGraphics();
                    
            render();
                    
                    // Dispose the graphics
                    //g.dispose();

                    
                    
                // Display the buffer
            strategy.show();
                
                //mazegenerator2.iterate();
                
            
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
