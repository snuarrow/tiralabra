/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import tiralabra.logic.structures.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hexvaara
 */
public class NodeTest {
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of compareSquareDistanceOfNodes method, of class Node.
     */
    @Test
    public void testInitNodeDefault() {
        Node a = new Node(0,0,0,null, 0);
        assertEquals(a.x, 0);
        assertEquals(a.y, 0);
        assertEquals(a.color, 0);
        assertEquals(a.previous, null);
    }
    @Test
    public void testInitNodeOverloaded()
    {
        Node a = new Node(0,0);
        assertEquals(a.x, 0);
        assertEquals(a.y, 0);
        assertEquals(a.color, 0);
        assertEquals(a.previous, null);
    }
    @Test
    public void testDistance()
    {
        Node a = new Node(0,0);
        double dist = a.distance(new Node(1,1));
        assertEquals(Math.sqrt(2), dist, 0.0001);
        
        assertEquals(1, a.distance(new Node(0,1)), 0.0001);
    }
    @Test
    public void testToString()
    {
        Node a = new Node(0,0);
        assertEquals("x:0 y:0 color:0",a.toString());
    }
    
}
