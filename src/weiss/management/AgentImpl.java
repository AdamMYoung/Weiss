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
package weiss.management;

import weiss.core.agent.*;
import weiss.core.message.UserMessage;
import weiss.management.client.Client;
import weiss.management.client.Managable;

/**
 * Class used for sending messages to other Agents, utilising
 * {@link weiss.core.agent.Portal Portal} and
 * {@link weiss.core.agent.Router Router} networks. The class is designed to be
 * assigned to a portal, and then push
 * {@link weiss.core.message.UserMessage User Messages} to other Agents. The
 * class can also receive these messages. Along with support for
 * {@link weiss.management.nodeMonitor.NodeMonitor NodeMonitor} hooks, the class
 * implements a {@link weiss.management.client.Client Client} hook, for
 * sending/displaying messages on a GUI.
 *
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class AgentImpl extends MetaAgent implements Managable      
{
    public enum AgentScope  {GLOBAL, ROUTER, PORTAL}
    
    private Client client;
    private final int scope; // 0: Global, 1: Router-wide, 2:Portal-wide

    /**
     * Constructor to generate an Agent class.
     *
     * @param name String to set the name of the Agent.
     * @param superAgent Set the Agent's superAgent, in this case a portal.
     */
    public AgentImpl(String name, MetaAgent superAgent)
    { 
        super(name, superAgent);

        scope = 0;
    }

    /**
     * Constructor to generate an Agent class.
     *
     * @param name String to set the name of the Agent.
     * @param superAgent Set the Agent's superAgent, in this case a portal.
     * @param scope Range that the agent can message. 0 = Global, 1 =
     * Router-wide, 2 = Portal-wide.
     */
    public AgentImpl(String name, MetaAgent superAgent, int scope)
    {
        super(name, superAgent);
        
        this.scope = scope;
    }

    /**
     * Method to handle a UserMessage.
     * @param msg A UserMessage passed from the message handler.
     */
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        client.updateClient(msg);
    }

    /**
     * Method to send messages.
     *
     * @param to the recipient of the message.
     * @param message the message to be sent.
     */
    public void sendMessage(String to, String message)
    {
        if (to != null && message != null)
        {
            pushToSuperAgent(new UserMessage(this.getName(), to, message, scope));
        }
        else
        {
            //Throw error
        }
    }
    /**
     * Method to get the scope value from the Agent.
     * @return An Integer detailing the scope level.
     */
    public int getScope()
    {
        return scope;
    }
    
    @Override
    public void addClient(Client client)
    {
        if(client != null)
            this.client = client;
    }

    @Override
    public void removeClient()
    {
        this.client = null;
    }

    @Override
    public boolean hasClient()
    {
        return client != null;
    }
    
    @Override
    public void updateClient(UserMessage msg)
    {
        if(client != null)
            client.updateClient(msg);
    }
}
