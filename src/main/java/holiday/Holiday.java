package holiday;

import java.time.LocalDate;

public class Holiday {
    public String sayHello() {
        LocalDate today = getToday();
        if (today.getMonthValue() == 12 && (today.getDayOfMonth() == 25 || today.getDayOfMonth() == 24)) {
            return "Merry Xmas";
        }
        return "Today is not Xmas";
    }

    protected LocalDate getToday() {
        return LocalDate.now();
    }
}
