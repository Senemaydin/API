package gmibank_api;

import base_urls.GmiBankBaseUrl;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.CountryPost;
import pojos.State;
import utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class CreateCountry extends GmiBankBaseUrl {
    /*
    Given
        https://gmibank.com/api/tp-countries
    And
        {
          "name": "Banana Republic",
          "states": [
            {
              "id": 0,
              "name": "Apple"
            },
             {
              "id": 1,
              "name": "Orange"
            },
             {
              "id": 2,
              "name": "Peach"
            }
          ]
        }
    When
        Send post request
    Then
        Status code is 201
    And
        Response body is like:
        {
            "id": 190145,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
     */
    @Test
    public void post01(){
        //Set the url
        spec.pathParams("first","api","second","tp-countries");

        //Set the expected data
        State state1 = new State(0,"Apple");
        State state2 = new State(1,"Orange");
        State state3 = new State(2,"Peach");
        List<State> stateList = new ArrayList<>();
        stateList.add(state1);
        stateList.add(state2);
        stateList.add(state3);
        System.out.println("stateList = " + stateList);

        CountryPost expectedData = new CountryPost("Banana Republic",stateList);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Validation: Then() method
        response
                .then()
                .statusCode(201)
                .body("name",equalTo( expectedData.getName()),
                        "states.id[0]",equalTo(state1.getId()),
                        "states.name[0]",equalTo(state1.getName()),
                        "states.id[1]",equalTo(state2.getId()),
                        "states.name[1]",equalTo(state2.getName()),
                        "states.id[2]",equalTo(state3.getId()),
                        "states.name[2]",equalTo(state3.getName())
                );

        //2nd Validation --> jsonPath() method
        JsonPath jsonPath = response.jsonPath();

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getName(), jsonPath.getString("name"));

        assertEquals(state1.getId(), jsonPath.getList("states.id").get(0));
        assertEquals(state1.getName(), jsonPath.getList("states.name").get(0));

        assertEquals(state2.getId(), jsonPath.getList("states.id").get(1));
        assertEquals(state2.getName(), jsonPath.getList("states.name").get(1));

        assertEquals(state3.getId(), jsonPath.getList("states.id").get(2));
        assertEquals(state3.getName(), jsonPath.getList("states.name").get(2));


        //3rd Validation --> as() method
        Map<String, Object> actualDataMap =  response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getName(), actualDataMap.get("name"));
        assertEquals(state1.getId(), ((Map)((List)actualDataMap.get("states")).get(0)).get("id"));
        assertEquals(state1.getName(), ((Map)((List)actualDataMap.get("states")).get(0)).get("name"));

        assertEquals(state2.getId(), ((Map)((List)actualDataMap.get("states")).get(1)).get("id"));
        assertEquals(state2.getName(), ((Map)((List)actualDataMap.get("states")).get(1)).get("name"));

        assertEquals(state3.getId(), ((Map)((List)actualDataMap.get("states")).get(2)).get("id"));
        assertEquals(state3.getName(), ((Map)((List)actualDataMap.get("states")).get(2)).get("name"));

        //4th Validation --> as() method
        CountryPost actualDataPojo = response.as(CountryPost.class);
        System.out.println("actualDataPojo = " + actualDataPojo);

        assertEquals(expectedData.getName(), actualDataPojo.getName());
        assertEquals(state1.getId(), actualDataPojo.getStates().get(0).getId());
        assertEquals(state1.getName(), actualDataPojo.getStates().get(0).getName());

        assertEquals(state2.getId(), actualDataPojo.getStates().get(1).getId());
        assertEquals(state2.getName(), actualDataPojo.getStates().get(1).getName());
        
        assertEquals(state3.getId(), actualDataPojo.getStates().get(2).getId());
        assertEquals(state3.getName(), actualDataPojo.getStates().get(2).getName());
        
        //5th Validation --> Best Practice: Pojo Class + Object Mapper
        CountryPost actualDataPojoMapper = ObjectMapperUtils.convertJsonToJava(response.asString(), CountryPost.class);
        System.out.println("actualDataPojoMapper = " + actualDataPojoMapper);

        assertEquals(expectedData.getName(), actualDataPojoMapper.getName());
        assertEquals(state1.getId(), actualDataPojoMapper.getStates().get(0).getId());
        assertEquals(state1.getName(), actualDataPojoMapper.getStates().get(0).getName());

        assertEquals(state2.getId(), actualDataPojoMapper.getStates().get(1).getId());
        assertEquals(state2.getName(), actualDataPojoMapper.getStates().get(1).getName());
        
        assertEquals(state3.getId(), actualDataPojoMapper.getStates().get(2).getId());
        assertEquals(state3.getName(), actualDataPojoMapper.getStates().get(2).getName());

        //6th Validation: Gson().fromJson() method --> De-serialization

        CountryPost actualDataPojoGson = new Gson().fromJson(response.asString(), CountryPost.class);
        System.out.println("actualDataPojoGson = " + actualDataPojoGson);

        assertEquals(expectedData.getName(), actualDataPojoGson.getName());

        assertEquals(state1.getId(), actualDataPojoGson.getStates().get(0).getId());
        assertEquals(state1.getName(), actualDataPojoGson.getStates().get(0).getName());

        assertEquals(state2.getId(), actualDataPojoGson.getStates().get(1).getId());
        assertEquals(state2.getName(), actualDataPojoGson.getStates().get(1).getName());

        assertEquals(state3.getId(), actualDataPojoGson.getStates().get(2).getId());
        assertEquals(state3.getName(), actualDataPojoGson.getStates().get(2).getName());

    }
}




