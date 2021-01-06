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
