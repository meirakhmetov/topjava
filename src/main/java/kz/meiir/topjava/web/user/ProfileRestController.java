package kz.meiir.topjava.web.user;

import kz.meiir.topjava.model.User;
import org.springframework.stereotype.Controller;

import static kz.meiir.topjava.web.SecurityUtil.authUserId;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
@Controller
public class ProfileRestController extends AbstractUserController{

    public User get(){
        return super.get(authUserId());
    }

    public void delete(){
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
