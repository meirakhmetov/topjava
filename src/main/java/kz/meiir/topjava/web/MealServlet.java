package kz.meiir.topjava.web;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.repository.InMemoryMealRepository;
import kz.meiir.topjava.repository.MealRepository;
import kz.meiir.topjava.util.MealsUtil;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Meiir Akhmetov on 04.08.2022
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        repository = new InMemoryMealRepository();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String id=request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
        LocalDateTime.parse(request.getParameter("dateTime")),
        request.getParameter("description"),
        Integer.parseInt(request.getParameter("calories")));

        LOG.info(meal.isNew() ? "Creat {}" : "Update{}", meal);
        repository.save(meal);
        response.sendRedirect("meals?action=sortByDate");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action==null){
            LOG.info("getAll");
            request.setAttribute("meals",MealsUtil.getTos(repository.getAll(),MealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if(action.equals("delete")){
            int id = getId(request);
            LOG.info("DELETE {}",id);
            repository.delete(id);
            response.sendRedirect("meals");

        }else if(action.equals("sortByDate")){
            LOG.info("getAllSortByDate");
            request.setAttribute("meals",MealsUtil.getTos(repository.sortByDate(),MealsUtil.DEFAULT_CALORIES_PER_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);

        }else if(action.equals("create")){
            final Meal meal = new Meal(LocalDateTime.now(),"",1000);
            request.setAttribute("meal",meal);
            request.getRequestDispatcher("/mealForm.jsp").forward(request,response);

        }else if(action.equals("update")) {
            int id = getId(request);
            final Meal meal = repository.get(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
