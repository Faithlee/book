package base;

import java.time.*;

/**
 * 时间转化
 */
public class TimeTest {

    public static void main(String[] args) {
//        testLocalDate();
//        testLocalTime();
//        testLocalDateTime();
        testYear();
    }

    public static void testLocalDate() {
        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalDate date1 = LocalDate.ofYearDay(2014, 146);
        System.out.println(date1);

        LocalDate date2 = LocalDate.of(2014, Month.MARCH,21);
        System.out.println(date2);
    }

    public static void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalTime time1 = LocalTime.of(22, 33);
        System.out.println(time1);

        LocalTime time2 = LocalTime.ofSecondOfDay(5503);
        System.out.println(time2);
    }

    public static void testLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        LocalDateTime future = dateTime.plusHours(25).plusMinutes(3);
        System.out.println(future);
    }

    public static void testYear() {
        Year year = Year.now();
        System.out.println(year);

        year = year.plusYears(5);
        System.out.println(year);

        YearMonth yearMonth = year.atMonth(10);
        System.out.println(yearMonth);

        yearMonth = yearMonth.plusYears(5).minusMonths(3);
        System.out.println(yearMonth);

        MonthDay monthDay = MonthDay.now();
        System.out.println(monthDay);

        MonthDay monthDay1 = monthDay.with(Month.MAY).withDayOfMonth(23);
        System.out.println(monthDay1);
    }
}
