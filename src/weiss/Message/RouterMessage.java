/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.Message;

/**
 *
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class RouterMessage extends DecoratedMessage
{
    String origin;
    
    RouterMessage(String t, String f, String m, Message c, String o)
    {
        super(t, f, m, c);
        origin = o;
    }
    
    //--------------------------------------------------------------------------
    //Getters
    //--------------------------------------------------------------------------
    public String getOrigin()
    {
        return origin;
    }
    
}