package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.AuthenticationGmiBank.generateToken;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before

    public void setUp() {
        spec = new RequestSpecBuilder()
                .addHeader("Authorization","Bearer "+generateToken())
                .setContentType(ContentType.JSON)
                .setBaseUri("https://www.gmibank.com")
                .build();
    }

}





