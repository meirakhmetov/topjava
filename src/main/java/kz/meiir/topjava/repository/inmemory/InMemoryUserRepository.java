package kz.meiir.topjava.repository.inmemory;

import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
@Repository
public class InMemoryUserRepository implements UserRepository {

    public static final int User_ID =1;
    public static final int ADMIN_ID = 2;


    @Override
    public User save(User user) {
       if (user.isNew()){
           user.setId(counter.incrementAddGet());
       }
    }



    @Override
    public User get(int id) {
        Log.info("get {}", id);
        return null;
    }

    @Override
    public User getByEmail(String email) {
        Log.info("getByEmail {}", email);
        return null;

    }

    @Override
    public List<User> getAll() {
        Log.info("getAll");
        return Collections.emptyList();
    }
}
