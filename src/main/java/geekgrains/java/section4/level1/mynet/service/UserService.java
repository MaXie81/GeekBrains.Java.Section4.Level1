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
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login).orElse(new User());
    }

    public UserDto getUserData(String login) {
        UserDto userDto = new UserDto();
        Map.setUserDtoFromUser(userRepository.findByLogin(login).orElse(new User()), userDto);
        return userDto;
    }

    public void setUserData(String login, UserDto userDto) {
        User user = userRepository.findByLogin(login).orElse(null);

        if (user != null) {
            if (userDto.getPassword() == null) userDto.setPassword(user.getPassword());
            Map.setUserFromUserDto(userDto, user);
            userRepository.save(user);
        };
    }

    public List<UserLoginDto> getFriendLoginList(String login) {
        List<UserLoginDto> friendLoginListDto = new ArrayList<>();
        User currentUser = userRepository.findByLogin(login).orElse(null);

        if (currentUser != null) {
            for (User friend : currentUser.getFriends()) {
                UserLoginDto userLoginDto = new UserLoginDto(friend.getLogin());
                friendLoginListDto.add(userLoginDto);
            }
        }
        return friendLoginListDto;
    }
    public List<UserLoginDto> getNonFriendLoginList(String login) {
        List<UserLoginDto> userLoginListDto = new ArrayList<>();
        User currentUser = userRepository.findByLogin(login).orElse(null);

        if (currentUser != null) {
            List<User> userList = userRepository.findAll();

            for (User user : userList) {
                if (currentUser.getLogin().equals(user.getLogin())) continue;
                if (currentUser.getFriends().stream().filter(friend -> friend.getLogin().equals(user.getLogin())).count() > 0) continue;
                UserLoginDto userLoginDto = new UserLoginDto(user.getLogin());
                userLoginListDto.add(userLoginDto);
            }
        }
        return userLoginListDto;
    }

    public void addFriend(String login, List<UserLoginDto> userLoginListDto) {
        User currentUser = userRepository.findByLogin(login).orElse(null);

        if (currentUser != null) {
            for (UserLoginDto userLoginDto : userLoginListDto) {
                User friendUser = userRepository.findByLogin(userLoginDto.getLogin()).orElse(null);
                if (friendUser != null) currentUser.getFriends().add(friendUser);
            }
            userRepository.save(currentUser);
        }
    }

    public void deleteFriend(String login, List<UserLoginDto> userLoginListDto) {
        User currentUser = userRepository.findByLogin(login).orElse(null);

        if (currentUser != null) {
            for (UserLoginDto userLoginDto : userLoginListDto) {
                User friendUser = userRepository.findByLogin(userLoginDto.getLogin()).orElse(null);
                if (friendUser != null) currentUser.getFriends().remove(friendUser);
            }
            userRepository.save(currentUser);
        }
    }

    public void addNewUser(UserDto userDto) {
        User currentUser = userRepository.findByLogin(userDto.getLogin()).orElse(null);

        if (currentUser == null) {
            User user = new UserBuilder(userDto.getLogin(), userDto.getPassword())
                    .setNickname(userDto.getNickname())
                    .setEmail(userDto.getEmail())
                    .build();
            userRepository.save(user);
        }
    }
}
