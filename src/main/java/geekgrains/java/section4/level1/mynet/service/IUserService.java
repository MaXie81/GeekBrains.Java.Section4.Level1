package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.entity.User;

import java.util.List;

public interface IUserService {
    User getUserByLogin(String login);
    List<User> getAllUsers();
    void saveUser(User user);
}
