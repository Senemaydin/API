package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01 extends JsonPlaceHolderBaseUrl {

        /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){

        //Set the url
        spec.pathParam("first", "todos");

        //Set the expected data --> Payload --> The data to transfer
        //We can create the payload by String but it is not Recommended! Because it is not going to be dynamic to get the data in assertion
        String expectedData =  "{\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false}";

        //Send the request and get the response
        Response response = given(spec).contentType(ContentType.JSON).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        //1.Way:
         JsonPath jsonPath = response.jsonPath();
         assertEquals(201, response.statusCode());
         assertEquals(55,jsonPath.getInt("userId"));
         assertEquals("Tidy your room",jsonPath.getString("title"));
         assertFalse("false",jsonPath.getBoolean("completed"));


       //OR
       //2.way: This way hard coding not prefer
        response
                .then()
                .statusCode(201)
                .body("userId",equalTo(55),
                        "title",equalTo("Tidy your room"),
                        "completed",equalTo(false));
    }
}

