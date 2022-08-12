package kz.meiir.topjava.web.user;

import kz.meiir.topjava.model.User;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
@Controller
public class AdminRestController extends AbstractUserController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

    @Override
    public User getByMail(String email) {
        return super.getByMail(email);
    }
}
