package microunit.testrunner;

import microunit.InvalidTestClassException;
import microunit.Test;
import microunit.accumulator.CountingTestResultAccumulator;
import microunit.accumulator.TestResultAccumulator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base class for classes for running unit tests.
 */
public abstract class TestRunner {

    protected Class<?> testClass;

    /**
     * Creates a {@code TestRunner} object for executing the test methods of
     * the class specified.
     *
     * @param testClass the class whose test methods will be executed
     */
    protected TestRunner(Class<?> testClass) {
        this.testClass = testClass;
    }

    /**
     * Returns the list of methods of the class marked with the annotation
     * specified.
     *
     * @param annotationClass a {@link Class} object representing an annotation
     *                        type
     * @return the list of methods of the class marked with the annotation
     *         specified
     */
    protected List<Method> getAnnotatedMethods(Class<? extends Annotation> annotationClass) {
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }

    /**
     * Runs the test methods of the class.
     */
    public void runTestMethods() {
        try {
            TestResultAccumulator accumulator = new CountingTestResultAccumulator();
            for (Method method : getAnnotatedMethods(Test.class)) {
                System.out.println(method);
                Object instance = testClass.getConstructor().newInstance();
                invokeTestMethod(method, instance, accumulator);
            }
            System.out.println(accumulator);
        } catch (ReflectiveOperationException | IllegalArgumentException e) {
            throw new InvalidTestClassException(e);
        }
    }

    /**
     * Invokes a test method on a test class instance accumulating the result of
     * the invocation into a {@link TestResultAccumulator}.
     *
     * @param testMethod the test method to be invoked
     * @param instance the test class instance on which the method is invoked
     * @param accumulator the object to accumulate the result of the invocation
     *                    into
     */
    protected abstract void invokeTestMethod(Method testMethod, Object instance, TestResultAccumulator accumulator) throws IllegalAccessException;

}
