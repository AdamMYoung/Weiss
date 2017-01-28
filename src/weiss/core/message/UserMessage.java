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
package weiss.core.message;

/**
 * Class extending the {@link Message Message} class, to be used for user to
 * user message transmission. This type of message is to be read by user
 * implemented classes, rather than the middleware infrastructure.
 * <p>
 * The alternative of this message type is {@link SysMessage SysMessage}.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class UserMessage extends Message
{

    private final int scope; // 0: Global, 1: Router-wide, 2:Portal-wide
    private final String message;

    /**
     * Constructor for the UserMessage class.
     *
     * @param from String of message sender.
     * @param to String of message receiver.
     * @param message String of message contents.
     */
    public UserMessage(String from, String to, String message)
    {
        super(from, to);
        this.message = message;
        scope = 0;
    }

    /**
     * Constructor for the UserMessage class.
     *
     * @param from String of message sender.
     * @param to String of message receiver.
     * @param message String of message contents.
     * @param scope Depth that the message can travel.
     */
    public UserMessage(String from, String to, String message, int scope)
    {
        super(from, to);
        this.message = message;
        this.scope = scope;
    }

    /**
     * Getter for the message variable.
     *
     * @return String of message variable.
     */
    public String getMsg()
    {
        return message;
    }
    
    /**
     * Method to get the scope value from the message.
     * @return An Integer detailing the scope level.
     */
    public int getScope()
    {
        return scope;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\nMessage: " + message + "\n";
    }
}
