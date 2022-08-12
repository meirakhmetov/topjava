package kz.meiir.topjava.service;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.repository.MealRepository;
import kz.meiir.topjava.util.DateTimeUtil;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static kz.meiir.topjava.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId){
        return checkNotFoundWithId(repository.get(id,userId),id);
    }

    public void delete(int id, int userId){
        checkNotFoundWithId(repository.delete(id,userId),id);
    }

    public List<Meal> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId){
        return repository.getBetween(
                DateTimeUtil.createDateTime(startDate,LocalDate.MIN, LocalTime.MIN),
                DateTimeUtil.createDateTime(endDate, LocalDate.MAX, LocalTime.MAX), userId);
    }

    public Collection<Meal> getAll(int userId){
        return repository.getAll(userId);
    }

    public void update(Meal meal, int userId){
        checkNotFoundWithId(repository.save(meal,userId), meal.getId());
    }

    public Meal creat(Meal meal, int userId){
        return repository.save(meal,userId);
    }

}
