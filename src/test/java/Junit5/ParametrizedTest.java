package Junit5;

import Junit5.ArgumentProvider.CustomArgumentsProvider1;
import Junit5.ArgumentProvider.CustomArgumentsProvider2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

public class ParametrizedTest {

    // ValueSource ----------------------------------------------------------
    @ParameterizedTest
    @ValueSource(strings = { "Hello", "World" })
    void testWithStrings(String argument) {
        System.out.println("Parameterized test with (String) parameter:  "
                + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1 })
    void testWithInts(int argument) {
        System.out.println("Parameterized test with (int) argument: "
                + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(longs = { 2L, 3L })
    void testWithLongs(long argument) {
        System.out.println("Parameterized test with (long) argument: "
                + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 4d, 5d })
    void testWithDoubles(double argument) {
        System.out.println("Parameterized test with (double) argument: "
                + argument);
        assertNotNull(argument);
    }

    // EnumSource ----------------------------------------------------------
    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void testWithEnum(TimeUnit argument) {
        System.out.println("Parameterized test with (TimeUnit) argument: "
                + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
    void testWithFilteredEnum(TimeUnit argument) {
        System.out.println("Parameterized test with some (TimeUnit) argument: "
                + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = EXCLUDE, names = {
            "DAYS", "HOURS" })
    void testWithExcludeEnum(TimeUnit argument) {
        System.out.println("Parameterized test with excluded (TimeUnit) argument: "
                + argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @EnumSource(value = TimeUnit.class, mode = MATCH_ALL, names = "^(M|N).+SECONDS$")
    void testWithRegexEnum(TimeUnit argument) {
        System.out.println("Parameterized test with regex filtered (TimeUnit) argument: "
                + argument);
        assertNotNull(argument);
    }

    // MethodSource ----------------------------------------------------------
    static Stream<String> stringProvider() {
        return Stream.of("hello", "world");
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithStringProvider(String argument) {
        System.out.println("Parameterized test with (String) argument: "
                + argument);
        assertNotNull(argument);
    }

    static IntStream intProvider() {
        return IntStream.of(0, 1);
    }

    @ParameterizedTest
    @MethodSource("intProvider")
    void testWithIntProvider(int argument) {
        System.out.println("Parameterized test with (int) argument: "
                + argument);
        assertNotNull(argument);
    }

    static Stream<Arguments> stringAndIntProvider() {
        return Stream.of(Arguments.of("Mastering", 10),
                Arguments.of("JUnit 5", 20));
    }

    @ParameterizedTest
    @MethodSource("stringAndIntProvider")
    void testWithMultiArgMethodSource(String first, int second) {
        System.out.println("Parameterized test with two arguments: (String) " + first + " and (int) " + second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    // CsvSource ----------------------------------------------------------
    @ParameterizedTest
    @CsvSource({ "hello, 1", "world, 2", "'happy, testing', 3" })
    void testWithCsvSource(String first, int second) {
        System.out.println("Parameterized test with (String) " + first + " and (int) " + second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    // CsvFileSource ----------------------------------------------------------
    @ParameterizedTest
    @CsvFileSource(resources = "/input.csv") // uses getResourceAsStream()
    void testWithCsvFileSource(String first, int second) {
        System.out.println("Yet another parameterized test with (String) " + first + " and (int) " + second);
        assertNotNull(first);
        assertNotEquals(0, second);
    }

    @DisplayName("Display name of test container")
    @ParameterizedTest(name = "[{index}] first argument=\"{0}\", second argument={1}")
    @CsvSource({ "mastering, 1", "parameterized, 2", "tests, 3" })
    void testWithCustomDisplayNames(String first, int second) {
        System.out.println("Testing with parameters: " + first + " and " +
                second);
    }

    // ArgumentsSource ----------------------------------------------------------
    @ParameterizedTest
    @ArgumentsSources({
            @ArgumentsSource(CustomArgumentsProvider1.class),
            @ArgumentsSource(CustomArgumentsProvider2.class)})
    void testWithArgumentsSource(String first, int second) {
        System.out.println("Parameterized test with (String) " + first
                + " and (int) " + second);
        assertNotNull(first);
        assertTrue(second > 0);
    }
}
