package kz.meiir.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.repository.MealRepository;
import kz.meiir.topjava.util.MealsUtil;
import kz.meiir.topjava.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static kz.meiir.topjava.UserTestData.ADMIN_ID;
import static kz.meiir.topjava.UserTestData.USER_ID;


/**
 * @author Meiir Akhmetov on 04.08.2022
 */
@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepository.class);

    // Map userId -> mealRepository
    private Map<Integer, InMemoryBaseRepository<Meal>> useraMealsMap = new ConcurrentHashMap<>();
    {
        MealsUtil.MEALS.forEach(meal-> save(meal,USER_ID));

        save(new Meal(LocalDateTime.of(2022, Month.JULY, 30, 14,0),"Обед Админа",510), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2022, Month.JULY, 30, 21,0),"Ужин Админа",1500), ADMIN_ID);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        InMemoryBaseRepository<Meal> meals = useraMealsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
        return meals.save(meal);
    }

    @PostConstruct
    public void postConstruct(){
        LOG.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy(){
        LOG.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
       InMemoryBaseRepository<Meal> meals = useraMealsMap.get(userId);
       return meals!=null && meals.delete(id);
    }

    @Override
    public Meal get(int id,int userId) {
        InMemoryBaseRepository<Meal> meals = useraMealsMap.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllFilteres(userId, m->true);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return getAllFilteres(userId, m-> Util.isBetweenInclusive(m.getDateTime(), startDate, endDate));

    }

    private List<Meal> getAllFilteres(int userId, Predicate<Meal> filter){
        InMemoryBaseRepository<Meal> meals = useraMealsMap.get(userId);
        return meals == null ? Collections.emptyList():
                meals.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
