package Junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.function.Function;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;


// created Dynamic Tests at runtime and not compilation time as JUnit4
// a TestFactory must return one of Stream, Collection, Iterable, or Iterator of TestFactoryTest
public class TestFactoryTest {

    @TestFactory
    Stream<DynamicTest> dynamicTestsFromStream() {
        Stream<String> inputStream = Stream.of("A", "B", "C");
        return inputStream.map(
                input -> dynamicTest("Display name for input " + input,
                        () -> {
                            System.out.println("Testing " + input);
                        }));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () ->
                        assertTrue(true)),
                dynamicTest("2nd dynamic test", () -> assertEquals(4, 2
                        * 2)));
    }

    @TestFactory
    Iterable<DynamicTest> dynamicTestsFromIterable() {
        return Arrays.asList(
                dynamicTest("3rd dynamic test", () ->
                        assertTrue(true)),
                dynamicTest("4th dynamic test", () -> assertEquals(4, 2
                        * 2)));
    }

    @TestFactory
    Iterator<DynamicTest> dynamicTestsFromIterator() {
        return Arrays.asList(
                dynamicTest("5th dynamic test", () ->
                        assertTrue(true)),
                dynamicTest("6th dynamic test", () -> assertEquals(4, 2
                        * 2))).iterator();
    }
}

class StreamExampleTest {

    @Disabled("Failing test")
    @TestFactory
    Stream<DynamicTest> streamTest() {
        // Input data
        Integer array[] = { 1, 2, 3 };
        Iterator<Integer> inputGenerator = Arrays.asList(array).iterator();

        // Display names
        Function<Integer, String> displayNameGenerator = (
                input) -> "Data input:" + input;

        // Test executor
        ThrowingConsumer<Integer> testExecutor = (input) -> {
            System.out.println(input);
            assertTrue(input % 2 == 0);
        };

        // Returns a stream of dynamic tests
        return stream(inputGenerator, displayNameGenerator,
                testExecutor);
    }
}
