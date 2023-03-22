package example;

import static microunit.Assert.*;

import microunit.testrunner.ExpectedExceptionHandlingTestRunner;
import microunit.Test;

import java.io.IOException;

// CHECKSTYLE:OFF
public class ExampleTest {

    public ExampleTest() {
    }

    @Test
    public void a() {
        assertTrue(1 + 1 == 2, "This should always be true");
    } // success

    @Test
    public void b() {
        assertTrue(1 + 1 == 0, "This should never be true");
    } // failure

    @Test(expected = IOException.class)
    public void c() throws Exception {
    } // failure

    @Test(expected = IOException.class)
    public void d() throws Exception {
        throw new IOException("An I/O exception occurred");
    } // success

    @Test(expected = IOException.class)
    public void e() {
        throw new RuntimeException("Oops!");
    } // error

    public void f() {
    }

    public static void main(String[] args) throws Exception {
        new ExpectedExceptionHandlingTestRunner(ExampleTest.class).runTestMethods();
    }

}
