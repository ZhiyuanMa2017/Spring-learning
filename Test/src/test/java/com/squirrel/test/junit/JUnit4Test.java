package com.squirrel.test.junit;

import com.squirrel.test.util.Calculation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnit4Test {

    Calculation calculation = new Calculation();
    int result;

    @Test
    public void testAdd() {
        System.out.println("Start");
        result = calculation.add(1, 2);
        assertEquals(3, result);
        System.out.println("End");
    }

    @Test(timeout = 1, expected = NullPointerException.class)
    public void testSub() {
        System.out.println("Start");
        result = calculation.sub(1, 2);
        assertEquals(-1, result);
        throw new NullPointerException();
    }

    @BeforeClass
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @AfterClass
    public static void afterAll() {
        System.out.println("AfterClass");
    }

    @Before
    public void beforeEvery() {
        System.out.println("before");
    }

    @After
    public void afterEvery() {
        System.out.println("After");
    }

}
