package kz.meiir.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Meiir Akhmetov on 09.08.2022
 */
@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    static final int USER_ID=1;
    static final int ADMIN_ID=2;

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
