package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerOkuAppTestData.expectedDataMethod;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04_PatchBooking extends HerOkuAppBaseUrl {
    /*
     Given
          https://restful-booker.herokuapp.com/booking/:id
     And
             {
            "firstname" : "Mary",
            "lastname" : "Star"
            }
     When
         Send the patch request
     Then
         Status code is 201
      And
                {
            "firstname": "Mary",
            "lastname": "Star",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-01-01",
                "checkout": "2024-01-01"
            },
            "additionalneeds": "Dinner"
        }

     */

    @Test
    public void patch01(){

        //Set the url
        spec.pathParams("first","booking", "second", bookingId);

        //Set the expected data
        Map<String, Object> expectedData = expectedDataMethod("Mary","Star",null,null,null,null);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
       Response response = given(spec).body(expectedData).patch("{first}/{second}");
       response.prettyPrint();

        //Do assertion
       Map<String, Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

       assertEquals(200, response.statusCode());
       assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
       assertEquals(expectedData.get("lastname"), actualData.get("lastname"));




    }


}
