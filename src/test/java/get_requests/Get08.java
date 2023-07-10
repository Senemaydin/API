package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get08 extends JsonPlaceHolderBaseUrl {

     /*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL
Then
    1)Status code is 200
    2)Print all ids greater than 190 on the console
      Assert that there are 10 ids greater than 190
    3)Print all userIds whose ids are less than 5 on the console
      Assert that the number of userIds whose ids are less than 5 is 4
    4)Print all titles whose ids are less than 5
      Assert that "delectus aut autem" is one of the titles whose id is less than 5
   */

    @Test
    public void get08(){

        //Set the url
        spec.pathParam("first","todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion
        assertEquals(200, response.statusCode());

        // Print all ids greater than 190 on the console
        //1.way: By using for loop
        JsonPath jsonpath = response.jsonPath();
        List<Integer> idList = jsonpath.getList("id");
        System.out.println("idList : " + idList);

       List<Integer> idsGreaterThan190 = new ArrayList<>();
        for (int w : idList){
            if (w>190){
                idsGreaterThan190.add(w);
            }
        }
        System.out.println("idsGreaterThan190 =  " +idsGreaterThan190);

        // Assert that there are 10 ids greater than 190
        assertEquals(10, idsGreaterThan190.size());

        //2.Way : By using Groovy language -->Recommended -->more dynamic
        //If you have a list with json data elements you can use groovy language to do filter.
        List<Integer> idSGreaterThan190Groovy = jsonpath.getList("findAll{it.id>190}.id"); //Groovy language --> Java based programming language
        System.out.println("idSGreaterThan190Groovy  " + idSGreaterThan190Groovy);

        //Assert that there are 10 ids greater than 190
        assertEquals(10, idSGreaterThan190Groovy.size());

        //3)Print all userIds whose ids are less than 5 on the console
        List<Integer> idLessThan5Groovy =  jsonpath.getList("findAll{it.id<5}.userId");
        System.out.println("idLessThan5Groovy "  + idLessThan5Groovy);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,idLessThan5Groovy.size());

        //4)Print all titles whose ids are less than 5
        List<String> idLessThan5Title = jsonpath.getList("findAll{it.id<5}.title");
        System.out.println("idLessThan5Title "  + idLessThan5Title);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
       assertTrue(idLessThan5Title.contains("delectus aut autem"));

    }
}
