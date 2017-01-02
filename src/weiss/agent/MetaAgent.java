package weiss.agent;

import java.util.HashMap;
import java.util.Map;
import weiss.message.Message;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be implemented
 * by the end user.
 * This implementation of a meta agent extends 
 * a <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 * 
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends LinkedBlockingQueue implements Runnable
{
    private final Thread t;
    private boolean shouldStop;
    
    private String name;
    protected MetaAgent superAgent;
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    protected Map<String, NodeMonitor> nodeMonitorMap; 
    
    
    
    /**
     * Constructor to initialise a MetaAgetn object.
     * @param name {@link weiss.agent.Portal Portal} belonging to MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        this.nodeMonitorMap = new HashMap<>();
        this.name = name;
        this.superAgent = superAgent;
        this.scope = 0;
        this.shouldStop = false;
        
        t = new Thread(this);
    }
    /**
     * Constructor to initialise a MetaAgent object, and setting the scope.
     * @param name Name of MetaAgent.
     * @param superAgent {@link weiss.agent.Portal Portal} belonging to MetaAgent.
     * @param scope Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent, int scope)
    {
        this.nodeMonitorMap = new HashMap<>();
        this.name = name;
        this.superAgent = superAgent;
        this.scope = scope;
        this.shouldStop = false;
        
        t = new Thread(this);
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    /**
     * Getter for name variable.
     * @return name String.
     */
    public String getName()
    {
        return name;
    }
    /**
     * Getter for {@link weiss.agent.Portal Portal} object.
     * @return {@link weiss.agent.Portal Portal} pointer.
     */
    public MetaAgent getPortal()
    {
        return superAgent;
    }
    
    /**
     * Method to set scope of MetaAgent
     * @return Integer relating to the scope of the MetaAgent
     */
    public int getScope()
    {
        return scope;
    }
    //--------------------------------------------------------------------------
    //SETTERS
    /**
     * Setter for name variable.
     * @param n name String.
     */
    public final void setName(String n)
    {
        name = n;
        //Validity checks present in superAgent
    }
    /**
     * Setter for {@link weiss.agent.Portal Portal} object.
     * @param superAgent {@link weiss.agent.Portal Portal} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {   
            this.superAgent = superAgent;

        //need to change scope
    }
    
    /**
     * Method to set scope of MetaAgent
     * @param scope Integer relating to the scope of the MetaAgent
     */
    public final void setScope(int scope)
    {
        this.scope = scope;
        
        //need to change registration and scope
    }
    /**
     * Method to send message.
     * @param message Message object.
     */
    public final void sendMessage(Message message)
    {
        superAgent.msgHandler(message);
    }
    /**
     * Method to handle incoming messages.
     * @param msg Message object.
     */
    
    public void updateNodeMonitor(Message msg)
    {
        for(String key : this.nodeMonitorMap.keySet())
        {
            try
            {
                nodeMonitorMap.get(key).put(msg);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public abstract void msgHandler(Message msg);    

    
    public void start()
    {
        t.start();
    }
    
    public void stop()
    {
        shouldStop = true;
    }
    
    @Override
    public void run()
    {
        while(!shouldStop)
        {
            try
            {
                Message msg = (Message) this.take();
                msgHandler(msg);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
}