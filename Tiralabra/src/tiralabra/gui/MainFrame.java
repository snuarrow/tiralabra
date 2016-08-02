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
import tiralabra.logic.Astar;
import tiralabra.logic.Bfs;
import tiralabra.logic.Board;

/**
 *
 * @author hexvaara
 */

public class MainFrame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
    private final Timer t;
    private Board board;
    private Bfs bfs;
    private Astar astar;
    /*
    private Engine engine;
    private final Global global;
    private final Picture smiley;
    private final Picture background;
    private int image_display_time;
    private Card clicked = null;
    private int image_zoom_time;
    private final StartMenu startmenu;
    private final SettingsMenu settingsmenu;
    private final EndMenu endmenu;
    
    private int card_clicks;
    private int gamestate; // 0 = startmenu, 1 = in game, 2 = in settings menu
    private final GameStarter gamestarter;
    */
    
    public MainFrame(ProgramStarter gamestarter, Board b)
    {
        this.board = b;
        
        
        this.bfs = new Bfs(this.board);
        bfs.setStart(0, 0);
        
        
        this.astar = new Astar(this.board);
        astar.setStart(0, 0);
        
        
        /*
        this.gamestarter = gamestarter;
        
        card_clicks = 0;
        gamestate = 0;
        
        startmenu = new StartMenu(global);
        settingsmenu = new SettingsMenu(global);
        endmenu = new EndMenu(global);
        
        image_zoom_time = 0;
        image_display_time = -1;
        smiley = new Picture(0, "acid3tb.png");
        background = new Picture(0, "background.png");
        this.global = global;
        engine = new Engine(global);
        */
        
        
        addMouseListener(this);
        addMouseMotionListener(this);
        t = new Timer(50, this);
        t.start();
    }

    
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
                
                
                if (board.getBoard()[j][i] == 1) drawObstacle(g, i, j);
                if (board.getBoard()[j][i] == 2) drawRed(g, i, j);
                if (board.getBoard()[j][i] == 3) drawBlue(g, i, j);
                if (board.getBoard()[j][i] == 4) drawGreen(g, i, j);
                
                
                
                //g.setPaint();
                
            }
           
        }
        
        
        
        //g.drawImage(new Picture(0, "pluto.gif").image(), 0, 0, global.getHorizontalsize(), global.getVerticalsize(), this);
        
        //startmenu.drawHeadline(g);
        
        //for (Button button : startmenu.buttons())
        //{
        //    button.draw(g);
        //}
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
    
    private void iteration()
    {
        repaint();
        astar.iterate();
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
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
