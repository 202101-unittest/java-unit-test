package holiday;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HolidayTest {
    @Test
    public void today_is_xmas() {

        Holiday holiday = new Holiday();
        assertEquals("Merry Xmas", holiday.sayHello());
    }
}
