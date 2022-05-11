package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.dto.UserLoginDto;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import geekgrains.java.section4.level1.mynet.util.UserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private HashMap<String, User> userHashMap = new HashMap<>();

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

    public UserDto getUserData(String login) {
        UserDto userDto = new UserDto();
        User user = getUserByLogin(login);

        if (user != null) {
            Map.setUserDtoFromUser(user, userDto);
        }
        return userDto;
    }

    public void setUserData(String login, UserDto userDto) {
        User user = getUserByLogin(login);

        if (user != null) {
            if (userDto.getPassword() == null) userDto.setPassword(user.getPassword());
            Map.setUserFromUserDto(userDto, user);
            userRepository.save(user);
        };
    }

    public List<UserLoginDto> getFriendLoginList(String login) {
        List<UserLoginDto> friendLoginListDto = new ArrayList<>();
        User user = getUserByLogin(login);

        if (user != null) {
            for (User friend : user.getFriends()) {
                UserLoginDto userLoginDto = new UserLoginDto(friend.getLogin());
                friendLoginListDto.add(userLoginDto);
            }
        }
        return friendLoginListDto;
    }

    public List<UserLoginDto> getNonFriendLoginList(String login) {
        List<UserLoginDto> userLoginListDto = new ArrayList<>();
        User user = getUserByLogin(login);

        if (user != null) {
            List<User> userList = userRepository.findAll();

            for (User userCurrent : userList) {
                if (user.getLogin().equals(userCurrent.getLogin())) continue;
                if (user.getFriends().stream().filter(friend -> friend.getLogin().equals(userCurrent.getLogin())).count() > 0) continue;
                UserLoginDto userLoginDto = new UserLoginDto(userCurrent.getLogin());
                userLoginListDto.add(userLoginDto);
            }
        }
        return userLoginListDto;
    }

    public void addFriend(String login, List<UserLoginDto> userLoginListDto) {
        User user = getUserByLogin(login);

        if (user != null) {
            for (UserLoginDto userLoginDto : userLoginListDto) {
                User friendUser = getUserByLogin(userLoginDto.getLogin());
                if (friendUser != null) {
                    user.getFriends().add(friendUser);
                }
            }
            userRepository.save(user);
        }
    }

    public void deleteFriend(String login, List<UserLoginDto> userLoginListDto) {
        User user = getUserByLogin(login);

        if (user != null) {
            for (UserLoginDto userLoginDto : userLoginListDto) {
                User friendUser = getUserByLogin(userLoginDto.getLogin());
                if (friendUser != null) user.getFriends().remove(friendUser);
            }
            userRepository.save(user);
        }
    }

    public void addNewUser(UserDto userDto) {
        User user = getUserByLogin(userDto.getLogin());

        if (user == null) {
            User newUser = new UserBuilder(userDto.getLogin(), userDto.getPassword())
                    .setNickname(userDto.getNickname())
                    .setEmail(userDto.getEmail())
                    .build();
            userRepository.save(newUser);
        }
    }
}
