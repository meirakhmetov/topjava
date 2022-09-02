package kz.meiir.topjava.service;

import kz.meiir.topjava.model.Role;
import kz.meiir.topjava.model.User;

import kz.meiir.topjava.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static kz.meiir.topjava.UserTestData.*;
import static kz.meiir.topjava.UserTestData.assertMatch;


/**
 * @author Meiir Akhmetov on 01.09.2022
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    static{
        //Only for postgres driver logginig
        //It uses java.util.Logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private UserService service;

    @Test
    public void creat() throws Exception{
        User newUser = new User(null,"New","new@new.kz","newPassword",1555,false,new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(),ADMIN,newUser,USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() throws Exception{
        service.create(new User(null,"Duplicate","user@yandex.ru","newPassword",Role.ROLE_USER));
    }


    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception{
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user,USER);
    }

    @Test
    public void getByEmail() throws Exception{
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }

    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all,ADMIN,USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdateName");
        updated.setCaloriesPerDay(330);
        service.update(updated);
        assertMatch(service.get(USER_ID),updated);
    }
}