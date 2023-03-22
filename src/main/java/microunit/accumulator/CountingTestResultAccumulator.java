package microunit.accumulator;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents an object accumulating the results of test method executions. The
 * implementation is thread-safe.
 */
public class CountingTestResultAccumulator implements TestResultAccumulator {

    private final AtomicInteger numberOfErrors;
    private final AtomicInteger numberOfFailures;
    private final AtomicInteger numberOfSuccesses;

    public CountingTestResultAccumulator() {
        this.numberOfErrors = new AtomicInteger();
        this.numberOfFailures = new AtomicInteger();
        this.numberOfSuccesses = new AtomicInteger();
    }

    @Override
    public void onError(Method method) {
        numberOfErrors.incrementAndGet();
    }

    @Override
    public void onFailure(Method method) {
        numberOfFailures.incrementAndGet();
    }

    @Override
    public void onSuccess(Method method) {
        numberOfSuccesses.incrementAndGet();
    }

    public String toString() {
        return String.format("""
            Tests run: %d
            Errors: %d
            Failures: %d""", numberOfSuccesses.get() + numberOfErrors.get() + numberOfFailures.get(), numberOfErrors.get(), numberOfFailures.get());
    }

}