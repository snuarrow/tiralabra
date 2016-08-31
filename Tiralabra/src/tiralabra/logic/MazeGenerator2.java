/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import tiralabra.communications.CommunicationBus;
import tiralabra.logic.structures.NodeMap;

/**
 *
 * @author hexvaara
 */
public class MazeGenerator2 {
    
    private NodeMap nodemap;
    private boolean finished;
    private ArrayList<Node> unprocessed;
    private ArrayList<Node> stage2;
    private ArrayList<Node> stage3;
    private ArrayList<Node> finalStageWhites;
    ArrayList<ArrayList<Node>> sets;
    private CommunicationBus bus;
    private int defaultBuffer = 10000;
    
    public MazeGenerator2(int x, int y, CommunicationBus bus)
    {
        this.bus = bus;
        this.nodemap = new NodeMap(x,y);
        finished = false;
        unprocessed = new ArrayList<>();
        stage2 = new ArrayList<>();
        stage3 = new ArrayList<>();
        finalStageWhites = new ArrayList<>();
        sets = new ArrayList<>();
        
        nodemap.getAllNodes().forEach(n -> 
        {
            unprocessed.add(n);
        });
    }
    
    public int[][] getStartFrame()
    {
        return nodemap.getIntMap();
    }
    
    public boolean isFinished()
    {
        return finished;
    }
    
    
    private boolean s1 = true;
    private boolean s2 = true;
    private boolean s3 = true;
    //private boolean s4 = true;
    private boolean s5 = true;
    
    public int[][] iterate()
    {
        if (!s1 && !s2 && !s3 && !s5) finished = true;
        
        //System.out.println("was called");
        if (s1)
        {
            //System.out.println("stage1");
            if (!removeRandomBlack()) s1 = false;
        }
        else if (s2)
        {
            //System.out.println("stage2");
            if (!removeSingleBlacks()) s2 = false;
        }
        else if (s3)
        {
            //System.out.println("stage3");
            if (!fillSingleWhites()) s3 = false;
        }
        //else if (s4)
        //{
        //    //System.out.println("stage4");
        //    for (int i = 0; i < 10; i++)
        //        if (!findSet() && remainingWhites.isEmpty()) s4 = false;
        //}
        else if (s5)
        {
            //nodemap.greenToWhite();
            nodemap.swapColors(0, 1);
            s5 = false;
        }
        
        
        /*
        if (removeRandomBlack());
        else if (removeSingleBlacks());
        else if (fillSingleWhites());
        else 
        {
            for(int i = 0; i < 20; i++) 
            {
                if (!findSet() && remainingWhites.isEmpty()) break;
            }
        }
        */
        
        return nodemap.getIntMap();
    }
    
    private void connectSets()
    {
        while(!sets.isEmpty())
        {
            for (Node n : sets.get(new Random().nextInt(sets.size())))
            {
                for(Node m : nodemap.getCrossNeighbours(n))
                {
                    if (m.color == 1)
                    {
                        
                    }
                }
            }
        }
    }
    
    private void enlargeSet(int id)
    {
        getNeighboursOfSet(sets.get(id));
    }
    
    private ArrayList<Node> getNeighboursOfSet(ArrayList<Node> set)
    {
        ArrayList<Node> neighbours = new ArrayList<>();
        
        for (Node n : set) {
            nodemap.getCrossNeighbours(n).forEach(a -> 
            {
                if (!neighbours.contains(a)) neighbours.add(a);
            });
        }
        return neighbours;
    }
    
    private boolean removeSingleBlacks()
    {
        int remain = defaultBuffer;
        while(!stage2.isEmpty())
        {
            //System.out.println("aa");
            Node rn = stage2.get(0);
            
            if (nodemap.getCrossNeighbours(rn).stream().filter(n -> n.color == 0).count() == 4)
            {
                nodemap.changeColor(rn, 0);
                //stage2.remove(rn);
                //break;
            }
            
            stage2.remove(rn);
            
            if (remain-- > 0) continue;
            return true;
            
            
        } 
        return false;
    }
    
    private ArrayList<Node> remainingWhites;
    
    private boolean findSet()
    {
        if (remainingWhites == null)
        {
            remainingWhites = new ArrayList<>();
            for (Node n : nodemap.getAllNodes())
            {
                if (n.color == 0)
                {
                    remainingWhites.add(n);
                }
            }
        }
        
        ArrayList<Node> set = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        
       
        
        for (Node n : remainingWhites)
        {
            //System.out.println("finding set");
            if (n.color == 0) 
            {
                queue.add(n);
                nodemap.changeColor(n, 4);
                remainingWhites.remove(n);
                break;
            }
        }
        
        
        
        //queue.add(some);
        
        while (!queue.isEmpty())
        {
            Node polled = queue.poll();
            
            for (Node n : nodemap.getNeighbours(polled))
            {
                if (n.color == 0 && !set.contains(n))
                {
                    set.add(n);
                    queue.add(n);
                }
            }
        }
        
        set.stream().forEach(n -> 
        {
            nodemap.changeColor(n, 4);
            remainingWhites.remove(n);
        });
        
        sets.add(set);
        
        if (set.isEmpty()) return false;
        return true;
        
    }
    
    
    
    private boolean fillSingleWhites()
    {
        int remain = defaultBuffer;
        while(!stage3.isEmpty())
        {
            //long whiteCount = nodemap.getNeighbours(stage3.get(0)).stream().filter(n -> n.color == 0).count();
            long blackCount = nodemap.getNeighbours(stage3.get(0)).stream().filter(n -> n.color == 1).count();
            
            if (blackCount >= 6) 
            {
                nodemap.changeColor(stage3.get(0), 1);
                
            }
            
            stage3.remove(0);
            
            if (remain-- > 0) continue;
            return true;
        }
        
        return false;
    }
    
    /*
    private boolean removeRandomBlack2() throws InterruptedException
    {
        for (int x = 0; x < 900; x++) {
            for (int y = 0; y < 1600; y++) {
                bus.setNewPixel(x, y, 1);
                //bus.doNotify();
            }
        }
        return true;
    }
    */
    
    
    
    private boolean removeRandomBlack()
    {
        int remain = defaultBuffer;
        while (!unprocessed.isEmpty())
        {
            Node rn = unprocessed.get(new Random().nextInt(unprocessed.size()));
            int b = 0;
            int w = 0;
            
            for (Node n : nodemap.getCrossNeighbours(rn))
            {
                if (n.color == 0) w++;
                else b++;
            }
            
            /*
            long cornerwhitecount = nodemap.getCornerNeighbours(rn).stream().filter(n -> nodemap.getColor(n) == 0).count();
            
            if (cornerwhitecount > 0) 
            {
                unprocessed.remove(rn);
                return false;
            }
            */
                    
            if (b == 4)
            {
                nodemap.changeColor(rn, 0);
                //bus.setNewPixel(rn.x, rn.y, 0);
                //System.out.println("here");
                unprocessed.remove(rn);
                stage3.add(rn);
                //nodemap.getCornerNeighbours(rn).stream().forEach(n -> unprocessed.remove(n));
                if (remain-- > 0) continue;
                return true;
            }
            
            if (w == 4)
            {
                nodemap.changeColor(rn, 0);
                //bus.setNewPixel(rn.x, rn.y, 0);
                unprocessed.remove(rn);
                stage3.add(rn);
                if (remain-- > 0) continue;
                return true;
            }
            
            unprocessed.remove(rn);
            //stage3.add(rn);
            stage2.add(rn);
        } 
        return false;
    }
    
}
