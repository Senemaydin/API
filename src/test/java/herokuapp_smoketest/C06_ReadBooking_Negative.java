package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.nio.file.Files;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06_ReadBooking_Negative extends HerOkuAppBaseUrl {

    /*
    Given
         https://restful-booker.herokuapp.com/booking/:id
    When
         Send the get request
    Then
         Status code is 404
     And
            Response body is "Not Found"
     */

    @Test
    public void get02(){
        //Set the url
        spec.pathParams("first", "booking","second", bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the response and get the response
        Response response =given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(404, response.statusCode());






    }


}
