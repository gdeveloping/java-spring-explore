package tech.gdev.javabasicexplore.javabasic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

public class JavaDateTimeExplore {
    public static void main(String[] args) {
        JavaDateTimeExplore explore = new JavaDateTimeExplore();
//        explore.testInstant();
//        explore.testLocalDate();
//        explore.testTemporalAdjusters();
//        explore.testLocalTime();
//        explore.testZonedDateTime();
        explore.tstDateTimeFormatter();
//        explore.exploreSimpleDateFormat();
    }

    public void testInstant() {
        Instant now = Instant.now();
        System.out.println(now);
        Instant instant = Instant.now();
        Duration duration = Duration.between(now, instant);
        System.out.println(duration.toMillis());
    }

    public void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        LocalDate anotherDate = LocalDate.of(2023, 3, 1);
        Period period = localDate.until(anotherDate);
        long days = localDate.until(anotherDate, ChronoUnit.DAYS);

        System.out.println(localDate);  // 2023-12-12
        System.out.println(anotherDate);  // 2023-03-01
        System.out.println(localDate.getMonth().getValue());  // 12
        System.out.println(localDate.getDayOfMonth());  // 12
        System.out.println(localDate.getDayOfYear());  // 346

        System.out.println(period.getYears());  // 0
        System.out.println(period.getMonths());  // -9
        System.out.println(period.getDays());  // -11

        System.out.println(days);  // -286

        System.out.println(DayOfWeek.MONDAY.getValue());  // 1
    }

    public void testTemporalAdjusters() {
        LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate tuesday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println(firstDayOfMonth); // 2023-12-01
        System.out.println(lastDayOfMonth); // 2023-12-31
        System.out.println(tuesday); // 2023-12-12
    }

    public void testLocalTime() {
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localTime);  // 23:22:10.638790700
        System.out.println(localDateTime);  // 2023-12-12T23:22:10.638790700
    }

    public void testZonedDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime losAngeles = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Los_Angeles"));

        Period period = Period.between(zonedDateTime.toLocalDate(), losAngeles.toLocalDate());
        Duration duration = Duration.between(zonedDateTime, losAngeles);

        System.out.println(zonedDateTime);  // 2023-12-12T23:44:50.534756100+08:00[Asia/Singapore]
        System.out.println(period.getDays());  // 0
        System.out.println(duration.toHours());  // 16
    }

    public void tstDateTimeFormatter() {
        String isoDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());

        String localizedLong = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now());
        String localizedUsLong = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(Locale.US).format(ZonedDateTime.now());

        String patternDatetime = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm:ss").format(ZonedDateTime.now());

        LocalDate parsedDate = LocalDate.parse("2023-12-12");
        ZonedDateTime parsedZonedDateTime = ZonedDateTime.parse("2023-12-12 23:30:01+0800", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));

        System.out.println(isoDateTime);  // 2023-12-13T00:15:25.4853725
        System.out.println(localizedLong);  // 2023年12月13日 SGT 上午12:15:25
        System.out.println(localizedUsLong);  // December 13, 2023 at 12:15:25 AM SGT
        System.out.println(patternDatetime);  // 周三 2023-12-13 00:15:25
        System.out.println(parsedDate);  // 2023-12-12
        System.out.println(parsedZonedDateTime);  // 2023-12-12T23:30:01+08:00
        System.out.println(parsedZonedDateTime.getZone());  // +08:00
    }


    public void exploreSimpleDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = "2024-01-01 01:01:01";
        try {
            System.out.println(dateFormat.parse(time));
            dateFormat.format(new Date());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
