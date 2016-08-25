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
import javax.swing.JFrame;
import tiralabra.communications.CommunicationBus;
import tiralabra.logic.MazeGenerator2;

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
    private CommunicationBus bus;

    public BufferedStrategyTest() 
    {
        super("FrameDemo");
        addWindowListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1600, 800);
        setResizable(true);
        setVisible(true);

        bus = new CommunicationBus();
        mazegenerator = new MazeGenerator2(400, 200, bus);
        
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

                    int[][] map = mazegenerator.iterate();
                    
                    
                    
                    for (int i = 0; i < 200; i++) {
                        for (int j = 0; j < 400; j++) {
                            if (map[j][i] == 0) 
                            {
                                g.setColor(Color.WHITE);
                                g.fillRect(j*4, i*4, 4, 4);
                            } else if (map[j][i] == 1)
                            {
                                g.setColor(Color.BLACK);
                                g.fillRect(j*4, i*4, 4, 4);
                            }else if (map[j][i] == 2)
                            {
                                g.setColor(Color.RED);
                                g.fillRect(j*4, i*4, 4, 4);
                            }else if (map[j][i] == 3)
                            {
                                g.setColor(Color.BLUE);
                                g.fillRect(j*4, i*4, 4, 4);
                            }
                            else if (map[j][i] == 4)
                            {
                                g.setColor(Color.GREEN);
                                g.fillRect(j*4, i*4, 4, 4);
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
