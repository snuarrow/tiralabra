/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.logic.Node;

/**
 *
 * @author hexvaara
 */
public class NodeQueueTest {
    
    private Node a;
    private Node b;
    private Node c;
    private Node d;
    private Node e;
    private NodeQueue q;
    
    public NodeQueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        q = new NodeQueue();
        a = new Node(1,1);
        b = new Node(2,2);
        c = new Node(3,3);
        d = new Node(4,4);
        e = new Node(5,5);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class NodeQueue.
     */
    
    @Test
    public void testPopEmpty()
    {
        assertEquals(null, q.popFirst());
    }
    
    @Test
    public void testAddPop() {
        NodeQueue q = new NodeQueue();
        Node a = new Node(0,0);
        Node b = new Node(1,1);
        Node c = new Node(2,2);
        Node d = new Node(3,3);
        Node e = new Node(4,4);
        
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);
        q.add(e);
        
        //System.out.println(q.toString());
        
        assertEquals(a, q.popFirst());
        assertEquals(b, q.popFirst());
        assertEquals(c, q.popFirst());
        
        q.add(a);
        q.add(b);
        
        assertEquals(d, q.popFirst());
        assertEquals(e, q.popFirst());
        assertEquals(a, q.popFirst());
        assertEquals(b, q.popFirst());
        assertEquals(null, q.popFirst());
    }
    @Test
    public void testPopClosest()
    {
        Node x = new Node(0,0);
        Node y = new Node(0,1);
        Node z = new Node(1,1);
        Node goal = new Node(0,2);
        
        q.add(x);
        q.add(y);
        q.add(z);
        
        assertEquals(y, q.popClosest(goal));
        assertEquals(z, q.popClosest(goal));
        
        q.add(y);
        
        assertEquals(y, q.popClosest(goal));
        assertEquals(x, q.popClosest(goal));
        assertEquals(null, q.popClosest(goal));
    }
    
    @Test
    public void testToStringEmptyQueue()
    {
        assertEquals("empty queue", q.toString());
    }
    @Test
    public void testToStringOneNode()
    {
        q.add(a);
        assertEquals("x:1 y:1 color:0\n", q.toString());
    }
    @Test
    public void testToStringMultipleNodes()
    {
        q.add(a);
        q.add(b);
        q.add(c);
        assertEquals("x:1 y:1 color:0\nx:2 y:2 color:0\nx:3 y:3 color:0\n", q.toString());
    }
    @Test
    public void testAdd0()
    {
        assertEquals(0, q.length());
    }
    @Test
    public void testAdd1()
    {
        q.add(a);
        assertEquals(1, q.length());
    }
    @Test
    public void testAdd2()
    {
        q.add(a);
        q.add(b);
        assertEquals(2, q.length());
    }
    @Test
    public void testAdd3()
    {
        q.add(a);
        q.add(b);
        q.add(c);
        assertEquals(3, q.length());
    }
    
    @Test
    public void testContains()
    {
        q.add(a);
        q.add(b);
        q.add(c);
        assertTrue(q.contains(a));
        assertTrue(q.contains(b));
        assertTrue(q.contains(c));
        assertFalse(q.contains(d));
        assertFalse(q.contains(e));
    }
    
    @Test
    public void testRemove()
    {
        q.add(a);
        q.add(b);
        q.add(c);
        
        q.remove(a);
        
        assertFalse(q.contains(a));
        q.remove(b);
        
        assertFalse(q.contains(b));
        q.remove(c);
        
        assertFalse(q.contains(c));
    }
    
    @Test
    public void testIsEmpty()
    {
        assertTrue(q.isEmpty());
        
        q.add(a);
        q.add(b);
        
        assertFalse(q.isEmpty());
        q.remove(a);
        assertFalse(q.isEmpty());
        q.remove(b);
        assertTrue(q.isEmpty());
    }
    
    @Test
    public void testLength()
    {
        assertEquals(0, q.length());
        q.add(a);
        assertEquals(1, q.length());
        q.add(b);
        assertEquals(2, q.length());
        q.remove(a);
        assertEquals(1, q.length());
        q.remove(b);
        assertEquals(0, q.length());
        
    }
    
}
