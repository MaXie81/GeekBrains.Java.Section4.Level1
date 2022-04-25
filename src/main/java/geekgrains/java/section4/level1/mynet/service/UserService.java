package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.UserDataDto;
import geekgrains.java.section4.level1.mynet.dto.UserFriendDto;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDataDto getUser(String login) {
        UserDataDto userDataDto = new UserDataDto();
        Map.setUserDtoFromUser(userRepository.findByLogin(login).orElse(new User()), userDataDto);
        return userDataDto;
    }

    public void setUser(UserDataDto userDataDto) {
        User user = userRepository.findByLogin(userDataDto.getLogin()).orElse(null);
        if (user != null) {
            if (userDataDto.getPassword() == null) userDataDto.setPassword(user.getPassword());
            Map.setUserFromUserDto(userDataDto, user);
            userRepository.save(user);
        };
    }

    public UserFriendDto getFriendList(String login) {
        UserFriendDto userFriendDto = new UserFriendDto();
        User user = userRepository.findByLogin(login).orElse(null);
        if (user != null) Map.setUserFriendDtoFromUser(user, userFriendDto);
        return userFriendDto;
    }
}
