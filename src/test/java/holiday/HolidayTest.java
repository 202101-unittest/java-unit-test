package holiday;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class HolidayTest {
    @Test
    public void today_is_xmas() {
        HolidayForTest holiday = new HolidayForTest();
        holiday.setToday(LocalDate.of(2000, 12, 25));
        assertEquals("Merry Xmas", holiday.sayHello());
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
