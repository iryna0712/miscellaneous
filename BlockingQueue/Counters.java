
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/** Class-container, created to avoid duplication in code, when method needs to use
 *  different types of counters, that do not have same interface. So we unified it;
 */
public class Counters {

    public interface Counter {
        void increment();
        int get();
    }

    public static class LongAdderCounter implements Counter {
        private LongAdder adder;

        public LongAdderCounter() {
            adder = new LongAdder();
        }

        public void increment() {
            adder.increment();
        }

        public int get() {
           return adder.intValue();
        }
    }

    public static class AtomicIntegerCounter implements Counter {
        private AtomicInteger atomicInteger;

        public AtomicIntegerCounter() {
            atomicInteger = new AtomicInteger();
        }

        public void increment() {
            atomicInteger.incrementAndGet();
        }

        public int get() {
            return atomicInteger.get();
        }
    }
}


