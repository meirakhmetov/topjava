package kz.meiir.topjava.repository;


import kz.meiir.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author Meiir Akhmetov on 04.08.2022
 */
public interface MealRepository {
    //null if not found, when update
    Meal save(Meal meal, int userId);

    // false if not found
    boolean delete(int id, int userId);

    //null if not found
    Meal get(int id, int UserId);

    Collection<Meal> getAll(int UserId);

    List<Meal> getBetween(LocalDateTime startTime, LocalDateTime endTime, int userId);
}
