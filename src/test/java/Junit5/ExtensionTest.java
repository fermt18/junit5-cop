package Junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.condition.OS.LINUX;
import static org.junit.jupiter.api.condition.OS.MAC;

import Junit5.Extensions.IgnoreIOExceptionExtension;
import Junit5.Extensions.ParameterResolverExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.HashMap;

class CustomExtensionTests {

    @Test
    void basicTest() {
        String message = "1+1 should be equal to 2";
        System.out.println(message);
        assertEquals(2, 1 + 1, message);
    }

    @ExtendWith(IgnoreIOExceptionExtension.class)
    @Test
    public void exceptionIgnored() throws IOException {
        throw new IOException("IO Exception");
    }

    @Disabled("Disabled as will fail")
    @Test
    public void exceptionThrown() throws IOException {
        throw new IOException("My IO Exception");
    }
}

class DependencyInjectionTest {

    @ExtendWith(ParameterResolverExtension.class)
    @Test
    public void test(Object parameter) {
        System.out.println("My parameter " + parameter);
    }

    @Test
    @DisplayName("My test")
    @Tag("my-tag")
    void testOne(TestInfo testInfo) {
        // TestInfo is injected by TestInfoParameterResolver
        System.out.println(testInfo.getDisplayName());
        System.out.println(testInfo.getTags());
        System.out.println(testInfo.getTestClass());
        System.out.println(testInfo.getTestMethod());
    }

    @RepeatedTest(2)
    void test(RepetitionInfo repetitionInfo) {
        // RepetitionInfo is injected by RepetitionInfoParameterResolver
        System.out.println("** Test " +
                repetitionInfo.getCurrentRepetition()
                + "/" + repetitionInfo.getTotalRepetitions());
    }

    @Test
    void reportSingleValue(TestReporter testReporter) {
        // TestReporter is injected by TestReporterParameterResolver
        testReporter.publishEntry("key", "value");
        HashMap<String, String> values = new HashMap<>();
        values.put("name", "john");
        values.put("surname", "doe");
        testReporter.publishEntry(values);
    }
}

class DisabledOnOsTest {

    @DisabledOnOs({ MAC, LINUX })
    @Test
    void conditionalTest() {
        System.out.println("This test will be disabled on MAC and LINUX");
    }

}
