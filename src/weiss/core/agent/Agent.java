/* 
 * Copyright (C) 2017 Adam Young
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package weiss.core.agent;


import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.management.client.Client;
import weiss.management.client.Managable;

/** Class used for sending messages to other Agents,
 * utilising {@link weiss.core.agent.Portal Portal} and {@link weiss.core.agent.Router Router}
 * networks.
 * The class is designed to be assigned to a portal, and then push
 * {@link weiss.core.message.UserMessage User Messages} to other Agents. The class
 * can also receive these messages. Along with support for
 * {@link weiss.management.nodeMonitor.NodeMonitor NodeMonitor} hooks, the class
 * implements a {@link weiss.management.client.Client Client} hook, for sending/displaying
 * messages on a GUI.
 * 
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class Agent extends MetaAgent implements Runnable, Managable
{  
    private Client client;
    
    /**Constructor to generate an Agent class
     *
     * @param name String to set the name of the Agent.
     * @param superAgent Set the Agent's superAgent, in this case a portal.
     * @param client Set the Agent's client window on construction.
     */
    public Agent(String name, MetaAgent superAgent, Client client)
    {
        super(name, superAgent);
        
        this.client = client;
    }
    
    /**Constructor to generate an Agent class.
     *
     * @param name String to set the name of the Agent.
     * @param superAgent Set the Agent's superAgent, in this case a portal.
     */
    public Agent(String name, MetaAgent superAgent)
    {
        super(name, superAgent);
    }

    /** Method to handle a UserMessage, by updating the 
     * {@link weiss.management.nodeMonitor.NodeMonitor NodeMonitor} and
     * {@link weiss.management.client.Client Client}, if present.
     *
     * @param msg A UserMessage passed from the message handler.
     */
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        this.updateNodeMonitor(msg);
        this.updateClient(msg);
    }
    //--------------------------------------------------------------------------
    //INTERFACE METHODS
    //--------------------------------------------------------------------------
    
    /**Method to set the client hook to an active client.
     * 
     * @param client A client object, typically a GUI.
     */
    @Override
    public void addClient(Client client)
    {
       this.client = client;
    }
    
    /**Method to remove the currently active client, if present.
     *
     */
    @Override
    public void removeClient()
    {
        this.client = null;
    }
    
    /**Method to update the attached client, if present.
     *
     * @param msg A Message object to push to the client hook.
     */
    public void updateClient(Message msg)
    {
        if(client != null)
            client.updateClient(msg);
    }
    
    @Override
    public boolean hasClient()
    {
        return this.client != null;
    }

    
}
