package kz.meiir.topjava;

import kz.meiir.topjava.model.Role;
import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.UserRepository;
import kz.meiir.topjava.repository.inmemory.InMemoryUserRepository;
import kz.meiir.topjava.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
public class SpringMain {
    public static void main(String[] args){

        try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")){
            System.out.println("\n Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames())+"\n");
            AdminRestController adminRestController = appCtx.getBean(AdminRestController.class);
            adminRestController.create(new User(null,"Meiir","admin@meiir.kz","password", Role.ROLE_ADMIN));


        }


    }
}
