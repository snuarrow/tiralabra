/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author hexvaara
 */
public class HexPriorityQueue {
    
    private Class type;
    private Object methodClass;
    private Method comparator;
    private Object[] memory;
    private Object goal;
    
    public HexPriorityQueue(Class type, Object methodClass, Method comparator) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        this.type = type;
        this.methodClass = methodClass;
        this.comparator = comparator;
        
        System.out.println(type);
        //memory = new Object[3];
        //memory[1] = 5;
        //memory[2] = "asd";
        
        //Object[] parameters = new Object[2];
        //parameters[0] = 5;
        //parameters[1] = 2;
        
        //int a = (int)comparator.invoke(methodClass, parameters);
        
        Node a = new Node(0,0);
        Node b = new Node(1,1);
        Node goal = new Node(3,3);
        
        System.out.println(compare(a,b,goal));
        //System.out.println(memory[2].getClass() == "".getClass());
    }
    
    public HexPriorityQueue(Class type, Object goal)
    {
        this.type = type;
        this.goal = goal;
        init();
    }
    
    private void init()
    {
        memory = new Object[1];
    }
    
    private void enlarge()
    {
        int size = memory.length;
        
        Object[] temp = new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = memory[i];
        }
        memory = new Object[size+1];
        
        for (int i = 0; i < size; i++) {
            memory[i] = temp[i];
        }
    }
    public boolean add(Object input)
    {
        if (input.getClass() != type) return false;
        if (memory.length == 1) memory[0] = input;
        else
        {
            enlarge();
            memory[memory.length-1] = input;
        }
                
        return true;
    }
    
    // tää bubble ei toimi viel, heitä roskii ja tee vaa nodejono
    public Object pop() throws IllegalAccessException, InvocationTargetException
    {
        if (memory.length == 1) return memory[0];
        
        Object best = memory[0];
                
        if (type == Node.class)
        {
            for (int i = 1; i < memory.length; i++) {
                for (int j = 1; j < memory.length; j++) {
                    best = compare(best, memory[j], goal);
                }
                best = compare(best, memory[i], goal);
            }
        } else return null;
        
        return best;
    }
    
    
    
    private Object compare(Object a, Object b) throws IllegalAccessException, InvocationTargetException
    {
        Object[] parameters = {a,b};
        return comparator.invoke(methodClass, parameters);
    }
    private Object compare(Object a, Object b, Object c) throws IllegalAccessException, InvocationTargetException
    {
        Object[] parameters = {a,b,c};
        return comparator.invoke(methodClass, parameters);
    }
    
}
