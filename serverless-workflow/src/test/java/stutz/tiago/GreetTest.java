package stutz.tiago;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class GreetTest {

    @Test
    public void testEnglish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"Yoda\", \"country\":\"United States\", \"reference_currency\":\"BRL\"}}")
                .when()
                .post("/greeting")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Greetings"), "workflowdata.city",
                        containsString("New York"), "workflowdata.state_code", containsString("NY"),
                        "workflowdata.avg_food_price", greaterThan(0.0F));
    }

    @Test
    public void testSpanish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"Yoda\", \"country\":\"Spain\"}}")
                .when()
                .post("/greeting")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Saludos"), "workflowdata.city",
                        containsString("Barcelona"));
    }
}