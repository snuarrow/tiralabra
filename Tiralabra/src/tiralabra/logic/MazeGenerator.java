/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import tiralabra.logic.structures.NodeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import tiralabra.logic.structures.MazeSet;

/**
 * Maze Generator.
 * not fully implemented yet
 * 
 * @author hexvaara
 */
public class MazeGenerator {
    
    private int[][] amaze;
    private ArrayList<MazeSet> sets;
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
    
    public byte[][] getStartFrame()
    {
        return nodemap.getByteMap();
    }
    
    int mazeSetId = 1;
    
    private void putIntoSet(Node n)
    {
        //System.out.println("i was put into set: "+n);
        if (sets.isEmpty()) 
        {
            amaze[n.x][n.y] = mazeSetId;
            sets.add(new MazeSet(mazeSetId++));
            sets.get(0).set.add(n);
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
            sets.add(new MazeSet(mazeSetId++));
            sets.get(sets.size()-1).set.add(n);
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
        for (MazeSet set : sets) {
            if (set.id == setId) 
            {
                set.set.add(n);
                amaze[n.x][n.y] = (byte) setId;
            }
        }
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
        if (blacks == null) 
        {
            blacks = nodemap.getAllRemainingBlacks();
        }
        
        Node randomBlack = blacks.get(new Random().nextInt(blacks.size()-1));
        
        ArrayList<Integer> neighboringSetIds = new ArrayList<>();
        
        for (Node n : nodemap.getCrossNeighbours(randomBlack))
        {
           int setid = belongsToSet(n);
           if (setid != -1 && !neighboringSetIds.contains(setid))
           {
               neighboringSetIds.add(setid);
           }
        }
        
        if (neighboringSetIds.size() == 0) {}
        if (neighboringSetIds.size() == 1) {
            // do nothing
        }
        if (neighboringSetIds.size() == 2)
        {
            joinSets(neighboringSetIds.get(0), neighboringSetIds.get(1));
            nodemap.changeColor(randomBlack, 0);
        }
        
        // if two of cross neighbours belongs to different sets, join sets and color randomblack white, add randomblack to The set and remove randomblack from blacks, continue
        
        
        
        
        
    }
    
    
    
    private void joinSets(int a, int b)
    {
        MazeSet aa = null;
        MazeSet bb = null;
        
        for (MazeSet set : sets) {
            if (set.id == a) aa = set;
            else if (set.id == b) bb = set;
        }
        
        //System.out.println("aa: "+aa.id+" bb: "+bb.id+" aasize: "+aa.set.size()+" bbsize: "+bb.set.size());
        
        for (Node n : bb.set) {
            //System.out.println("kävinkö ees täällä?");
            amaze[n.x][n.y] = a;
            aa.set.add(n);
        }
        
        sets.remove(bb);
    }
    
    private int belongsToSet(Node n)
    {
        for (MazeSet set : sets) {
            if (set.set.contains(n)) return set.id;
        }
        return -1;
    }
    
    private boolean finished = false;
    
    public boolean isFinished()
    {
        return finished;
    }
    
    public byte[][] iterate()
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
            return nodemap.getByteMap();
        }   
        
        if (sets.size()>1) joinSets();
        else finished = true;
        
        return nodemap.getByteMap();
    }
}
