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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam Young
 */
public class MessageTest
{
    
    public MessageTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getFrom method, of class Message.
     */
    @Test
    public void testGetFrom()
    {
        System.out.println("getFrom");
        Message instance = null;
        String expResult = "";
        String result = instance.getFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTo method, of class Message.
     */
    @Test
    public void testGetTo()
    {
        System.out.println("getTo");
        Message instance = null;
        String expResult = "";
        String result = instance.getTo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMsg method, of class Message.
     */
    @Test
    public void testGetMsg()
    {
        System.out.println("getMsg");
        Message instance = null;
        String expResult = "";
        String result = instance.getMsg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTime method, of class Message.
     */
    @Test
    public void testGetTime()
    {
        System.out.println("getTime");
        Message instance = null;
        String expResult = "";
        String result = instance.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Message.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Message instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MessageImpl extends Message
    {

        public MessageImpl()
        {
            super("", "", "");
        }
    }
    
}
