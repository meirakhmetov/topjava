package kz.meiir.topjava.web.user;

import kz.meiir.topjava.UserTestData;
import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.inmemory.InMemoryUserRepository;
import kz.meiir.topjava.util.exception.NotFoundException;
import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collection;

import static kz.meiir.topjava.UserTestData.ADMIN;

/**
 * @author Meiir Akhmetov on 15.08.2022
 */
public class InMemoryAdminRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeClass
    public static void beforeClass(){
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass(){
        appCtx.close();
    }

    @Before
    public void setUp() throws Exception{
        //re-initialize
        InMemoryUserRepository repository = appCtx.getBean(InMemoryUserRepository.class);
        repository.init();
    }

    @Test
    public void delete() throws Exception {
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(),users.size());
        Assert.assertEquals(users.iterator().next(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception{
        controller.delete(5);
    }
}
