package Junit5;

import Junit5.CustomAnnotations.*;
import org.junit.jupiter.api.*;

@Tag("lifecycle")
@Functional // works as a custom tag
public class LifeCycleTest {

    @BeforeAll
    static void setupAll() {
        System.out.println("Setup ALL TESTS in the class");
    }

    @BeforeEach
    void setup() {
        System.out.println("Setup EACH TEST in the class");
    }

    @Tag("first")
    @Test
    void testOne() {
        System.out.println("TEST 1");
    }

    @Tag("second")
    @DisplayName(":)")
    @Test
    void testTwo() {
        System.out.println("TEST 2");
    }

    @AfterEach
    void teardown() {
        System.out.println("Teardown EACH TEST in the class");
    }

    @AfterAll
    static void teardownAll() {
        System.out.println("Teardown ALL TESTS in the class");
    }
}

class NonFunctionalTest {

    @Test
    @Load
    void testOne() {
        System.out.println("Test 1");
    }

    @Test
    @Stress
    void testTwo() {
        System.out.println("Test 2");
    }

    @Test
    @Security
    void testThree() {
        System.out.println("Test 3");
    }

    @Test
    @Usability
    void testFour() {
        System.out.println("Test 4");
    }

}
