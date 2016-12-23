
package weiss;

import java.util.HashMap;
import weiss.Message.Message;

/** Class used for handling messages from {@link weiss.Portal Portal} classes, and routing them to the correct
 * destination. Routers are linked together in a pseudo linked list.
 * <p>
 * The Router wraps the original message with its own name, and checks each message it receives for that name.
 * <p>
 * If the name on the message doesn't match the routers name, it checks it's {@link Router#routingTable Routing Table} for the value in the {@link weiss.Message.Message#to To} field.
 * If found, the message is passed to the selected object, if not, it is passed to the next router.
 * <p>
 * If the name does match that of the router, a reply is send to the value in the {@link weiss.Message.Message#from From} field, saying that
 * the message couldn't be sent.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class Router extends MetaAgent
{
    /**
     * HashMap holding references to MetaAgents stored in connected portals.
     */
    private HashMap routingTable = new HashMap();
    
    /**
     * Constructor for the Router class
     * @param n String for the name variable.
     * @param p String pointing to the next Router in the chain.
     */
    public Router(String n, MetaAgent p)
    {
        super(n, p);
    }

    @Override
    public void msgHandler(Message msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
