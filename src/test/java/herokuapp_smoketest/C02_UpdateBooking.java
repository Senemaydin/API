package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02_UpdateBooking extends HerOkuAppBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
                    {
            "firstname" : "Ali",
            "lastname" : "Can",
            "totalprice" : 1234,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2019-01-01",
                "checkout" : "2020-01-01"
            },
            "additionalneeds" : "Breakfast"
        }

    When
          Send the put request
    Then
          Status code is 200
     And
          Response body is :

                   {
            "firstname": "James",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
     */



@Test
    public void put01(){
      //Set the url
       spec.pathParams("first","booking","second", bookingId);

    //Set the expected data
    BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2019-01-01","2020-01-01");
    BookingPojo expectedData = new BookingPojo("James","Brown",111,true,bookingDatesPojo,"Breakfast");
    System.out.println("expectedData = " + expectedData);

    //Send the request and get the response
    Response response = given(spec).body(expectedData).put("{first}/{second}");
    response.prettyPrint();

    //Do assertion
    BookingPojo actualData =ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
    System.out.println("actualData = " + actualData);

    assertEquals(200, response.statusCode());
    assertEquals(expectedData.getFirstname(), actualData.getFirstname());
    assertEquals(expectedData.getLastname(), actualData.getLastname());
    assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
    assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
    assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
    assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
    assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());



}

}
