package kz.meiir.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime){
        return lt.compareTo(startTime)>=0 && lt.compareTo(endTime) <=0;
    }
    public static String toString(LocalDateTime ldt){
        return ldt == null ? "" :ldt.format(DATE_TIME_FORMATTER);
    }
}
