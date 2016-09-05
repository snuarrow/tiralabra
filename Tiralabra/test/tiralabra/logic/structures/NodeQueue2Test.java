/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic.structures;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hexvaara
 */
public class NodeQueue2Test {

    private Node a;
    private Node b;
    private Node c;
    private Node d;
    private Node e;
    private NodeQueue2 q2;
    private NodeQueue q1;
    
    
    public NodeQueue2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        q2 = new NodeQueue2();
        q1 = new NodeQueue();
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
     * Test of getContent method, of class NodeQueue2.
     */
    
    /*
    @Test
    public void testPopEmpty()
    {
        assertEquals(null, q.popFirst());
    }
    */
    
    /*
    @Test
    public void testAddPop() {
        NodeQueue2 q = new NodeQueue2();
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
    */
    
    /*
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
    */
    
    //@Test
    public void testToStringEmptyQueue()
    {
        assertEquals("empty queue", q2.toString());
    }
    //@Test
    public void testToStringOneNode()
    {
        q2.add(a);
        assertEquals("x:1 y:1 color:0\n", q2.toString());
    }
    //@Test
    public void testToStringMultipleNodes()
    {
        q2.add(a);
        q2.add(b);
        q2.add(c);
        assertEquals("x:1 y:1 color:0\nx:2 y:2 color:0\nx:3 y:3 color:0\n", q2.toString());
    }
    @Test
    public void testAdd0()
    {
        assertEquals(0, q2.length());
        assertEquals(0, q1.length());
    }
    @Test
    public void testAdd1()
    {
        q2.add(a);
        assertEquals(1, q2.length());
        q1.add(a);
        assertEquals(q2.length(), q1.length());
    }
    @Test
    public void testAdd2()
    {
        q2.add(a);
        q2.add(b);
        assertEquals(2, q2.length());
        
        q1.add(a);
        q1.add(b);
        
        assertEquals(q1.length(), q2.length());
    }
    @Test
    public void testAdd3()
    {
        q2.add(a);
        q2.add(b);
        q2.add(c);
        assertEquals(3, q2.length());
        q1.add(a);
        q1.add(b);
        q1.add(c);
        assertEquals(q1.length(), q2.length());
    }
    
    @Test
    public void testContains()
    {
        q2.add(a);
        q2.add(b);
        q2.add(c);
        assertTrue(q2.contains(a));
        assertTrue(q2.contains(b));
        assertTrue(q2.contains(c));
        assertFalse(q2.contains(d));
        assertFalse(q2.contains(e));
    }
    
    @Test
    public void testRemove()
    {
        q2.add(a);
        q2.add(b);
        q2.add(c);
        
        q2.remove(a);
        
        assertFalse(q2.contains(a));
        q2.remove(b);
        
        assertFalse(q2.contains(b));
        q2.remove(c);
        
        assertFalse(q2.contains(c));
    }
    
    @Test
    public void testIsEmpty()
    {
        assertTrue(q2.isEmpty());
        
        q2.add(a);
        q2.add(b);
        
        assertFalse(q2.isEmpty());
        //assertEquals(2, q.length());
        q2.remove(a);
        assertFalse(q2.isEmpty());
        //assertEquals(1, q.length());
        q2.remove(b);
        assertTrue(q2.isEmpty());
        //assertEquals(0, q.length());
    }
    
    @Test
    public void testLength()
    {
        assertEquals(0, q2.length());
        q2.add(a);
        assertEquals(1, q2.length());
        q2.add(b);
        assertEquals(2, q2.length());
        q2.remove(a);
        assertEquals(1, q2.length());
        q2.remove(b);
        assertEquals(0, q2.length());
        
    }
    
    @Test
    public void testGetContent()
    {
        NodeQueue n0 = new NodeQueue();
        NodeQueue2 n1 = new NodeQueue2();
        
        n0.add(a);
        n0.add(b);
        n0.add(c);
        n0.add(d);
        n0.add(e);
        
        n1.add(a);
        n1.add(b);
        n1.add(c);
        n1.add(d);
        n1.add(e);
        
        Assert.assertArrayEquals(n0.getContent(), n1.getContent());
        
    }
    
    @Test
    public void bigScaleTest()
    {
        ArrayList<Node> container = new ArrayList<>();
        
        for (int i = 0; i < 8000; i++) {
            Node n = new Node(i,1,0);
            q2.add(n);
            container.add(n);
        }
        
        for (Node container1 : container) {
            assertTrue(q2.contains(container1));
        }
        
        
        for (int i = 0; i < 8000; i++) {
            assertEquals(q2.get(i), container.get(i));
        }
        
        
        
        for (Node n : q2.getContent())
        {
            if (container.contains(n)) container.remove(n);
        }
        
        assertEquals(0, container.size());
    }
    
    @Test
    public void testGetRemoveAdd()
    {
        ArrayList<Node> container = new ArrayList<>();
        
        for (int i = 0; i < 8000; i++) {
            Node n = new Node(i,1,0);
            q2.add(n);
            container.add(n);
        }
        
        
        for (int i = 0; i < 8000; i++) {
            assertEquals(q2.get(i), container.get(i));
        }
        
        
        
        
        
        HashSet<Node> removed = new HashSet<>();
        for (int i = 0; i < q2.size(); i += 100) {
            removed.add(q2.get(i));
            q2.remove(i);
        }
        
        for (int i = 0; i < q2.size(); i++) {
            assertFalse(removed.contains(q2.get(i)));
        }
        
        for (int i = 0; i < q2.size(); i++) {
            container.remove(q2.get(i));
        }
        
        assertEquals(container.size(), removed.size());
    }
    
    @Test
    public void testGetNode()
    {
        ArrayList<Node> container = new ArrayList<>();
        
        for (int i = 0; i < 8000; i++) {
            Node n = new Node(i,1,0);
            q2.add(n);
            container.add(n);
        }
        
        for (Node container1 : container) {
            q2.remove(container1);
        }
        
        assertEquals(q2.size(), 0);
    }
    
    @Test
    public void testGetNeighbours()
    {
        ArrayList<Node> container = new ArrayList<>();
        
        for (int i = 0; i < 8000; i++) {
            Node n = new Node(i,1,0);
            q2.add(n);
            container.add(n);
        }
        
        int count = 0;
        while (!q2.isEmpty()) 
        {
            System.out.println(count++);
            //assertNotNull(q2.get(0));
            q2.remove(0);
        }
    }
    
    
}
