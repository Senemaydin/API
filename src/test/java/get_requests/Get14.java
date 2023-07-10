package get_requests;

import base_urls.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get14  extends DummyBaseUrl {

        /*
Given
    https://dummy.restapiexample.com/api/v1/employees
When
    User sends Get Request to the Url
Then
    Status code is 200
And
    There are 24 employees
And
    "Tiger Nixon" and "Garrett Winters" are among the employees
And
    The greatest age is 66
And
    The name of the lowest age is "[Tatyana Fitzpatrick]"
And
    Total salary of all employees is 6,644,770
 */

    @Test

    public void get14(){

        //Set the url
        spec.pathParam("first", "employees");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("data", hasSize(24),
                        "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));


        //The greatest age is 66
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ages = jsonPath.getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("ages = " + ages);
        assertEquals(66, (int)ages.get(ages.size()-1));


        //The name of the lowest age is "[Tatyana Fitzpatrick]"
        Object nameOfLowestAge = (String) jsonPath.getList("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name").get(0);
        System.out.println("nameOfLowestAge = " + nameOfLowestAge);
        assertEquals("Tatyana Fitzpatrick", nameOfLowestAge);

        //Total salary of all employees is 6,644,770
       List<Integer> salaries = jsonPath.getList("data.employee_salary");
        System.out.println("salaries = " + salaries);
        
        //1st Way: for each loop
        int sumOfSalaries =0;
        for (int w :salaries){
            sumOfSalaries +=w;       // -->sumOfSalaries + w;
        }
        System.out.println("sumOfSalaries = " + sumOfSalaries);
        assertEquals(6644770, sumOfSalaries);
        
        //2nd Way: Lambda
        int sumOfSalariesLambda = salaries.stream().reduce(0, Integer::sum);
        System.out.println("sumOfSalariesLambda = " + sumOfSalariesLambda);
        assertEquals(6644770, sumOfSalariesLambda);
        
        

    }

}
