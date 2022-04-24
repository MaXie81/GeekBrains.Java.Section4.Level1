package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.entity.User;

public class Map {
    static public void setUserDtoFromUser(User user, UserDto userDto) {
       userDto.setLogin(user.getLogin());
       userDto.setPassword(user.getPassword());
       userDto.setNickname(user.getNickname());
       userDto.setEmail(user.getEmail());
    }
    static public void setUserFromUserDto(UserDto userDto, User user) {
        user.setLogin(userDto.getLogin());
        user.setLogin(userDto.getPassword());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
    }
}
