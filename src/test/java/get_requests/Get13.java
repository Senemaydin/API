package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;


public class Get13 extends GorestBaseUrl {

    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        "Marut Nambeesan DC", "Ms. Ankal Sinha" and "Chaturbhuj Adiga" are among the users
    And
        The female users are less than or equals to male users
*/


    @Test
    public void get13(){
        //Set the url
        spec.pathParam("first", "users");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("meta.pagination limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Dron Saini", "Rohana Tandon", "Menaka Iyengar Esq."));

        //Number of female users are greater than or equals to male users
        JsonPath jsonPath = response.jsonPath();
        List<String> genderList = jsonPath.getList("data.gender");
        System.out.println("genderList = " + genderList);

        //1st way: Using for each loop
        int numberOfFemales = 0;
        for (String w : genderList) {
            if (w.equalsIgnoreCase("female")) {
                numberOfFemales++;
            }
        }
        System.out.println("numberOfFemales = " + numberOfFemales);

        assertTrue(numberOfFemales >= genderList.size() - numberOfFemales);

        //2nd Way: Using groovy language
        int numOfFemales = jsonPath.getList("data.findAll{it.gender=='female'}").size();
        System.out.println("numOfFemales = " + numOfFemales);

        int numOfMales = jsonPath.getList("data.findAll{it.gender=='male'}").size();
        System.out.println("numOfMales = " + numOfMales);

        assertTrue(numOfFemales >= numOfMales);

    }
}