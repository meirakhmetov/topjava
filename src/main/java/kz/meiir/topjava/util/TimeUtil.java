package kz.meiir.topjava.util;

import java.time.LocalTime;

/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class TimeUtil {
    public static boolean isBetweenInclusive(LocalTime lt, LocalTime startTime, LocalTime endTime){
        return lt.compareTo(startTime)>=0 && lt.compareTo(endTime) <=0;
    }
}
