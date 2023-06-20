package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get03 {

        /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
    Response format should be "application/json"
      And
    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
    "completed" is false
     And
    "userId" is 2
   */
    @Test
    public void get03(){
//        i)Set the URL
           String url = "https://jsonplaceholder.typicode.com/todos/23";

//        ii)Set the expected data

//        iii)Send the request and get the response
           Response response=given().get(url);
           response.prettyPrint();
//        iv)Do Assertion
        //1.Way:
        response
                .then()
                .statusCode(200)
                .contentType("application/json")//"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed", equalTo(false)) //"completed" is false
                .body("userId", equalTo(2)); // "userId" is 2

        //2.Way:
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2) );

        /*
               1)When you run code Java stops execution in the first failure.
                So, assertions after the first failure will not be executed.
                But the assertions before the failure will pass.
               2)If Java stops execution in the first failure this is called "Hard Assertion"
               3)If type your code as Java does NOT stop execution in the first failure this is called "Soft Assertion".
               4)If you use multiple body method it will work like "Hard Assertion"
               5)If you use single body method  with multiple assertions in it will work like "Soft Assertion"
         */


    }



}
