package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProxyService implements IUserService{
    private final UserRepository userRepository;
    private HashMap<String, User> userHashMap = new HashMap<>();

    @Override
    public User getUserByLogin(String login) {
        User user = userHashMap.get(login);

        if (user == null) {
            user = userRepository.findByLogin(login).orElse(null);
            if (user != null) {
                userHashMap.put(login, user);
            }
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();

        userHashMap.clear();
        userList.stream().forEach(user -> userHashMap.put(user.getLogin(), user));

        return userList;
    }

    @Override
    public void saveUser(User user) {
        userHashMap.remove(user.getLogin());
        userRepository.save(user);
    }
}
