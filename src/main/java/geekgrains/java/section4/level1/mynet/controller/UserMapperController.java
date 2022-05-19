package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.mapper.UserIdentityMap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usermapper")
@RequiredArgsConstructor
public class UserMapperController {
    private final UserIdentityMap userMapper;

    @GetMapping("/{login}")
    public UserDto getUserData(@PathVariable String login) {
        return userMapper.getUser(login);
    }

    @PostMapping("/set")
    public void setUserData(@RequestBody UserDto userDto) {
        userMapper.setUser(userDto);
    }

    @PostMapping("/add")
    public void addNewUser(@RequestBody UserDto userDto) {
        userMapper.addUser(userDto);
    }

    @GetMapping("/delete/{login}")
    public void deleteNewUser(@PathVariable String login) {
        userMapper.removeUser(login);
    }
}