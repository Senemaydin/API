package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.expectedDataMap;

public class Get09 extends JsonPlaceHolderBaseUrl {

     /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title":  "quis ut nam facilis et officia qui"
                "completed": false
            }
     */

    @Test
    public void get09(){

        //Set the url
        spec.pathParams("first", "todos", "second", 2);

        //Set the expected data --> To do assertion we will create the expected data
        Map<String, Object> expectedData = expectedDataMap(1,"quis ut nam facilis et officia qui", false );
        expectedData.put("id", 2);
        //To prevent hard coding in assertion, we add the headers here
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData =response.as(HashMap.class);
        System.out.println("actualData  = " + actualData);


        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //To prevent hard coding in assertion, we add the headers above in expected data
        assertEquals(expectedData.get("Via"), response.header("Via"));
        assertEquals(expectedData.get("Server"), response.header("Server"));

        //If you need to assert id:
        // assertEquals(expectedData.get("id"), actualData.get("id"));


    }


}
