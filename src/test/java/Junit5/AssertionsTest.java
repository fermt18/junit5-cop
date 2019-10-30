package Junit5;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {

    @Test
    void groupedAssertions(){
        assertAll(
                "assertion 1",
                ()->assertFalse(false,()-> "Really " + "expensive " + "message" + "."),
                ()->assertTrue(true, ()-> "Really " + "expensive " + "message" + "."));
    }

    @Test
    void exceptionTesting() {
        Throwable exception =
                assertThrows(IllegalArgumentException.class,
                        () -> {
                            throw new IllegalArgumentException("a message");});
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // Go on with execution even if timeout exceeded, then fail
        assertTimeout(
                ofMinutes(2),
                () -> {
            // Perform task that takes less than 2 minutes
        });
    }

    @Disabled("Failing test")
    @Test
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> {
            Thread.sleep(100);
        });

    }

    @Test
    void timeoutNotExceededWithResult() {
        String actualResult = assertTimeout(ofMinutes(1), () -> {
            return "hi there";
        });
        assertEquals("hi there", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        String actualGreeting = assertTimeout(ofMinutes(1),
                AssertionsTest::greeting);
        assertEquals("hello world!", actualGreeting);
    }

    @Disabled("Failing test")
    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // fail as soon as we reach the timeout
        assertTimeoutPreemptively(ofMillis(10), () -> {
            Thread.sleep(100);
        });
    }

    private static String greeting() {
        return "hello world!";
    }
}
