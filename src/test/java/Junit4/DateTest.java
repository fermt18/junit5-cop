package Junit4;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class DateTest {

    @Test
    public void test_random_days_last_year(){
        final int numberOfDays = 5;
        final LocalDate thisYearJan1 = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 01);
        final LocalDate lastYearJan1 = thisYearJan1.minus(1, ChronoUnit.YEARS);
        final boolean wasLeapYear = lastYearJan1.isLeapYear();

        final String wasWasNot = wasLeapYear ? "was" : "was not";
        final int expectationOfDaysInYear = wasLeapYear ? 366 : 365;
        final long daysBetween = Duration.between(lastYearJan1.atTime(0, 0), thisYearJan1.atTime(0, 0)).toDays();

        final String s = String.format("Last year was %d and %s a leap year. It had %d days", lastYearJan1.getYear(), wasWasNot, daysBetween);
        System.out.println(s);
    }
}
