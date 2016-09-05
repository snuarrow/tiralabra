package tiralabra.logic;

import tiralabra.logic.structures.Node;
import tiralabra.logic.structures.NodeMap;
import java.util.ArrayList;
import java.util.Random;

/**
 * Maze Generator.
 * Generates traditional maze with multiple possible routes. not in use.
 * Is not implemented with own data structures, because not in use.
 * @author hexvaara
 */
public class MazeGenerator {
    
    private int[][] amaze;
    private ArrayList<ArrayList<Node>> sets;
    private ArrayList<Node> unprocessed;
    private ArrayList<Node> processed;
    private NodeMap nodemap;
    private int xsize, ysize;
    
    public MazeGenerator(int xsize, int ysize)
    {
        amaze = new int[xsize][ysize];
        this.xsize = xsize;
        this.ysize = ysize;
        nodemap = new NodeMap(xsize, ysize);
        
        sets = new ArrayList<>();
        
        unprocessed = new ArrayList<>();
        processed = new ArrayList<>();
        
        for (Node n : nodemap.getAllNodes()) {
            unprocessed.add(n);
        }
    }
    
    public int[][] getStartFrame()
    {
        return nodemap.getIntMap();
    }
    
    int mazeSetId = 1;
    
    private void putIntoSet(Node n)
    {
        if (sets.isEmpty()) 
        {
            amaze[n.x][n.y] = mazeSetId;
            sets.add(new ArrayList<>());
            sets.get(0).add(n);
            return;
        }
        
        ArrayList<Node> neighbors = nodemap.getCrossNeighbours(n);
        ArrayList<Integer> neighboringSets = new ArrayList<>();
        
        for (Node neighbor : neighbors) {
            if (belongsToSet(neighbor) != -1)
            {
                if (!neighboringSets.contains(belongsToSet(neighbor)))neighboringSets.add(belongsToSet(neighbor));
            }
        }
        
        if (neighboringSets.isEmpty())
        {
            amaze[n.x][n.y] = mazeSetId;
            sets.add(new ArrayList<>());
            sets.get(sets.size()-1).add(n);
            return;
        }
        else if (neighboringSets.size() == 1)
        {
            amaze[n.x][n.y] = neighboringSets.get(0);
            putIntoSet(n, neighboringSets.get(0));
        } else
        {
            int set0 = neighboringSets.get(0);
            for (int i = 1; i < neighboringSets.size(); i++) {
                joinSets(set0, neighboringSets.get(i));
            }
            
        }
        
    }
    
    private void putIntoSet(Node n, int setId)
    {
        sets.get(setId).add(n);
        
    }
    
    public void printAmaze()
    {
        String returnvalue = "";
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                returnvalue += amaze[i][j];
            }
            returnvalue += "\n";
        }
        System.out.println(returnvalue);
    }
    
    private ArrayList<Node> blacks;
    private boolean run = true;
    
    private void joinSets()
    {
        //for (int i = 0; i < sets.size(); i++) {
            
        int i = smallestSetIndex();
        
            ArrayList<Node> allNeighbours = getNeighboursOfSet(sets.get(i));
            ArrayList<Node> filtered = new ArrayList<>();
            
            for (Node n : allNeighbours) // find and connect touching whitesets
            {
                int nset = belongsToSet(n);
                
                if (nset == -1 && nodemap.getColor(n) == 0)
                {
                    sets.get(i).add(n);
                    return;
                }
                
                if (nodemap.getColor(n) == 1) filtered.add(n);
                
                if (i != nset && nodemap.getColor(n) == 0)
                {
                    joinSets(i, nset);
                    return;
                }
            }
            
            for (Node black : filtered) {
                
                ArrayList<Integer> setIds = new ArrayList<>();
                
                for (Node neighbour : nodemap.getCrossNeighbours(black))
                {
                    int setid = belongsToSet(neighbour);
                    
                    if (setid != -1 && !setIds.contains(setid)) setIds.add(setid);
                    
                }
                
                if (setIds.size() > 1)
                {
                    for (int j = 1; j < 10; j++) {
                        joinSets(setIds.get(0), setIds.get(j));
                        nodemap.changeColor(black, 0);
                        return;
                    }
                }
            }
           
        if (sets.get(i).size() == 1) nodemap.changeColor(sets.get(i).get(0), 3);
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
    
    private int smallestSetIndex()
    {
        int smallestSetSize = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < sets.size(); i++) {
            if (sets.get(i).size() < smallestSetSize)
            {
                smallestSetSize = sets.get(i).size();
                index = i;
            }
        }
        return index;
    }
    
    
    private void fixBlacks() // not implemented yet
    {
        nodemap.getAllRemainingBlacks().forEach(n -> 
        {
            nodemap.getCrossNeighbours(n).forEach(c -> 
            {
                if (c.color == 1)
                {
                    if (n.x > c.x)
                    {
                        // yläkulmat suhteessa n.
                        if (n.y > c.y)
                        {
                            // vasen ylä
                            
                        } else 
                        {
                            // oikee ylä
                        }
                    } else
                    {
                        if (n.y > c.y)
                        {
                            // vasen ala
                            
                        } else 
                        {
                            // oikee ala
                        }
                        // alakulmat
                    }
                }
            });
        });
    }
    
    
    
    private void joinSets(int a, int b)
    {
        //System.out.println(a+" "+b);
        
        
        for (Node n : sets.get(b)) sets.get(a).add(n);
        sets.remove(b);
        
    }
    
    private int belongsToSet(Node n)
    {
        //if (sets.isEmpty()) return -1;
        
        for (int i = 0; i < sets.size(); i++)
        {
            if (sets.get(i).contains(n)) return i;
        }
        return -1;
    }
    
    private boolean finished = false;
    
    public boolean isFinished()
    {
        return finished;
    }
    
    private void findSetlessNodes()
    {
        nodemap.getAllNodes().forEach(node -> 
        {
            if (nodemap.getColor(node) == 0 && belongsToSet(node) == -1) nodemap.changeColor(node, 4);
        });
    }
    
    private boolean removeRandomBlack()
    {
        if (!unprocessed.isEmpty())
        {
            int randomIndex = new Random().nextInt(unprocessed.size());
            Node randomUnprocessedNode = unprocessed.get(randomIndex);
            unprocessed.remove(randomIndex);
            
            int blackCount = 0;
            int whiteCount = 0;
            nodemap.getCrossNeighbours(randomUnprocessedNode).forEach(neighbourOfRandom -> 
            {
                
            });
            
            
            
            return true;
        } return false;
    }
    
    public int[][] iterate()
    {
        if (!unprocessed.isEmpty())
        {
            int randomIndex = new Random().nextInt(unprocessed.size());
            Node randomUnprocessedNode = unprocessed.get(randomIndex);
            unprocessed.remove(randomIndex);
            processed.add(randomUnprocessedNode);
            
            
            ArrayList<Node> neighbours = nodemap.getNeighbours(randomUnprocessedNode);
            int blackCount = 0;
            
            for (Node neighbour : neighbours) {
                if (neighbour.color == 1) blackCount++;
            }
            
            if (blackCount >= 6) 
            {
                nodemap.changeColor(randomUnprocessedNode, 0);
                //System.out.println("put into set called");
                putIntoSet(randomUnprocessedNode);
        
            }
            //System.out.println("");
            //printAmaze();
            return nodemap.getIntMap();
        }   
        
        if (sets.size()>1) findSetlessNodes();
        else finished = true;
        
        return nodemap.getIntMap();
    }
}
