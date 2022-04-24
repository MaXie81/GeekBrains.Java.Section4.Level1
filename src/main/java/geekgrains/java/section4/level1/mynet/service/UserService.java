package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(String login) {
        UserDto userDto = new UserDto();
        Map.setUserDtoFromUser(userRepository.findByLogin(login).orElse(new User()), userDto);
        userDto.setPassword(null);
        return userDto;
    }

//    public void setUser(UserDto userDto) {
//        User user = new User();
//
//        userRepository.save()
//    }
}
