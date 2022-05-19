package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usermapper")
@RequiredArgsConstructor
public class UserMapperController {
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/{login}")
    public UserDto getUserData(@PathVariable String login) {
        return userDtoMapper.getUser(login);
    }

    @PostMapping("/set")
    public void setUserData(@RequestBody UserDto userDto) {
        userDtoMapper.setUser(userDto);
    }

    @PostMapping("/add")
    public void addNewUser(@RequestBody UserDto userDto) {
        userDtoMapper.addUser(userDto);
    }

    @GetMapping("/delete/{login}")
    public void deleteNewUser(@PathVariable String login) {
        userDtoMapper.deleteUser(login);
    }
}