package kz.meiir.topjava.util;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.model.MealTo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class MealsUtil {
    public static void main(String[] args){
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2022, Month.JULY,30,10,0),"Завтрак", 500),
                new Meal(LocalDateTime.of(2022, Month.JULY,30,13,0),"Обед", 1000),
                new Meal(LocalDateTime.of(2022, Month.JULY,30,20,0),"Ужин", 500),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,0,0),"Еда на граничнное значение", 100),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,10,0),"Завтрак", 1000),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,13,0),"Обед", 500),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,20,0),"Ужин", 410)
        );
        List<MealTo> mealsWithExcess = getFilteredWithExcess(meals, LocalTime.of(7,0), LocalTime.of(12,0),2000);
        mealsWithExcess.forEach(System.out::println);

    }

    public static List<MealTo> getFilteredWithExcess(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
        return meals.stream()
                .filter(um -> TimeUtil.isBetweenInclusive(um.getTime(),startTime,endTime))
                .map(um -> new MealTo(um.getDateTime(),um.getDescription(),um.getCalories(),
                        caloriesSumByDate.get(um.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }


}
