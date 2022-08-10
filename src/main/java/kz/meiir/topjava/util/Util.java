package kz.meiir.topjava.util;

import org.springframework.lang.Nullable;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
public class Util {
    public static <T extends Comparable<? super T>> boolean isBetweenInclusive(T value, @Nullable T start, @Nullable T end){
        return value.compareTo(start) >=0 && value.compareTo(end) <=0;
    }
}
