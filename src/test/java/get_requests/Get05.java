package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
   Given
       https://restful-booker.herokuapp.com/booking
   When
       User sends get request to the URL
   Then
       Status code is 200
     And
       Among the data there should be someone whose firstname is "John" and lastname is "Smith"
*/

    @Test
    public void get05(){

        //Set the url
        //https://restful-booker.herokuapp.com/booking?Firstname=John&Lastname=Smith
        spec
                .pathParam("first", "booking")
                .queryParams("Firstname","John","Lastname", "Smith");

        //Set the expected data


        //Sent the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion

        response
                .then()
                .statusCode(200).
                body("bookingid", hasSize(Matchers.greaterThan(0)));
        //hasSize(Matchers.greaterThan(0)) method checks if the size of the bookingIds is greater than 0


        //OR
        //If response body contains "bookingId", it means body is not empty.
        assertTrue(response.asString().contains("bookingid"));




    }
}
