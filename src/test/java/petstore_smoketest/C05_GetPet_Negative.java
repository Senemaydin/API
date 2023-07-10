package petstore_smoketest;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static petstore_smoketest.C01_CreatePet.petId;

public class C05_GetPet_Negative extends PetStoreBaseUrl {
/*
    Given
        https://petstore.swagger.io/v2/pet/3465589
    When
        Send get request
    Then
        Status code is 404
    And
        Response body is:
        {
            "code": 1,
            "type": "error",
            "message": "Pet not found"
        }
     */

    @Test
    public void get02(){

        //Set the url
        spec.pathParams("first", "pet", "second",petId);

        //Send the expected data
        String expectedData = "Pet not found";

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        String actualData = response.jsonPath().getString("message");


        assertEquals(404, response.statusCode());
        assertEquals(expectedData,actualData);

    }


}
