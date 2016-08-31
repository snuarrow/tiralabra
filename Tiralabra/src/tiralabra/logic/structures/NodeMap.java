/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import java.util.ArrayList;
import tiralabra.logic.Node;

/**
 * n*m sized array for handling nodes in graph.
 * for easy access.
 * 
 * 
 * @author hexvaara
 */
public class NodeMap {
    
    private Node[][] nodemap;
    private final int xsize;
    private final int ysize;
    private int xgoal, ygoal, xstart, ystart;
    
    //tested
    public NodeMap(int xsize, int ysize, int[][] bytemap)
    {
        nodemap = new Node[xsize][ysize];
        this.xsize = xsize;
        this.ysize = ysize;
        init(bytemap);
    }
    
    
    //tested
    public NodeMap(int xsize, int ysize)
    {
        nodemap = new Node[xsize][ysize];
        this.xsize = xsize;
        this.ysize = ysize;
        initBlack();
    }
    
    //tested
    public Node getGoal()
    {
        return nodemap[xgoal][ygoal];
    }
    
    //tested
    public Node getStart()
    {
        return nodemap[xstart][ystart];
    }
    
    // tested
    public void changeColor(Node n, int color)
    {
        if (nodemap[n.x][n.y].color != 4 && nodemap[n.x][n.y].color != 3) nodemap[n.x][n.y].color = (byte) color;
    }
    
    //tested
    private void init(int[][] bytemap)
    {
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                nodemap[i][j] = new Node(i, j, bytemap[i][j], null, 0);
                if (bytemap[i][j] == 3) { xgoal = i; ygoal = j;}
                else if (bytemap[i][j] == 4) { xstart = i; ystart = j;}
            }
        }
    }
    
    //tested
    private void initBlack()
    {
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                nodemap[i][j] = new Node(i, j, 1);
            }
        }
    }
    
    public ArrayList<Node> getFreeNeighbours(Node n)
    {
        ArrayList<Node> returnlist = new ArrayList<>();
        for (Node nn : getCrossNeighbours(n)) {
            if (nn.color != 1) returnlist.add(nn);
        }
        return returnlist;
    }
    
    //tested
    public Node getNode(int x, int y)
    {
        return nodemap[x][y];
    }
    
    public void swapColors(int a, int b)
    {
        for (Node[] nodemap1 : nodemap) {
            for (Node nodemap11 : nodemap1) {
                if (nodemap11.color == a) nodemap11.color = (byte) b;
                else if (nodemap11.color == b) nodemap11.color = (byte) a; 
            }
        }
    }
    
    
    public void greenToWhite()
    {
        for (Node[] nodemap1 : nodemap) {
            for (Node nodemap11 : nodemap1) {
                if (nodemap11.color == 4) nodemap11.color = 0;
            }
        }
    }
    
    //tested
    public ArrayList<Node> getAllNodes()
    {
        ArrayList<Node> returnlist = new ArrayList<>();
        
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                returnlist.add(nodemap[i][j]);
            }
        }
        return returnlist;
    }
    
    //tested
    public ArrayList<Node> getCrossNeighbours(Node n)
    {
        ArrayList<Node> r = new ArrayList<Node>();
        
        //up
        if (n.x > 0) r.add(nodemap[n.x-1][n.y]);
       
        //down
        if (n.x < xsize-1) r.add(nodemap[n.x+1][n.y]);
        
        //left
        if (n.y > 0) r.add(nodemap[n.x][n.y-1]);
        
        //right
        if (n.y < ysize-1) r.add(nodemap[n.x][n.y+1]);
        
        return r;
    }
    
    //tested
    public ArrayList<Node> getCornerNeighbours(Node n)
    {
        ArrayList<Node> r = new ArrayList<>();
        
        //upleft
        if (n.x > 0 && n.y > 0) r.add(nodemap[n.x-1][n.y-1]);
        
        
        //upright
        if (n.x > 0 && n.y < ysize-1) r.add(nodemap[n.x-1][n.y+1]);
        
        //downleft
        if (n.x < xsize-1 && n.y > 0) r.add(nodemap[n.x+1][n.y-1]);
        
        //downright
        if (n.x < xsize-1 && n.y < ysize-1) r.add(nodemap[n.x+1][n.y+1]);
        
        return r;
    }
    
    //tested
    public int getColor(Node n)
    {
        return nodemap[n.x][n.y].color;
    }
    
    public ArrayList<Node> getAllRemainingBlacks()
    {
        ArrayList<Node> r = new ArrayList<Node>();
        for (Node[] r1 : nodemap) {
            for (Node r11 : r1) {
                if (r11.color == 1) r.add(r11);
            }
        }
        return r;
    }
    
    //tested
    public ArrayList<Node> getNeighbours(Node n)
    {
        ArrayList<Node> r = new ArrayList<Node>();
        
        r.addAll(getCrossNeighbours(n));
        r.addAll(getCornerNeighbours(n));
        
        return r;
    }
    
    public int[][] getIntMap()
    {
        int[][] temp = new int[xsize][ysize];
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                temp[i][j] = nodemap[i][j].color;
            }
        }
        return temp;
    }
    
    @Override
    public String toString()
    {
        String temp = "";
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                temp += nodemap[i][j].color;
            }
            temp += "\n";
        }
        return temp;
    }
}
