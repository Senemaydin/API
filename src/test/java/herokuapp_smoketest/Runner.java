package herokuapp_smoketest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
//This class will run the classes below in this order.

        C01_CreateBooking.class,
        C02_UpdateBooking.class,
        C03_ReadBooking.class,
        C04_PatchBooking.class,
        C05_DeleteBooking.class,
        C06_ReadBooking_Negative.class

})
public class Runner {
}
