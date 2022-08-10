package kz.meiir.topjava.web;

import kz.meiir.topjava.model.Meal;
import kz.meiir.topjava.util.MealsUtil;
import kz.meiir.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author Meiir Akhmetov on 04.08.2022
 */
public class MealServlet extends HttpServlet {

    private ConfigurableApplicationContext spingContext;
    private MealRestController mealController;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        spingContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealController = spingContext.getBean(MealRestController.class);
    }

    @Override
    public void destroy(){
        spingContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
            LocalDateTime.parse(request.getParameter("dateTime")),
            request.getParameter("description"),
            Integer.parseInt(request.getParameter("calories")));

        if(StringUtils.isEmpty(request.getParameter("id"))){
            mealController.create(meal);
        }else{
            mealController.update(meal, getId(request));
        }
        response.sendRedirect("meals");

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(action==null){
            request.setAttribute("meals",mealController.getAll());
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        } else if(action.equals("delete")){
            int id = getId(request);
            mealController.delete(id);
            response.sendRedirect("meals");
        }else if(action.equals("create")){
            final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),"",1000);
            request.setAttribute("meal",meal);
            request.getRequestDispatcher("/mealForm.jsp").forward(request,response);

        }else if(action.equals("update")) {
            int id = getId(request);
            final Meal meal = mealController.get(id);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
