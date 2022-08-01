package kz.meiir.topjava.util;

import kz.meiir.topjava.model.UserMeal;
import kz.meiir.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class UserMealUtil {
    public static void main(String[] args){
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2022, Month.JULY,30,10,0),"Завтрак", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,30,13,0),"Обед", 1000),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,30,20,0),"Ужин", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,0,0),"Еда на граничнное значение", 100),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,10,0),"Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,13,0),"Обед", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,20,0),"Ужин", 410)
        );
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7,0), LocalTime.of(12,0),2000);
    }

    private static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return null;
    }
    private static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return null;
    }

}
