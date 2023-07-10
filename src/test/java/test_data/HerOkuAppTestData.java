package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public static Map<String, String> bookingdatesMapMethod(String checkin, String checkout){

        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2018-01-01");
        bookingdatesMap.put("checkout", "2019-01-01");

        return bookingdatesMap;

    }

    public static  Map<String, Object> expectedDataMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdatesMap, String additionalneeds){

        Map<String, Object> expectedData = new HashMap<>();
        if (firstname!=null){
            expectedData.put("firstname", firstname);
        }

        if (lastname!=null){
            expectedData.put("lastname", lastname);
        }

        if (totalprice!=null){
            expectedData.put("totalprice", totalprice);
        }

        if (depositpaid!=null){
            expectedData.put("depositpaid",depositpaid);
        }

        if (bookingdatesMap!=null){
            expectedData.put("bookingdates",bookingdatesMap);
        }

        if (additionalneeds!=null){
            expectedData.put("additionalneeds",additionalneeds);
        }

        return expectedData;

    }


}


