package kz.meiir.topjava.repository.inmemory;

import kz.meiir.topjava.UserTestData;
import org.springframework.stereotype.Repository;
import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static kz.meiir.topjava.UserTestData.ADMIN;
import static kz.meiir.topjava.UserTestData.USER;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init(){
        map.clear();
        map.put(UserTestData.USER_ID, USER);
        map.put(UserTestData.ADMIN_ID, ADMIN);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        return getCollection().stream()
                .filter(u->email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);

    }

}
