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
public class HexMap2Test {
    
    public HexMap2Test() {
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

    @Test public void testTemp()
    {
        
    }
       
    
    /*
    @Test
    public void testPutContainsGetNodeNode() {
        Node key = new Node(0,0);
        Object value = new Node(1,1);
        HexMap2 instance = new HexMap2();
        instance.put(key, value);
        
        assertTrue(instance.containsKey(key));
        assertEquals(instance.get(key), value);
        
        Node key2 = new Node(2,2);
        Object value2 = new Node(3,3);
        
        instance.put(key2, value2);
        
        assertTrue(instance.containsKey(key2));
        assertEquals(instance.get(key2), value2);
    }

    @Test
    public void testPutContainsGetNodeDouble() {
        Node key = new Node(0,0);
        Object value = 1.1;
        HexMap instance = new HexMap();
        instance.put(key, value);
        
        assertTrue(instance.containsKey(key));
        assertEquals((Double)instance.get(key), 1.1, 0.001);
        
        Node key2 = new Node(2,2);
        Object value2 = 1.2;
        
        instance.put(key2, value2);
        
        assertTrue(instance.containsKey(key2));
        assertEquals((Double)instance.get(key2), 1.2, 0.001);
    }
    */
}
