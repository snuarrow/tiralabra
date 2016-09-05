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
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import tiralabra.communications.CommunicationBus;
import tiralabra.logic.Astar3;
import tiralabra.logic.AstarGreedy;
import tiralabra.logic.Bfs;
import tiralabra.logic.Dfs;
import tiralabra.logic.EndAnimator;
import tiralabra.logic.MazeGenerator2;
import tiralabra.logic.RandomSearch;

/**
 * Graphical User Interface.
 * receives integer maps from program logic and renders them to pixel maps
 * 
 * 
 * @author hexvaara
 */
public class MainFrame3 extends JFrame implements Runnable, WindowListener
{
    private Thread graphicsThread;
    private boolean running = false;
    private BufferStrategy strategy;
    private MazeGenerator2 mazegenerator;
    private Astar3 astar;
    private AstarGreedy astargreedy;
    private Bfs bfs;
    private Dfs dfs;
    private RandomSearch rs;
    private EndAnimator endAnimator;
    
    private CommunicationBus bus;
    private int[][] map;
    private int[][] competitorMap;
    private int pixelsize = 4;
    private int slotsx;
    private int slotsy;
    private int slotleveys;
    
    /**
     * Constructor,
     * 
     * gets screen size from system resolution, runs in own thread
     * 
     */
    public MainFrame3() 
    {
        super("Tiralabra Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.slotsx = ((screenSize.height-20)/pixelsize);//-29;
        this.slotsy = (screenSize.width/pixelsize);//-48;
        this.slotleveys = slotsy/4;
        
        setSize(screenSize.width,screenSize.height);
        setResizable(true);
        setVisible(true);

        bus = new CommunicationBus();
        mazegenerator = new MazeGenerator2(slotleveys, slotsx);
        
        map = new int[slotsy][slotsx];
        
        for (int[] map1 : map) {
            for (int n : map1) {
                n = 0;
            }
        }
        
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        running = true;
        graphicsThread = new Thread(this);
        graphicsThread.start();
    }

    public void addNotify() {
        super.addNotify();
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }
    
    private boolean generateMaze()
    {
        if (mazegenerator.isFinished()) return false;
        
        int[][] temp = mazegenerator.iterate();
        
        competitorMap = temp;
        
        for (int x = 0; x < slotsx-2; x++) {
            for (int y = 0; y < slotleveys; y++) {
                map[y][x] = temp[y][x];
                map[y+slotleveys][x] = temp[y][x];
                map[y+2*slotleveys][x] = temp[y][x];
                map[y+3*slotleveys][x] = temp[y][x];
            }
        }
        return true;
    }
    
    private void placeIteration(int[][] algo, int slot)
    {
        for (int i = 0; i < slotsx; i++) {
            for (int j = 0; j < slotleveys; j++) {
                map[j+slot*slotleveys][i] = algo[j][i];
            }
        }
    }

    private boolean start = true;
    
    private void render(Graphics g)
    {
        for (int i = 0; i < slotsx; i++) {
            for (int j = 0; j < slotsy; j++) {
                 drawPixel(i,j,map[j][i], g);
            }
        }
    }
    
    private void drawPixel(int x, int y, int color, Graphics g)
    {
        if (color == 0) g.setColor(new Color(200,200,255));
        else if (color == 1) g.setColor(Color.BLACK);
        else if (color == 2) g.setColor(new Color(255,0,0));
        else if (color == 3) g.setColor(Color.blue);
        else if (color == 4) g.setColor(new Color(0,200,0));
        
        g.fillRect(y*pixelsize, x*pixelsize, pixelsize, pixelsize);
    }
    
    private void drawBorders(int[][] competitorMap)
    {
        for (int i = 0; i < competitorMap[0].length; i++) {
            competitorMap[0][i] = 1;
            competitorMap[competitorMap.length-1][i] = 1;
        }
    }
    
    private boolean setStartPoint(int[][] competitorMap)
    {
        for (int i = 100; i < 300; i++) {
            if (competitorMap[slotleveys/2][i] == 0) 
            {
                competitorMap[slotleveys/2][i] = 4;
                startpointheight = i;
                return true;
            }
        } return false;
    }
    
    int startpointheight;
    private boolean drawpoints = false;
    private boolean end = false;
    private boolean mid = false;
    
    public void runIterations()
    {
        if (generateMaze());
        else if (start)
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainFrame3.class.getName()).log(Level.SEVERE, null, ex);
            }
            start = false;
            
