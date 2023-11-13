package com.allen.dayup.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Auther: allen
 * @Date: 2019-12-15 16:52
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GuavaDemoTest {
    private GuavaDemo guavaDemo;

    @Before
    public void setUp() throws Exception {
        guavaDemo = new GuavaDemo();
    }

    @After
    public void tearDown() throws Exception {
        guavaDemo = null;
    }

    @Test
    public void testCheckArg(){
        try {
            guavaDemo.checkArg(null);
            fail("user argments is error!");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
    }

    @Test
    public void testCheckArgNotNull(){
        try {
            guavaDemo.checkArgNotNull(null);
            fail("user can not be null!");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(true);
        }
    }

    @Test
    public void testOptionalMethodNull(){
        String s = guavaDemo.optionalMethod(null);
        assertEquals("default string", s);
    }

    @Test
    public void testOptionalMethodNotNull(){
        String s = guavaDemo.optionalMethod("Test");
        assertEquals("Test", s);
    }

    @Test
    public void testOptionalMethodSupplierNull(){
        String s = guavaDemo.optionalMethod(null);
        assertEquals("default string", s);
    }

    @Test
    public void testOptionalMethodSupplierNotNull(){
        String s = guavaDemo.optionalMethod("Test");
        assertEquals("Test", s);
    }

    @Test
    public void testOptionalMethodOrnull(){
        String s = guavaDemo.optionalMethodOrnull(null);
        assertNull(s);
    }

    @Test
    public void testOrnullReturnNotNull(){
        String s = guavaDemo.optionalMethodOrnull("Test");
        assertNotNull(s);
    }

    @Test
    public void testOptionalTranformSuccess(){
        String s = guavaDemo.optionalTranform("Test");
        assertEquals("transform string", s);
    }

    @Test
    public void testComparisonChainSuccess(){
        boolean result = guavaDemo.comparisonChain(new User("allen",15), new User("allen", 15));
        assertTrue(result);
    }

    @Test
    public void testComparisonChainFail(){
        boolean result = guavaDemo.comparisonChain(new User("allen",14), new User("allen", 15));
        assertFalse(result);
    }
}
