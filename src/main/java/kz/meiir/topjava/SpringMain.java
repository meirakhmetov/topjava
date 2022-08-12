package kz.meiir.topjava;

import kz.meiir.topjava.model.Role;
import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.UserRepository;
import kz.meiir.topjava.repository.inmemory.InMemoryUserRepository;
import kz.meiir.topjava.to.MealTo;
import kz.meiir.topjava.web.meal.MealRestController;
import kz.meiir.topjava.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
public class SpringMain {
    public static void main(String[] args){
        // java 7 automatic resource management
        try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")){
            System.out.println("\n Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames())+"\n");
            AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
            adminRestController.create(new User(null,"userName","email@mail.ru","password", Role.ROLE_ADMIN));
            System.out.println();

            MealRestController mealRestController=appCtx.getBean(MealRestController.class);
            List<MealTo> filteredMealsWithExcess =
                    mealRestController.getBetween(
                            LocalDate.of(2022, Month.JULY,30), LocalTime.of(7,0),
                            LocalDate.of(2022,Month.JULY,31), LocalTime.of(11,0));
                filteredMealsWithExcess.forEach(System.out::println);
        }
    }
}
