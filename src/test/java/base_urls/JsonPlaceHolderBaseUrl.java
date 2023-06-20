package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {
    //This class is created to prevent repeated actions in requests like base url, content type, authorization etc.

   protected  RequestSpecification spec;

    @Before  //This annotation runs the method before each @Test method. Then spec object will be assigned.
    public void setUp(){

        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();





    }

}
