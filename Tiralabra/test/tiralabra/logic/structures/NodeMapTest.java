/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tiralabra.logic.Node;

/**
 *
 * @author hexvaara
 */
public class NodeMapTest {
    
    private NodeMap nmap;
    private final int[][] bytemap;
    
    public NodeMapTest() {
        int[][] bytemap = {
            {1,4,1,1,1,1,1,1,1,1},
            {3,1,1,1,1,1,1,1,1,1},
            {1,1,0,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,0,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1}
        };
        
        this.bytemap = bytemap;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nmap = new NodeMap(10,10,bytemap);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGoal method, of class NodeMap.
     */
    @Test
    public void testConstructorBlack() {
        NodeMap n = new NodeMap(10,10);
        
        int[][] bytemap = {
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1}
        };
        
        Assert.assertArrayEquals(bytemap, n.getIntMap());
    }
    
    @Test
    public void testConstructorSelfInit()
    {
        NodeMap n = new NodeMap(10,10, bytemap);
        
        Assert.assertArrayEquals(bytemap, n.getIntMap());
        assertEquals(1, n.getGoal().x);
        assertEquals(0, n.getGoal().y);
        
        assertEquals(0, n.getStart().x);
        assertEquals(1, n.getStart().y);
    }
    
    @Test
    public void testChangeColor()
    {
        assertEquals(1, nmap.getColor(new Node(5,6)));
        nmap.changeColor(new Node(5,6), 0);
        assertEquals(0, nmap.getColor(new Node(5,6)));
        
        assertEquals(3, nmap.getColor(new Node(1,0)));
        nmap.changeColor(new Node(1,0), 0);
        assertEquals(3, nmap.getColor(new Node(1,0)));
        
        assertEquals(4, nmap.getColor(new Node(0,1)));
        nmap.changeColor(new Node(0,1), 0);
        assertEquals(4, nmap.getColor(new Node(0,1)));
    }
    
    @Test
    public void testGetColor()
    {
        assertEquals(1,nmap.getColor(new Node(0,0)));
        assertEquals(3,nmap.getColor(new Node(1,0)));
        assertEquals(4,nmap.getColor(new Node(0,1)));
    }
    
    @Test
    public void testGetCornerNeighbours()
    {
        ArrayList<Node> n = nmap.getCornerNeighbours(new Node(0,0));
        
        assertTrue(n.contains(nmap.getNode(1, 1)));
        assertEquals(1, n.size());
        
        n = nmap.getCornerNeighbours(new Node(2,2));
        assertTrue(n.contains(nmap.getNode(1, 1)));
        assertTrue(n.contains(nmap.getNode(3, 1)));
        assertTrue(n.contains(nmap.getNode(1, 3)));
        assertTrue(n.contains(nmap.getNode(3, 3)));
        assertEquals(4, n.size());
        
        n = nmap.getCornerNeighbours(new Node(0,5));
        assertTrue(n.contains(nmap.getNode(1, 6)));
        assertTrue(n.contains(nmap.getNode(1, 4)));
        assertEquals(2, n.size());
        
        n = nmap.getCornerNeighbours(new Node(5,0));
        assertTrue(n.contains(nmap.getNode(6, 1)));
        assertTrue(n.contains(nmap.getNode(4, 1)));
        assertEquals(2, n.size());
        
        n = nmap.getCornerNeighbours(new Node(9,5));
        assertTrue(n.contains(nmap.getNode(8, 4)));
        assertTrue(n.contains(nmap.getNode(8, 6)));
        assertEquals(2, n.size());
        
        n = nmap.getCornerNeighbours(new Node(5,9));
        assertTrue(n.contains(nmap.getNode(4, 8)));
        assertTrue(n.contains(nmap.getNode(6, 8)));
        assertEquals(2, n.size());
        
        n = nmap.getCornerNeighbours(new Node(9,9));
        assertTrue(n.contains(nmap.getNode(8, 8)));
        assertEquals(1, n.size());
        
        n = nmap.getCornerNeighbours(new Node(0,9));
        assertTrue(n.contains(nmap.getNode(1, 8)));
        assertEquals(1, n.size());
        
        n = nmap.getCornerNeighbours(new Node(9,0));
        assertTrue(n.contains(nmap.getNode(8, 1)));
        assertEquals(1, n.size());
    }
    
