package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.UserDataDto;
import geekgrains.java.section4.level1.mynet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{login}")
    public UserDataDto getUser(@PathVariable String login) {
        return userService.getUser(login);
    }
    @PostMapping
    public void getUser(@RequestBody UserDataDto userDataDto) {
        userService.setUser(userDataDto);
    }
}
