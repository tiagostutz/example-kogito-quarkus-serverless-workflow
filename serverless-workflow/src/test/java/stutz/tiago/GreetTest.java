package stutz.tiago;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class GreetTest {

    @Test
    public void testEnglish() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"workflowdata\" : {\"name\" : \"Yoda\", \"country\":\"United States\"}}")
                .when()
                .post("/greeting")
                .then()
                .statusCode(201)
                .body("workflowdata.greeting", containsString("Greetings"), "workflowdata.city",
                        containsString("New York"));
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