package mango.api.appgateway;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

class GarmentsTest {

    private String baseURL = "http://appgateway.pro.mango.com";

    @Test
    void garments_basic(){
        /*
        garments/v1/{isoCode}/{languageCode}/{uuid}?numPage={numPage}&numGarments={numGarments}
        String uuid = "she.sections_she.prendas_she.abrigos_she";
        String numPage = "1";
        String numGarments = "3";*/
        given().
                pathParam("isoCode", "ES").
                pathParam("languageCode", "ES").
                pathParam("uuid", "she.sections_she.prendas_she.abrigos_she").
                queryParam("numPage", "1").
                queryParam("numGarments", "3").
        when().
                get(baseURL + "/garments/v1/{isoCode}/{languageCode}/{uuid}").
        then().
                assertThat().
                    statusCode(200).
                    body("groups", hasSize(1)).
                    body("groups[0].garments", hasSize(3));
    }
}
