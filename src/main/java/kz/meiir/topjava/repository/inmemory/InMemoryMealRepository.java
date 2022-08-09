package kz.meiir.topjava.repository.inmemory;


import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.repository.MealRepository;
import kz.meiir.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Meiir Akhmetov on 04.08.2022
 */
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    {
        MealsUtil.MEALS.forEach(this::save);
    }
    @Override
    public Meal save(Meal meal) {
        if(meal.isNew()){
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        //trea case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

   /* @Override
    public Collection<Meal> sortByDate(){
        List<Map.Entry<Integer, Meal>> capitalList = new LinkedList<>(repository.entrySet());

        // call the sort() method of Collections
        Collections.sort(capitalList, (l1, l2) -> l1.getValue().getDateTime().compareTo(l2.getValue().getDateTime()));

        // create a new map
        LinkedHashMap result = new LinkedHashMap();

        // get entry from list to the map
        for (Map.Entry<Integer, Meal> entry : capitalList) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result.values();
    } */
}
