package practice;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Practice04 extends HerOkuAppBaseUrl {

    /*
        Given
           https://restful-booker.herokuapp.com/booking?firstname=John&secondname=Smith
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "John" and lastname is "Smith"

 */
    @Test
    public void get04(){

        //Set the url
        spec
                .pathParam("first","booking")
                .queryParams("firstname","John", "lastname","Smith");

        //Set the expected data


        //Sent the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));




    }

}