            drawBorders(competitorMap);
            
            if (setStartPoint(competitorMap));
            else 
            {
                competitorMap[slotleveys/2][0] = 4;
                startpointheight = 0;
            }
            
            drawpoints = true;
            
            competitorMap[slotleveys/2][slotsx-1] = 3;
            astar = new Astar3(competitorMap);
            bfs = new Bfs(competitorMap);
            dfs = new Dfs(competitorMap);
            rs = new RandomSearch(competitorMap);
            astargreedy = new AstarGreedy(competitorMap);
            mid = true;
        } 
        else if (mid)
        {
            if (astar.isFinished() && bfs.isFinished() && dfs.isFinished() && astargreedy.isFinished())
            {
                mid = false;
                end = true;
                drawpoints = false;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrame3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!astar.isFinished()) placeIteration(astar.iterate(), 0);//map = astar.iterate();
            if (!bfs.isFinished()) placeIteration(bfs.iterate(), 2);
            if (!dfs.isFinished()) placeIteration(dfs.iterate(),3);
            if (!astargreedy.isFinished()) placeIteration(astargreedy.iteration(), 1);
        }
        else if (end)
        {
            if (endAnimator == null) endAnimator = new EndAnimator(map);
            
            if (endAnimator.isFinished())
            {
                end = false;
                start = true;
                mazegenerator = new MazeGenerator2(slotleveys, slotsx);
                endAnimator = null;
            } else
            {
                map = endAnimator.iterate();
            }
        }
    }
    
    /**
     * render loop, where everything is triggered
     */
    @Override
    public void run() {
        
        // Main loop
        while (running) {
            // Prepare for rendering the next frame
            // ...

            // Render single frame
            do {
                // The following loop ensures that the contents of the drawing buffer
                // are consistent in case the underlying surface was recreated
                do {
                    // Get a new graphics context every time through the loop
                    // to make sure the strategy is validated
                    Graphics g = strategy.getDrawGraphics();

                    runIterations();
                    
                    render(g);
                    
                    if (drawpoints)
                    {
                        g.setColor(Color.GREEN);
                        g.fillOval(((slotleveys*pixelsize)/2)-10, (startpointheight*pixelsize)-10, 20, 20);
                        g.fillOval((((slotleveys*pixelsize)/2)-10)+slotleveys*pixelsize, (startpointheight*pixelsize)-10, 20, 20);
                        g.fillOval((((slotleveys*pixelsize)/2)-10)+slotleveys*pixelsize*2, (startpointheight*pixelsize)-10, 20, 20);
                        g.fillOval((((slotleveys*pixelsize)/2)-10)+slotleveys*pixelsize*3, (startpointheight*pixelsize)-10, 20, 20);
                        
                        g.setColor(Color.blue);
                        g.fillOval(((slotleveys*pixelsize)/2)-20, slotsx*pixelsize-30, 40, 40);
                        g.fillOval((((slotleveys*pixelsize)/2)-20)+slotleveys*pixelsize, slotsx*pixelsize-30, 40, 40);
                        g.fillOval((((slotleveys*pixelsize)/2)-20)+slotleveys*pixelsize*2, slotsx*pixelsize-30, 40, 40);
                        g.fillOval((((slotleveys*pixelsize)/2)-20)+slotleveys*pixelsize*3, slotsx*pixelsize-30, 40, 40);
                    }
                    g.setColor(Color.BLACK);
                    // Dispose the graphics
                    g.dispose();

                    // Repeat the rendering if the drawing buffer contents
                    // were restored
                } while (running && strategy.contentsRestored());

                // Display the buffer
                strategy.show();

                // Repeat the rendering if the drawing buffer was lost
            } while (running && strategy.contentsLost());
        }
        setVisible(false);
        dispose();
    }

    
    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
