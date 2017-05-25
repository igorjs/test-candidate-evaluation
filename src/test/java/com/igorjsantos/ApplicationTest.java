package com.igorjsantos;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Unit test.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {

    @SuppressWarnings("unchecked")
    private static final Class<? extends Exception>[] notAcceptableExceptions = new Class[] { ArrayIndexOutOfBoundsException.class, NullPointerException.class, ParseException.class };

    private void exceptionNotAcceptable(final Object param) {
        for (final Class<? extends Exception> notAcceptableException : notAcceptableExceptions)
            exceptionNotAcceptable(param, notAcceptableException);
    }

    private void exceptionNotAcceptable(final Object param, final Class<? extends Exception> notAcceptableException) {
        Exception throwed = null;

        try {
            // TODO
        }
        catch (final Exception e) {
            throwed = e;

            if (e.getClass().equals(notAcceptableException))
                Assert.fail();
        }
        finally {
            log(throwed.getClass().getSimpleName(), notAcceptableException.getSimpleName());
        }
    }

    private void log(final String throwedException, final String notAcceptableException) {
        log(String.format("[exception disparada: %s][exception nao aceita: %s]", throwedException, notAcceptableException));
    }

    private void log(final String message) {
        System.out.println(message);
    }

    @BeforeClass
    public static void setup() throws FileNotFoundException {
        // TODO
    }

    @Test
    public void parameter_false() throws Exception {
        assertTrue(true);
    }

    @Ignore
    @Test
    public void parameter_null() throws Exception {
        assertTrue(true);
    }

}
