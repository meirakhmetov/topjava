package kz.meiir.topjava.repository.jdbc;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.repository.MealRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author Meiir Akhmetov on 01.09.2022
 */
@Repository
public class JdbcMealRepository implements MealRepository {
    @Override
    public Meal save(Meal meal, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id, int UserId) {
        return null;
    }

    @Override
    public Collection<Meal> getAll(int UserId) {
        return null;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startTime, LocalDateTime endTime, int userId) {
        return null;
    }
}
