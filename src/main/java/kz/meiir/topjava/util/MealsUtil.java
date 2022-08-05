package kz.meiir.topjava.util;


import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.model.MealTo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import java.util.stream.Collectors;

/**
 * @author Meiir Akhmetov on 01.08.2022
 */
public class MealsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static final List<Meal> MEALS = Arrays.asList(
                new Meal(LocalDateTime.of(2022, Month.JULY,30,10,0),"Завтрак", 400),
                new Meal(LocalDateTime.of(2022, Month.JULY,30,13,0),"Обед", 1000),
                new Meal(LocalDateTime.of(2022, Month.JULY,30,20,0),"Ужин", 500),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,0,0),"Еда на граничнное значение", 100),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,10,0),"Завтрак", 1000),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,13,0),"Обед", 500),
                new Meal(LocalDateTime.of(2022, Month.JULY,31,20,0),"Ужин", 410)
    );


    public static List<MealTo> getTos(Collection<Meal> meals, int caloriesPerDay) {
        return getFiltered(meals,LocalTime.of(0,0), LocalTime.of(23,59),2000);
    }

   /* public static List<MealTo> getFilteredTos(Collection<Meal> meals, int caloriesPerDay, LocalTime startTime, LocalTime endTime){
        return getFiltered(meals, caloriesPerDay, (meal -> DateTimeUtil.isBetween(, startTime, endTime)));
    }*/
    public static List<MealTo> getFiltered(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)))
                ;
        return meals.stream()
                .filter(um -> DateTimeUtil.isBetween(um.getTime(),startTime,endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) >caloriesPerDay))
                .collect(Collectors.toList());
    }



    public static List<MealTo> getFilteredByCycle(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<MealTo> mealsTo = new ArrayList<>();
        meals.forEach(meal ->{
                if(DateTimeUtil.isBetween(meal.getTime(), startTime,endTime)) {
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
        if(DateTimeUtil.isBetween(meal.getTime(),startTime,endTime)){
            result.add(createTo(meal, dailyCaloriesMap.get(meal.getDate())>caloriesPerDay));
        }
    }



    private static MealTo createTo(Meal meal, boolean excess){
        return new MealTo(meal.getId(), meal.getDateTime(),meal.getDescription(), meal.getCalories(),excess);
    }




}
