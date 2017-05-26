package com.igorjsantos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test.
 */
public class ApplicationTest {

    @BeforeClass
    public static void setup() throws FileNotFoundException {
        // TODO
    }

    @Test
    public void expect_parameter_false() throws Exception {
        assertFalse(false);
    }

    @Test
    public void expect_parameter_true() throws Exception {
        assertTrue(true);
    }

    @Test
    public void expect_parameter_null() throws Exception {
        assertNull(null);
    }

}
