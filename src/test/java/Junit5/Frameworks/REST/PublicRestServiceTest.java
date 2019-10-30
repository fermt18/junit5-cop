package Junit5.Frameworks.REST;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class PublicRestServiceTest {

    @Test
    void testEchoService() {
        String key = "foo";
        String value = "bar";
        given().when().get("http://echo.jsontest.com/" + key + "/" + value)
                .then().assertThat().statusCode(200).body(key,
                equalTo(value));
    }
}