import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {


   @Test
   public void testGetSinglePost() {
      given().baseUri("https://jsonplaceholder.typicode.com").
              when().get("/posts/1").
              then().statusCode(200).body("id", equalTo(1)).
              body("userId", equalTo(1));
   }

   @Test
   public void testGetNonExistingPost(){
      given().baseUri("https://jsonplaceholder.typicode.com").
              when().get("/posts/101").
              then().statusCode(404);
   }
}
