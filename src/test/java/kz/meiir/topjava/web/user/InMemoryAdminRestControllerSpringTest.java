package kz.meiir.topjava.web.user;

import kz.meiir.topjava.UserTestData;
import kz.meiir.topjava.model.Role;
import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.inmemory.InMemoryUserRepository;
import kz.meiir.topjava.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static kz.meiir.topjava.UserTestData.ADMIN;

/**
 * @author Meiir Akhmetov on 15.08.2022
 */
@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.init();
    }

    @Test
    public void testCreate() throws Exception{
        controller.create(new User(null,"Meiir","admin@ok.kz","password", Role.ROLE_ADMIN));
    }

    @Test
    public void delete() throws Exception {
        Collection<User> users = controller.getAll();
        int n = users.size();
        controller.delete(UserTestData.USER_ID);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(ADMIN, users.iterator().next());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception{
        controller.delete(10);
    }

}
