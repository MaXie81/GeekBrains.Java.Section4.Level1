package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.dto.UserDataDto;
import geekgrains.java.section4.level1.mynet.entity.User;

public class Map {
    static public void setUserDtoFromUser(User user, UserDataDto userDataDto) {
       userDataDto.setLogin(user.getLogin());
       userDataDto.setNickname(user.getNickname());
       userDataDto.setEmail(user.getEmail());
    }
    static public void setUserFromUserDto(UserDataDto userDataDto, User user) {
        user.setPassword(userDataDto.getPassword());
        user.setNickname(userDataDto.getNickname());
        user.setEmail(userDataDto.getEmail());
    }
}
