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
    private static final int DEFAULT_CALORIES_PER_DAY = 2000;
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
        LocalTime startTime = LocalTime.of(7,0);
        LocalTime endTime = LocalTime.of(12,0);

        List<MealTo> mealTo = getFiltered(meals, startTime, endTime, DEFAULT_CALORIES_PER_DAY);
        mealTo.forEach(System.out::println);
        System.out.println("--------");

        getFilteredByCycle(meals, startTime, endTime, DEFAULT_CALORIES_PER_DAY).forEach(System.out::println);
        System.out.println("--------");
        System.out.println(getFilteredByRecursion(meals, startTime, endTime, DEFAULT_CALORIES_PER_DAY));





    }



    public static List<MealTo> getFiltered(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
                );
        return meals.stream()
                .filter(um -> TimeUtil.isBetween(um.getTime(),startTime,endTime))
                .map(um -> new MealTo(um.getDateTime(),um.getDescription(),um.getCalories(),
                        caloriesSumByDate.get(um.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }



    public static List<MealTo> getFilteredByCycle(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<MealTo> mealsTo = new ArrayList<>();
        meals.forEach(meal ->{
                if(TimeUtil.isBetween(meal.getTime(), startTime,endTime)) {
                    mealsTo.add(createTo(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
                }
        });
        return mealsTo;
    }

    private static List<MealTo> getFilteredByRecursion(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<MealTo> result = new ArrayList<>();
        filterWithRecursion(new LinkedList<>(meals),startTime,endTime,caloriesPerDay, new HashMap<>(), result);
        return result;
    }

    private static void filterWithRecursion(LinkedList<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay,
                                            Map<LocalDate, Integer> dailyCaloriesMap, List<MealTo> result) {
        if(meals.isEmpty()) return;
        Meal meal = meals.pop();
        dailyCaloriesMap.merge(meal.getDate(),meal.getCalories(),Integer::sum);
        filterWithRecursion(meals,startTime,endTime,caloriesPerDay,dailyCaloriesMap,result);
        if(TimeUtil.isBetween(meal.getTime(),startTime,endTime)){
            result.add(createTo(meal, dailyCaloriesMap.get(meal.getDate())>caloriesPerDay));
        }
    }



    private static MealTo createTo(Meal meal, boolean excess){
        return new MealTo(meal.getDateTime(),meal.getDescription(), meal.getCalories(),excess);
    }




}
