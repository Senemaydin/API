package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {

    /*
   Given
       https://restful-booker.herokuapp.com/booking/0
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "TechProEd"
   And
       Server is "Cowboy"
*/

    @Test
    public void get02(){

//        i)Set the URL
          String url = "https://restful-booker.herokuapp.com/booking/0";

//         ii)Set the expected data

//         iii)Send the request and get the response
           Response  response =given().get(url);
           response.prettyPrint();
//         iv)Do Assertion

           response.
                   then().
                   statusCode(404). // HTTP Status code should be 404
                   statusLine("HTTP/1.1 404 Not Found"); //Status Line should be HTTP/1.1 404 Not Found

         //Response body contains "Not Found"
           String responseBody = response.asString();
         //With assetEquals() method we match 2 parameters. If they are equal test will pass.
           assertEquals("Not Found",responseBody);
         //Response body does not contain "TecProEd"
         //With assetNotEquals() method we match 2 parameters. If they are  NOT equal test will pass.
          assertNotEquals("TechProEd", responseBody);

          // OR
         //assertFalse() method passes if the value between parenthesis is false otherwise it will fail.
         assertFalse(responseBody.contains("TechProEd"));



          //Server is "Cowboy"
        String server = response.header("Server");
        assertEquals("Cowboy", server);

    }
}
