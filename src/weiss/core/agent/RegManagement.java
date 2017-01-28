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

import java.util.ArrayList;

/**
 *
 * @author Adam Young
 */
public class RegManagement
{

    private static volatile ArrayList<String> registeredNames = new ArrayList();
    
    /**
     * Recursive method to set the name of the MetaAgent. Checks an ArrayList of
     * registered names to make sure no name is written twice.
     *
     * @param name The name of the MetaAgent.
     * @return
     */
    public static String setName(String name)
    {
        if (registeredNames.contains(name))
            return setName(name, 1);
        else
            registeredNames.add(name);
        
        return name;
    }

    /**
     * Recursive method to set the name of the MetaAgent. Checks an ArrayList of
     * registered names to make sure no name is written twice.
     *
     * @param name The name of the MetaAgent.
     * @param value The number that appears after the agent.
     */
    private static String setName(String name, int val)
    {
        if (registeredNames.contains(name + " (" + val + ")"))
            return setName(name, val + 1);
        
        System.out.println("Renamed to " + name + " (" + val + ")");
        return setName(name + " (" + val + ")");
    }
}
