package practice;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Practice05 extends ReqresBaseUrl {

     /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */


    @Test
    public void get05(){

        //Set the url
        spec.pathParams("first","unknown","second",3);

        //Set the expected data


        //Sent the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);


        JsonPath jsonPath= response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        softAssert.assertEquals(jsonPath.getInt("data.id"), 3,"Id does not match");
        softAssert.assertEquals(jsonPath.getString("data.name"), "true_red","Name does not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"), 2002,"Year does not match");
        softAssert.assertEquals(jsonPath.getString("data.color"), "#BF1932","Color does not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"), "19-1664","Pantone value does not match");
        softAssert.assertEquals(jsonPath.getString("support.url"), "https://reqres.in/#support-heading","Url does not match");
        softAssert.assertEquals(jsonPath.getString("support.text"),  "To keep ReqRes free, contributions towards server costs are appreciated!",
               "Text does not match");


    }
}
