/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.communications;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author hexvaara
 */
public class CommunicationBus {
    
    private BlockingQueue x, y, c;
    
    public CommunicationBus()
    {
        this.x = new ArrayBlockingQueue<>(1);
        this.y = new ArrayBlockingQueue<>(1);
        this.c = new ArrayBlockingQueue<>(1);
    }
    
    public void setNewPixel(int x, int y, int c) throws InterruptedException
    {
        this.x.put(x);
        this.y.put(y);
        this.c.put(c);
        //System.out.println("was set");
        
        //doNotify();
        System.out.println("donotify");
    }
    
    public int x() throws InterruptedException
    {
        return (int) x.take();
    }
    
    public int y() throws InterruptedException
    {
        return (int) y.take();
    }
    
    public int c() throws InterruptedException
    {
        return (int) c.take();
    }
    
    /*
    public void doWait()
    {
        synchronized(this)
        {
            try
            {
                this.wait();
            } catch (InterruptedException e) {}
        }
    }
    */
    
    public void doNotify()
    {
        synchronized(this)
        {
            try
            {
                this.notifyAll();
            } catch (Exception e) {}
        }
    }
    
}
