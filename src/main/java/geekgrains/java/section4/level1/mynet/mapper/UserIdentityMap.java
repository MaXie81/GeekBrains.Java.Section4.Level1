package geekgrains.java.section4.level1.mynet.mapper;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserIdentityMap {
    private final UserMapper mapper;
    private final Map<String, UserDto> map;

    public UserDto getUser(String login) {
        UserDto user = map.get(login);
        if (user != null) return user;

        user = mapper.get(login);
        if (user != null) map.put(login, user);

        return user;
    }
    public void setUser(UserDto userDto) {
        UserDto user = map.get(userDto.getLogin());
        if (user != null) map.remove(user.getLogin());

        mapper.set(userDto);
    }
    public void addUser(UserDto userDto) {
        UserDto user = map.get(userDto.getLogin());
        if (user != null) return;

        mapper.add(userDto);
    }
    public void removeUser(String login) {
        UserDto user = map.get(login);
        if (user != null) map.remove(login);

        mapper.remove(login);
    }
}
