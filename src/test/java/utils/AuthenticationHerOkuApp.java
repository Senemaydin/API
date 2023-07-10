package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    public static String generateToken(){

        String body= "{\"username\" : \"admin\",\"password\" : \"password123\"}";
        Response response = given().contentType(ContentType.JSON).body(body).post("https://restful-booker.herokuapp.com/auth");


       return response.jsonPath().getString("token");

    }
}
