package mango.api.appgateway.extensions;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class LifeCycleExtension {

    @BeforeAll
    static void setupAll() {

        System.out.println("Setup ALL TESTS in the class");
        when().
                get("http://appgateway.pro.mango.com/health").
        then().
                assertThat().
                statusCode(200).
                body("status", is("UP"));
    }

    @BeforeEach
    void setup(TestInfo info) {
        System.out.println("Setup " + info.getDisplayName() + " in the class");
    }

    @AfterEach
    void teardown() {
        System.out.println("Teardown single test in the class");
    }

    @AfterAll
    static void teardownAll() {
        System.out.println("Teardown ALL TESTS in the class");
    }
}
