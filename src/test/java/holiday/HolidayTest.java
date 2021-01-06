package holiday;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class HolidayTest {

    private HolidayForTest holiday = new HolidayForTest();

    @Test
    public void today_is_xmas() {
        giveToday(12, 25);
        sayHelloShouldBe("Merry Xmas");
    }

    @Test
    public void today_is_xmas_when_Dec_24() {
        giveToday(12, 24);
        sayHelloShouldBe("Merry Xmas");
    }

    @Test
    public void today_is_not_xmas() {
        giveToday(12, 26);
        sayHelloShouldBe("Today is not Xmas");
    }

    private void sayHelloShouldBe(String expected) {
        assertEquals(expected, holiday.sayHello());
    }

    private void giveToday(int month, int dayOfMonth) {
        holiday.setToday(LocalDate.of(2000, month, dayOfMonth));
    }

    private class HolidayForTest extends Holiday {
        private LocalDate today;

        @Override
        protected LocalDate getToday() {
            return this.today;
        }

        public void setToday(LocalDate today) {
            this.today = today;
        }
    }
}
