package mango.api.appgateway;

import mango.api.appgateway.extensions.LifeCycleExtension;
import mango.api.appgateway.extensions.TimingExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@ExtendWith(TimingExtension.class)
class CatalogTest extends LifeCycleExtension {

    private String baseURL = "http://appgateway.pro.mango.com";

    @ParameterizedTest
    @CsvSource({"ES, ES, she.sections_she.prendas_she.abrigos_she, 1, 3", "ES, CA, she.sections_she.prendas_she.abrigos_she, 1, 3"})
    void catalog_basic(String isoCode, String languageCode, String uuid, String numPage, String numGarments){
        given().
                pathParam("isoCode", isoCode).
                pathParam("languageCode", languageCode).
                pathParam("uuid", uuid).
                queryParam("numPage", numPage).
                queryParam("numGarments", numGarments).
        when().
                get(baseURL + "/catalog/v1/{isoCode}/{languageCode}/{uuid}").
        then().
                assertThat().
                    statusCode(200).
                    body("filters", hasSize(4));
    }
}
