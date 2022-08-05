package kz.meiir.topjava.repository;


import kz.meiir.topjava.model.Meal;

import java.util.Collection;

/**
 * @author Meiir Akhmetov on 04.08.2022
 */
public interface MealRepository {
    //null if not found, when update
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    //null if not found
    Meal get(int id);

    Collection<Meal> getAll();
    Collection<Meal> sortByDate();
}
