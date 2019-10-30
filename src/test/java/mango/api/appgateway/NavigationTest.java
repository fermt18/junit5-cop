package mango.api.appgateway;

import mango.api.appgateway.extensions.NavigationArgProvider;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.DynamicTest.stream;

class NavigationTest {

    private String baseURL = "http://appgateway.pro.mango.com";

    // rest assured
    // parametrized by CSV
    @Test
    void navigation_version() {
        String isoCode = "ES";
        String languageCode = "ES";
        given().
                pathParam("isoCode", isoCode).
                pathParam("languageCode", languageCode).
        when().
                get(baseURL + "/navigation/v1/{isoCode}/{languageCode}").

        then().
                assertThat().
                    statusCode(200).
                    body("version", equalTo("1.0"));
    }

    @ParameterizedTest
    //@CsvSource({"ES, ES", "ES, CA"})
    @ArgumentsSources({
            @ArgumentsSource(NavigationArgProvider.class)})
    void navigation_menus(String isoCode, String languageCode) {
        //String isoCode = "ES";
        //String languageCode = "FR";
        given().
                pathParam("isoCode", isoCode).
                pathParam("languageCode", languageCode).
        when().
                get(baseURL + "/navigation/v1/{isoCode}/{languageCode}").
        then().
                assertThat().
                    statusCode(200).
                    body("menus", hasSize(6)).
                    body("menus.id", hasItems("she", "he", "nina", "nino", "violeta", "sections_edits"));
    }


    // Test Factory approach - not life cycle callbacks available
    @TestFactory
    Stream<DynamicTest> streamTest() {
        // Input data
        Iterator<IsoCode> inputGenerator = getCodes();

        // Display names
        Function<IsoCode, String> displayNameGenerator = (input) -> "Data input: " + input;

        // Test executor
        ThrowingConsumer<IsoCode> testExecutor = (input) -> {
            given().
                    pathParam("isoCode", input.isoCode).
                    pathParam("languageCode", input.languageCode).
            when().
                    get(baseURL + "/navigation/v1/{isoCode}/{languageCode}").
            then().
                    assertThat().
                        statusCode(200).
                        body("menus.id", hasItems("she", "he", "nina", "nino", "violeta", "sections_edits"));
        };

        // Returns a stream of dynamic tests
        return stream(inputGenerator, displayNameGenerator,
                testExecutor);
    }

    Iterator<IsoCode> getCodes(){
        List<IsoCode> isoCodeList = new ArrayList();
        isoCodeList.add(new IsoCode("ES", "ES"));
        isoCodeList.add(new IsoCode("ES", "CA"));
        // ddbb, shopconfig, file, ...
        return isoCodeList.iterator();
    }

    class IsoCode {
        private String isoCode;
        private String languageCode;

        IsoCode(String isoCode, String languageCode){
            this.isoCode = isoCode;
            this.languageCode = languageCode;
        }
    }
}
