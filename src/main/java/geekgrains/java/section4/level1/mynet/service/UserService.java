package geekgrains.java.section4.level1.mynet.service;

import geekgrains.java.section4.level1.mynet.dto.FriendDto;
import geekgrains.java.section4.level1.mynet.dto.UserDataDto;
import geekgrains.java.section4.level1.mynet.dto.FriendListDto;
import geekgrains.java.section4.level1.mynet.entity.User;
import geekgrains.java.section4.level1.mynet.repository.UserRepository;
import geekgrains.java.section4.level1.mynet.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public FriendListDto getFriendList(String login) {
        FriendListDto friendListDto = new FriendListDto();
        User user = userRepository.findByLogin(login).orElse(null);
        if (user != null) Map.setUserFriendDtoFromUser(user, friendListDto);
        return friendListDto;
    }

    public void addFriend(FriendDto friendDto) {
        User user = userRepository.findByLogin(friendDto.getUserLogin()).orElse(null);
        User friend = userRepository.findByLogin(friendDto.getFriendLogin()).orElse(null);
        if (user != null && friend != null) {
            user.getFriends().add(friend);
            userRepository.save(user);
        };
    }
}
