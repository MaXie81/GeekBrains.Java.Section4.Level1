package geekgrains.java.section4.level1.mynet.util;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.entity.User;

public class Map {
    static public UserDto getUserDtoFromUser(User user) {
        return new UserDto(user.getUsername(), user.getPassword(), user.getEmail());
    }
}
