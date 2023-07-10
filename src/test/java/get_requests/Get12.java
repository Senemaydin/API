package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get12 extends JsonPlaceHolderBaseUrl {

       /*
  Given
      https://jsonplaceholder.typicode.com/todos/198
  When
       I send GET Request to the URL
   Then
       Status code is 200
       And response body is like {
                                  "userId": 10,
                                  "id": 198,
                                  "title": "quis eius est sint explicabo",
                                  "completed": true
                                }
*/

    @Test
    public void get12(){

        //Set the url
        spec.pathParams("first", "todos","second",198);

        //Set the expected data
        String stringJson = "{\n" +
                            "\"userId\": 10,\n" +
                            "\"id\": 198,\n" +
                            "\"title\": \"quis eius est sint explicabo\",\n" +
                            " \"completed\": true\n" +
                            " }";
        Map<String, Object>  expectedData = convertJsonToJava(stringJson, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData) ;

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
