package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01  extends JsonPlaceHolderBaseUrl {

     /*
     Given
         https://jsonplaceholder.typicode.com/todos/198
     When
   I send DELETE Request to the Url
Then
   Status code is 200
   And Response body is { }
  */

@Test
    public void delete01(){

     //Set the url
    spec.pathParams("first", "todos","second",198);

    //Set the expected data
    Map<String, String> expectedData = new HashMap<>();

    //Send the requests and get the response
    Response response = given(spec).delete("{first}/{second}");
    response.prettyPrint();

    //Do assertion
    Map<String, String>  actualData = convertJsonToJava(response.asString(), HashMap.class);
    System.out.println("actualData = " + actualData);
    assertEquals(200, response.statusCode());

    //1st Way:
    assertEquals(expectedData, actualData);

    //2nd Way: ==> This is a better
    assertTrue(actualData.isEmpty());

    //3rd Way:
    assertEquals(0,actualData.size());


    //Be careful when you do delete request. Do not delete anyone else's data. First create data and delete it.



}


}
