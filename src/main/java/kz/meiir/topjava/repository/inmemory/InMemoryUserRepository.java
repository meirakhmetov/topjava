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
    private static final Logger Log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    @Override
    public User save(User user) {
        Log.info("save {}",user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        Log.info("delete {}", id);
        return true;
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
