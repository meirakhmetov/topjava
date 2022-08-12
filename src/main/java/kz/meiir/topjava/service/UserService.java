package kz.meiir.topjava.service;

import kz.meiir.topjava.model.User;
import kz.meiir.topjava.repository.UserRepository;
import kz.meiir.topjava.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static kz.meiir.topjava.util.ValidationUtil.checkNotFoundWithId;
import static kz.meiir.topjava.util.ValidationUtil.checkNotFound;


/**
 * @author Meiir Akhmetov on 08.08.2022
 */
@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User creat(User user){
        return repository.save(user);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.get(id), id);
    }

    public User get(int id) throws NotFoundException{
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException{
        return checkNotFound(repository.getByEmail(email),"email="+email);
    }

    public List<User> getAll(){
        return repository.getAll();
    }

    public void update(User user) throws NotFoundException{
        checkNotFoundWithId(repository.save(user), user.getId());
    }


}
