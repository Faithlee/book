package base;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间操作
 */
public class DateTest {

    public static void main(String[] args) {

//        testCalendar();
//        getDateRange();
        testTimeRange();
    }

    public static void testDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormat = format.format(date);
        System.out.println(dateFormat);

        String day = "2019-06-25";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(format1.parse(day));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCalendar(){
        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.YEAR) + "年" + c.get(Calendar.MONDAY) + "月" + c.get(Calendar.DATE) + "日");

        Date date = c.getTime();
        System.out.println("当前日期:" + date);

        System.out.println(c.getTimeInMillis());

        System.out.println(c.getTimeZone());
    }

    public static void testTimeRange() {
        Instant instant = Instant.now();
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);

        LocalDateTime firstDay = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastDay = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(firstDay);
        System.out.println(lastDay);

        LocalDate date = LocalDate.now(zoneId);
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(last);

        // last day
        LocalTime time = LocalTime.of(23, 59, 59);
        LocalDateTime dateTime1 = LocalDateTime.of(last, time);
        System.out.println(dateTime1.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
    }
}
