package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.UserDto;
import geekgrains.java.section4.level1.mynet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{login}")
    public UserDto getUserData(@PathVariable String login) {
        return userService.getUserData(login);
    }
    @PostMapping
    public void setUserData(@RequestBody UserDto userDto) {
        userService.setUserData(userDto);
    }
}
