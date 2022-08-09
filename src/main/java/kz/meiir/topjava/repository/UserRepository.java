package kz.meiir.topjava.repository;

import kz.meiir.topjava.model.User;

import java.util.List;

/**
 * @author Meiir Akhmetov on 08.08.2022
 */
public interface UserRepository {
    //null if not found, when update
    User save(User user);

    //false if not found
    boolean delete(int id);

    //null if not found
    User get(int id);

    //null if not found
    User getByEmail(String email);

    List<User> getAll();
}
