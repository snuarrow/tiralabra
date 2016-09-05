package tiralabra.logic;

import tiralabra.logic.structures.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import tiralabra.logic.structures.NodeMap;
import tiralabra.logic.structures.NodeQueue2;

/**
 * Generates randomly distributed barrier map.
 * iterations designed for nice graphical output.
 * 
 * @author hexvaara
 */
public class MazeGenerator2 {
    
    private NodeMap nodemap;
    private boolean finished;
    
    private ArrayList<Node> unprocessed;
    //private NodeQueue2 unprocessed;
    
    private ArrayList<Node> stage2;
    //private NodeQueue2 stage2;
    
    private ArrayList<Node> stage3;
    //private NodeQueue2 stage3;
    
    private ArrayList<Node> finalStageWhites;
    //private NodeQueue2 finalStageWhites;
    
    private ArrayList<Node> unswapped;
    
    private ArrayList<Node> remainingWhites;
    private ArrayList<ArrayList<Node>> sets;
    private int defaultBuffer = 2000;
    
    public MazeGenerator2(int x, int y)
    {
        this.nodemap = new NodeMap(x,y);
        finished = false;
        unprocessed = new ArrayList<>();
        //unprocessed = new NodeQueue2();
        stage2 = new ArrayList<>();
        //stage2 = new NodeQueue2();
        stage3 = new ArrayList<>();
        //stage3 = new NodeQueue2();
        finalStageWhites = new ArrayList<>();
        //finalStageWhites = new NodeQueue2();
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
    private boolean s5 = true;
    
    public int[][] iterate()
    {
        if (!s1 && !s2 && !s3 && !s5) finished = true;
        
        if (s1)
        {
            if (!removeRandomBlack()) s1 = false;
        }
        else if (s2)
        {
            if (!removeSingleBlacks()) s2 = false;
        }
        else if (s3)
        {
            if (!fillSingleWhites()) s3 = false;
        }
        else if (s5)
        {
            if(!swapColors(0,1)) s5 = false;
            
        }
        return nodemap.getIntMap();
    }
    
    private boolean swapColors(int colora, int colorb)
    {
        for (int i = 0; i < defaultBuffer; i++) {
            
            if (unswapped == null)
            {
                unswapped = new ArrayList<>();
                for (Node n : nodemap.getAllNodes())
                    unswapped.add(n);
            }
        
            if (unswapped.isEmpty()) return false;
        
            int random = new Random().nextInt(unswapped.size());
            Node randomNode = unswapped.get(random);
            unswapped.remove(random);
        
            if (randomNode.color == colora) 
            {
                nodemap.changeColor(randomNode, colorb);
            }
            else if (randomNode.color == colorb)
            {
                nodemap.changeColor(randomNode, colora);
            }
        }
        return true;
    }
    
    // not used
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
    
    // not used
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
            Node rn = stage2.get(0);
            
            if (nodemap.getCrossNeighbours(rn).stream().filter(n -> n.color == 0).count() == 4)
            {
                nodemap.changeColor(rn, 0);
            }
            
            stage2.remove(rn);
            
            if (remain-- > 0) continue;
            
            return true;
        } 
        return false;
    }
    
    // not used
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
            if (n.color == 0) 
            {
                queue.add(n);
                nodemap.changeColor(n, 4);
                remainingWhites.remove(n);
                break;
            }
        }
        
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
            int blackCount = 0;
            for (Node n : nodemap.getNeighbours(stage3.get(0))) 
            {
                if (n != null && n.color == 1) blackCount++;
            }
            
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
            if (b == 4)
            {
                nodemap.changeColor(rn, 0);
                unprocessed.remove(rn);
                stage3.add(rn);
                if (remain-- > 0) continue;
                return true;
            }
            
            if (w == 4)
            {
                nodemap.changeColor(rn, 0);
                unprocessed.remove(rn);
                stage3.add(rn);
                if (remain-- > 0) continue;
                return true;
            }
            
            unprocessed.remove(rn);
            stage2.add(rn);
        } 
        return false;
    }
}
