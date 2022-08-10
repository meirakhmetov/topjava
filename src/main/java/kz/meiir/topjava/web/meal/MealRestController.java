package kz.meiir.topjava.web.meal;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.service.MealService;
import kz.meiir.topjava.to.MealTo;
import kz.meiir.topjava.util.MealsUtil;
import kz.meiir.topjava.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static kz.meiir.topjava.util.ValidationUtil.assureIdConsistent;
import static kz.meiir.topjava.util.ValidationUtil.checkNew;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
@Controller
public class MealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id){
        int userId = SecurityUtil.authUserId();
        LOG.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id){
        int userId = SecurityUtil.authUserId();
        LOG.info("delete meal {} for user {}", id, userId);
        service.delete(id,userId);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        LOG.info("get All for user{}",userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal create(Meal meal){
        int userId = SecurityUtil.authUserId();
        checkNew(meal);
        LOG.info("create {} for user {}", meal, userId);
        return service.creat(meal,userId);
    }

    public void update(Meal meal, int id){
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(meal,id);
        LOG.info("update {} for user {}", meal, userId);
        service.update(meal, userId);
    }
    public List<MealTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                   @Nullable LocalDate endDate, @Nullable LocalTime endTime){
        int userId = SecurityUtil.authUserId();
        LOG.info("getBetween dates({} - {}) time({} - {}) for user {}",
                startDate, endDate, startTime, endTime, userId);
        List<Meal> mealsDateFiltered = service.getBetweenDates(startDate, endDate, userId);
        return MealsUtil.getFilteredTos(mealsDateFiltered,SecurityUtil.authUserCaloriesPerDay(),startTime,endTime);
    }
}
