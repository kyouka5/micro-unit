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
    public void test_alwaysTrueCondition_shouldReturnTrue() {
        assertTrue(1 + 1 == 2, "This should always be true");
    } // success

    @Test
    public void test_parseInt_shouldThrowException_whenParameterIsInvalid() {
        Integer.parseInt("Hello, world!");
    } // error

    @Test
    public void test_alwaysFalseCondition_shouldReturnFalse() {
        assertTrue(1 + 1 == 0, "This should never be true");
    } // failure

    @Test(expected = IOException.class)
    public void test_emptyTestMethod_shouldThrowException() throws Exception {
    } // failure

    @Test(expected = IOException.class)
    public void test_expectedIOException_shouldThrowException() throws Exception {
        throw new IOException("An I/O exception occurred");
    } // success

    @Test(expected = IOException.class)
    public void test_differentException_shouldThrowException() {
        throw new RuntimeException("Oops!");
    } // error

    public void test_nonTestMethod_shouldNotBeExecuted() {
    }

    public static void main(String[] args) throws Exception {
        new ExpectedExceptionHandlingTestRunner(ExampleTest.class).runTestMethods();
    }

}
