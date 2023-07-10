package petstore_smoketest;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Category;
import pojos.PetPojo;
import pojos.Tags;
import utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreatePet extends PetStoreBaseUrl {
    /*
    Given
        https://petstore.swagger.io/v2/pet/
    And
        {
        "id": 3465589,
        "category": {
            "id": 0,
            "name": "Bird"
        },
        "name": "Tweety",
        "photoUrls": [
            "string"
        ],
        "tags": [
            {
                "id": 0,
                "name": "string"
            }
        ],
        "status": "available"
        }
    When
        Send post request
    Then
        Status code is 200
    And
        Response body is:
        {
        "id": 3465589,
        "category": {
            "id": 0,
            "name": "Bird"
        },
        "name": "Tweety",
        "photoUrls": [
            "string"
        ],
        "tags": [
            {
                "id": 0,
                "name": "string"
            }
        ],
        "status": "available"
        }
     */

    public static int petId;
    @Test
    public void post01(){
        //Set the url
        spec.pathParam("first","pet");

        //Set the expected data
        Category category =new Category(0,"Bird");
        Tags tags = new Tags(0,"string");
        List<Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);
        List<String> photoUrlsList = new ArrayList<>();
        photoUrlsList.add("string");
        petId=3465589;
        PetPojo exepctedData = new PetPojo(petId,category,"Tweety",photoUrlsList, tagsList,"available");
        System.out.println("exepctedData = " + exepctedData);

        //Send the request and get the response
        Response response= given(spec).body(exepctedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        PetPojo actualData = convertJsonToJava(response.asString(), PetPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(category.getId(), actualData.getCategory().getId());
        assertEquals(category.getName(), actualData.getCategory().getName());
        assertEquals(exepctedData.getName(), actualData.getName());
        assertEquals(exepctedData.getPhotoUrls().get(0), actualData.getPhotoUrls().get(0));
        assertEquals(exepctedData.getTags().get(0).getId(), actualData.getTags().get(0).getId());
        assertEquals(exepctedData.getTags().get(0).getName(), actualData.getTags().get(0).getName());
        assertEquals(exepctedData.getStatus(), actualData.getStatus());

    }
}
