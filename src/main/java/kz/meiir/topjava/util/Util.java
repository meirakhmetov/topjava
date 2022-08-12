package kz.meiir.topjava.util;

import org.springframework.lang.Nullable;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
public class Util {
    public static <T extends Comparable<? super T>> boolean isBetweenInclusive(
            T value, @Nullable T start, @Nullable T end){
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);
    }
}
