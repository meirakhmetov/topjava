package kz.meiir.topjava.web.user;

import kz.meiir.topjava.model.User;
import kz.meiir.topjava.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

import static kz.meiir.topjava.util.ValidationUtil.assureIdConsistent;
import static kz.meiir.topjava.util.ValidationUtil.checkNew;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
@Controller
public class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id){
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user){
        log.info("create {}", user);
        checkNew(user);
        return service.creat(user);
    }

    public void delete(int id){
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id){
        log.info("update {} with id={}", user,id);
        assureIdConsistent(user,id);
        service.update(user);
    }
    public User getByMail(String email){
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
