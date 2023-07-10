package petstore_smoketest;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static petstore_smoketest.C01_CreatePet.petId;

public class C04_DeletePet extends PetStoreBaseUrl {
 /*
    Given
        https://petstore.swagger.io/v2/pet/3465589
    When
        Send delete request
    Then
        Status code is 200
    And
        Response body is:
        {
            "code": 200,
            "type": "unknown",
            "message": "3465589"
        }
     */


    @Test
    public void delete01(){
        //Set the url
        spec.pathParams("first", "pet", "second",petId);

        //Send the expected data

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        String message = response.jsonPath().getString("message");
        assertEquals(200, response.statusCode());
        assertEquals(petId+"", message);

    }

    }


