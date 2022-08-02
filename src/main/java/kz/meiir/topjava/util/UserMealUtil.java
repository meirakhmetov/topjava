package kz.meiir.topjava.util;

import kz.meiir.topjava.model.UserMeal;
import kz.meiir.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class UserMealUtil {
    public static void main(String[] args){
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2022, Month.JULY,30,10,0),"Завтрак", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,30,13,0),"Обед", 1000),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,30,20,0),"Ужин", 400),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,0,0),"Еда на граничнное значение", 1000),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,10,0),"Завтрак", 400),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,13,0),"Обед", 500),
                new UserMeal(LocalDateTime.of(2022, Month.JULY,31,20,0),"Ужин", 410)
        );
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7,0), LocalTime.of(12,0),2000);

        System.out.println("--------------By Cycles------------------");
        for(UserMealWithExcess meal:mealsTo){
            System.out.println(meal);
        }

        System.out.println("--------------By Stream--------------------");

        List<UserMealWithExcess> mealsTo2 = filteredByStreams(meals, LocalTime.of(12,0), LocalTime.of(17,0),2000);
        mealsTo2.forEach(System.out::println);




    }

    private static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> list = new ArrayList<UserMealWithExcess>();
        Map<LocalDate,Integer> map = new HashMap<LocalDate, Integer>();
        for(UserMeal meal:meals){
            map.put(meal.getDateTime().toLocalDate(),map.merge(meal.getDateTime().toLocalDate(),meal.getCalories(), Integer::sum));
        }
        System.out.println(map);

        for(UserMeal meal:meals){
            if(TimeUtil.isBetweenInclusive(meal.getDateTime().toLocalTime(),startTime,endTime)){
                list.add(new UserMealWithExcess(meal.getDateTime(),meal.getDescription(),meal.getCalories(),caloriesPerDay < map.get(meal.getDateTime().toLocalDate())));
            }
        }

        return list;
    }
    private static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate,Integer> caloriesSumByDay = meals.stream()
                .collect(
        //                Collectors.toMap(userMeal -> userMeal.getDateTime().toLocalDate(), UserMeal::getCalories, Integer::sum));
                        Collectors.groupingBy(u->u.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        System.out.println(caloriesSumByDay);

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenInclusive(meal.getDateTime().toLocalTime(),startTime,endTime))
                .map(meal -> new UserMealWithExcess(meal.getDateTime(),meal.getDescription(),meal.getCalories(),
                        caloriesPerDay < caloriesSumByDay.get(meal.getDateTime().toLocalDate())))
                .collect(Collectors.toList());


    }

}
