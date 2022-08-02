package kz.meiir.topjava.util;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.model.MealTo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

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

        System.out.println(getFilteredWithExcessByCycle(meals, LocalTime.of(7,0),LocalTime.of(12,0),2000));

    }

    public static List<MealTo> getFilteredWithExcessByCycle(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<MealTo> mealWithExcess = new ArrayList<>();
        meals.forEach(meal ->{
                if(TimeUtil.isBetweenInclusive(meal.getTime(), startTime,endTime)) {
                    mealWithExcess.add(creatWithExcess(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
                }
        });
        return mealWithExcess;
    }

    private static MealTo creatWithExcess(Meal meal, boolean excess){
        return new MealTo(meal.getDateTime(),meal.getDescription(), meal.getCalories(),excess);
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