    @Test
    public void testGetCrossNeighbours()
    {
        ArrayList<Node> n = nmap.getCrossNeighbours(new Node(0,0));
        assertTrue(n.contains(nmap.getNode(0, 1)));
        assertTrue(n.contains(nmap.getNode(1, 0)));
        assertEquals(2, n.size());
        
        n = nmap.getCrossNeighbours(new Node(9,9));
        assertTrue(n.contains(nmap.getNode(9, 8)));
        assertTrue(n.contains(nmap.getNode(8, 9)));
        assertEquals(2, n.size());
        
        n = nmap.getCrossNeighbours(new Node(0,9));
        assertTrue(n.contains(nmap.getNode(0, 8)));
        assertTrue(n.contains(nmap.getNode(1, 9)));
        assertEquals(2, n.size());
        
        n = nmap.getCrossNeighbours(new Node(9,0));
        assertTrue(n.contains(nmap.getNode(8, 0)));
        assertTrue(n.contains(nmap.getNode(9, 1)));
        assertEquals(2, n.size());
        
        n = nmap.getCrossNeighbours(new Node(2,2));
        assertTrue(n.contains(nmap.getNode(2, 1)));
        assertTrue(n.contains(nmap.getNode(1, 2)));
        assertTrue(n.contains(nmap.getNode(2, 3)));
        assertTrue(n.contains(nmap.getNode(3, 2)));
        assertEquals(4, n.size());
        
        n = nmap.getCrossNeighbours(new Node(0,5));
        assertTrue(n.contains(nmap.getNode(0, 4)));
        assertTrue(n.contains(nmap.getNode(0, 6)));
        assertTrue(n.contains(nmap.getNode(1, 5)));
        assertEquals(3, n.size());
        
        n = nmap.getCrossNeighbours(new Node(5,0));
        assertTrue(n.contains(nmap.getNode(4, 0)));
        assertTrue(n.contains(nmap.getNode(6, 0)));
        assertTrue(n.contains(nmap.getNode(5, 1)));
        assertEquals(3, n.size());
        
        n = nmap.getCrossNeighbours(new Node(9,5));
        assertTrue(n.contains(nmap.getNode(9, 4)));
        assertTrue(n.contains(nmap.getNode(9, 6)));
        assertTrue(n.contains(nmap.getNode(8, 5)));
        assertEquals(3, n.size());
        
        n = nmap.getCrossNeighbours(new Node(5,9));
        assertTrue(n.contains(nmap.getNode(4, 9)));
        assertTrue(n.contains(nmap.getNode(6, 9)));
        assertTrue(n.contains(nmap.getNode(5, 8)));
        assertEquals(3, n.size());
    }
    
    @Test
    public void testGetNeighbours()
    {
        ArrayList<Node> n = nmap.getNeighbours(new Node(0,0));
        assertTrue(n.contains(nmap.getNode(0, 1)));
        assertTrue(n.contains(nmap.getNode(1, 0)));
        assertTrue(n.contains(nmap.getNode(1, 1)));
        assertEquals(3, n.size());
        
        n = nmap.getNeighbours(new Node(1,1));
        assertTrue(n.contains(nmap.getNode(0, 0)));
        assertTrue(n.contains(nmap.getNode(1, 0)));
        assertTrue(n.contains(nmap.getNode(2, 0)));
        assertTrue(n.contains(nmap.getNode(0, 1)));
        //assertTrue(n.contains(nmap.getNode(1, 1)));
        assertTrue(n.contains(nmap.getNode(2, 1)));
        assertTrue(n.contains(nmap.getNode(0, 2)));
        assertTrue(n.contains(nmap.getNode(1, 2)));
        assertTrue(n.contains(nmap.getNode(2, 2)));
        
        assertEquals(8, n.size());
    }
    
    @Test
    public void testGetAllNodes()
    {
        ArrayList<Node> n = nmap.getAllNodes();
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(n.contains(nmap.getNode(i, j)));
            }
        }
        
        assertEquals(100, n.size());
    }
}
