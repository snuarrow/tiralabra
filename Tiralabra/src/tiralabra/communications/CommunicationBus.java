package tiralabra.communications;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Communication bus between concurrent logic and GUI threads.
 * sorry, not in use.
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
        
        doNotify();
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
