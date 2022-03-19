package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import platform.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findUserByUsername(String username);

//    final private Map<String, User> users = new ConcurrentHashMap<>();
//
//    public User findUserByUsername(String username) {
//        return users.get(username);
//    }
//
//    public void save(User user) {
//        users.put(user.getUsername(), user);
//    }
}
