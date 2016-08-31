/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra;

import java.awt.Color;
import java.awt.Graphics;
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
import tiralabra.logic.MazeGenerator2;
import tiralabra.logic.RandomSearch;

/**
 *
 * @author hexvaara
 */
public class BufferedStrategyTest extends JFrame implements Runnable, WindowListener 
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
    
    private CommunicationBus bus;
    private int[][] map;
    private int[][] competitorMap;
    private int pixelsize = 2;
    private int slotsx = 400;
    private int slotsy = 800;
    //private int competitors = 4;
    private int slotleveys = 200;
    
    
    public BufferedStrategyTest() 
    {
        super("Tiralabra");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //super.setLocationRelativeTo(null);
        addWindowListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(slotsy*pixelsize, slotsx*pixelsize);
        setResizable(true);
        setVisible(true);

        bus = new CommunicationBus();
        mazegenerator = new MazeGenerator2(slotleveys, slotsx, bus);
        
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
        
        //System.out.println("slotsx:"+slotsx+" slotleveys:"+slotleveys);
        
        for (int x = 0; x < slotsx-2; x++) {
            for (int y = 0; y < slotleveys; y++) {
                //int t = temp[x][y];
                map[y][x] = temp[y][x];
                map[y+slotleveys][x] = temp[y][x];
                map[y+2*slotleveys][x] = temp[y][x];
                map[y+3*slotleveys][x] = temp[y][x];
            }
        }
        return true;
    }
    
    private boolean grid = false;
    private void drawGrid()
    {
        for (int x = 0; x < slotsx ; x++) {
            
            for (int i = 1; i < 4; i++) {
                map[i*slotleveys-2][x] = 1;
                map[i*slotleveys-1][x] = 1;
                map[i*slotleveys][x] = 1;
                map[i*slotleveys+1][x] = 1;
            }
        }
        
        for (int i = 0; i < slotsx; i++) {
            map[0][i] = 1;
            map[1][i] = 1;
            map[2][i] = 1;
            
            map[slotsy-1][i] = 1;
            map[slotsy-2][i] = 1;
            map[slotsy-3][i] = 1;
        }
        
        /*
        for (int i = 0; i < slotsy; i++) {
            map[i][0] = 1;
            map[i][1] = 1;
            map[i][2] = 1;
            
            map[i][slotsx-1] = 1;
            map[i][slotsx-2] = 1;
            map[i][slotsx-3] = 1;
        }
                */
        
        grid = true;
    }
    
    private void placeIteration(int[][] algo, int slot)
    {
        for (int i = 0; i < slotsx; i++) {
            for (int j = 0; j < slotleveys; j++) {
                map[j+slot*slotleveys][i] = algo[j][i];
            }
        }
        //drawGrid();
    }

    private boolean start = true;
    
    @Override
    public void run() {
        
        int a = 0;
        int b = 0;
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

                    //if (!mazegenerator.isFinished()) map = mazegenerator.iterate();
                    if (generateMaze());
                    else if (!grid) drawGrid();
                    else if (start)
                    {
                        start = false;
                        int startpointheight = 80;
                        while (competitorMap[slotleveys/2][startpointheight++] == 0) competitorMap[slotleveys/2][startpointheight] = 4;
                        competitorMap[slotleveys/2][slotsx-1] = 3;
                        astar = new Astar3(competitorMap);
                        bfs = new Bfs(competitorMap);
                        dfs = new Dfs(competitorMap);
                        rs = new RandomSearch(competitorMap);
                        astargreedy = new AstarGreedy(competitorMap);
                        
                    } 
                    else
                    {
                        if (!astar.isFinished()) placeIteration(astar.iterate(), 0);//map = astar.iterate();
                        //if (!bfs.isFinished()) placeIteration(bfs.iterate(), 1);
                        if (!bfs.isFinished()) placeIteration(bfs.iterate(),2);
                        if (!dfs.isFinished()) placeIteration(dfs.iterate(),3);
                        if (!astargreedy.isFinished()) placeIteration(astargreedy.iteration(), 1);
                        
                        if (astar.isFinished() && rs.isFinished() && dfs.isFinished() && astargreedy.isFinished())
                        {
                            mazegenerator = new MazeGenerator2(slotleveys, slotsx, bus);
                            start = true;
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BufferedStrategyTest.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                    
                    
                    
                    
                    for (int i = 0; i < slotsx; i++) {
                        for (int j = 0; j < slotsy; j++) {
                            if (map[j][i] == 0) 
                            {
                                g.setColor(Color.WHITE);
                                g.fillRect(j*pixelsize, i*pixelsize, pixelsize, pixelsize);
                            } else if (map[j][i] == 1)
                            {
                                g.setColor(Color.BLACK);
                                g.fillRect(j*pixelsize, i*pixelsize, pixelsize, pixelsize);
                            }else if (map[j][i] == 2)
                            {
                                g.setColor(Color.RED);
                                g.fillRect(j*pixelsize, i*pixelsize, pixelsize, pixelsize);
                            }else if (map[j][i] == 3)
                            {
                                g.setColor(Color.BLUE);
                                g.fillRect(j*pixelsize, i*pixelsize, pixelsize, pixelsize);
                            }
                            else if (map[j][i] == 4)
                            {
                                g.setColor(Color.GREEN);
                                g.fillRect(j*pixelsize, i*pixelsize, pixelsize, pixelsize);
                            }
                            
                        }
                    }
                    
                    
                    //.fillRect(bus.x(), bus.y(), 10, 10);
                    //g.setColor(Color.GRAY);
                    //g.drawRect(0, 0, 500, 500);
                    g.setColor(Color.BLACK);
                    //g.drawLine(a++, b++, 200, 50);

                    //if (a > 500) a = 0;
                    //if (b > 500) b = 0;
                    
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
