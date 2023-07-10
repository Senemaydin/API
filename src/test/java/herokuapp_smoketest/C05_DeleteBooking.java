package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.junit.Test;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_DeleteBooking extends HerOkuAppBaseUrl {

    /*
    Given
         https://restful-booker.herokuapp.com/booking/:id
     When
         Send delete request
     Then
          Status code is 201
     And
          Response body is "Created"
     */

    @Test
    public void delete01(){

        //Set the url
        spec.pathParams("first", "booking","second", bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the response and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData,response.asString());




    }
}
