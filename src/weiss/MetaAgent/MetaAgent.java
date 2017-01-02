package weiss.MetaAgent;

<<<<<<< HEAD:src/weiss/MetaAgent/MetaAgent.java
import Weiss.Manager.NodeMonitor;
import java.util.ArrayList;
import weiss.message.Message;
=======
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
>>>>>>> refs/remotes/origin/master:src/weiss/agent/MetaAgent.java
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.*;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be implemented
 * by the end user.
 * This implementation of a meta agent extends 
 * a <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 * 
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends WeissBase implements Runnable, Monitorable
{
    
    private String name;    
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    private ArrayList<NodeMonitor> nodeMonitors;
    protected NodeMonitor client;
    protected MetaAgent superAgent;
    
    /**
     * Constructor to initialise a MetaAgetn object.
     * @param name {@link weiss.MetaAgent.Portal Portal} belonging to MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        super();
        this.nodeMonitors = new ArrayList();
        this.client = null;
        this.name = name;
        this.superAgent = superAgent;
        this.scope = 0;
        
    }
    /**
     * Constructor to initialise a MetaAgent object, and setting the scope.
     * @param name Name of MetaAgent.
     * @param superAgent {@link weiss.MetaAgent.Portal Portal} belonging to MetaAgent.
     * @param scope Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent, int scope)
    {
        super();
        
        this.nodeMonitors = new ArrayList();
        this.client = null;
        this.name = name;
        this.superAgent = superAgent;
        this.scope = scope;        
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    //--------------------------------------------------------------------------
    /**
     * Getter for name variable.
     * @return name String.
     */
    public String getName()
    {
        return name;
    }
    /**
     * Getter for {@link weiss.MetaAgent.Portal Portal} object.
     * @return {@link weiss.MetaAgent.Portal Portal} pointer.
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
    //--------------------------------------------------------------------------
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
     * Setter for {@link weiss.MetaAgent.Portal Portal} object.
     * @param superAgent {@link weiss.MetaAgent.Portal Portal} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {   
        this.superAgent = superAgent;
        try
        {
            if(this.superAgent != null)
                this.superAgent.put(new SysMessage(this.getName(), this.superAgent.getName(),
                    "reg", this));
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    //--------------------------------------------------------------------------
    //INTERFACE METHODS
    @Override
    public void addNodeMonitor(NodeMonitor nodeMonitor)
    {
        this.nodeMonitors.add(nodeMonitor);
    }
    @Override
    public void removeNodeMonitor(NodeMonitor nodeMonitor)
    {
        this.nodeMonitors.remove(nodeMonitor);
    }
    public void updateNodeMonitor(Message msg)
    {
        for(NodeMonitor node : nodeMonitors)
        {
            try
            {
                node.put(msg);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    public void addClient(NodeMonitor client)
    {
        this.client = client;
    }
    @Override
    public void removeClient(NodeMonitor client)
    {
        this.client = null;
    }
    public void updateClient(Message msg)
    {
        try
        {
            client.put(msg);
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public boolean hasClient()
    {
        return this.client != null;
    }
    @Override
    public boolean hasMonitor()
    {
        return !nodeMonitors.isEmpty();
    }
    
    //--------------------------------------------------------------------------
    //CLASS SPECIFIC METHODS
    @Override
    protected void msgHandler(Message msg)
    {
        this.updateNodeMonitor(msg);
        String to = msg.getTo();   //puts the address of the message into a local variable
        String from = msg.getFrom();

        if ((to.equals(this.getName()) && (msg instanceof SysMessage)))
            this.sysMsgHandler((SysMessage) msg); //it gets sent to the handler specifically for SysMessages
        else if((to.equals(this.getName()) && (msg instanceof RouterMessage)))
            this.RouterMsgHandler((RouterMessage) msg);//gets sent to the handler specifically for RouterMessages
        else
            this.userMsgHandler((UserMessage) msg);
    }

    public void sendMessage(String to, String message)
    {
        try
        {
            superAgent.put(new UserMessage(this.getName(), to, message));
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    

}
