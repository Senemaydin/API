package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestAndResponse {

    /*
    1)Postman is used for manual API testing.
    2)We use Rest Assured Library for API automation testing.
    3)To type automation script follow this steps
        a)Understand the requirements.
        b)Type the test case
           To type test cases. We use "Gherkin Language"
           The keyword in "Gherkin Language" :
               x)Given: It is used for pre-conditions(Endpoint)
               y)When: It is used for actions(Request)
               z)Then: It is used for output(Assertion)
               t)And: It is used for multiple usage of keywords
        c)Start to type Automation Script :
                i)Set the URL
                ii)Set the expected data
                iii)Send the request and get the response
                iv)Do Assertion
     */

     /*
   Given
       https://restful-booker.herokuapp.com/booking/10
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
    */

    public static void main(String[] args) {

//        i)Set the URL
          String url = " https://restful-booker.herokuapp.com/booking/10";

//        ii)Set the expected data
          //We will do this step later

//        iii)Send the request and get the response
          Response response = given().get(url); //get() method returns a Response
          //To see the response on console use prettyPrint() method with response object
       //   response.prettyPrint();

//        iv)Do Assertion
          //To do assertion we need testing framework like JUnit, Test NG, Cucumber etc.

          //How to get status code
          //All the data comes from API is inside "response"
          //HTTP Status Code should be 200
          System.out.println("Status code: " +response.statusCode());

          //How to get Content Type :
          // Content Type should be JSON
          System.out.println("Content Type: "+ response.contentType());

         //How to get Status Line:
         // Status Line should be HTTP/1.1 200 OK
         System.out.println("Status Line: " + response.statusLine());

         //How to get Header:
         System.out.println(response.header("Date"));

         //How to get Headers:
         System.out.println("\nHeaders: ");
         System.out.println(response.headers());

         //How to get Time:
         System.out.println("Time: " +response.time());


    }


}
