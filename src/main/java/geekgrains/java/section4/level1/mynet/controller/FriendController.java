package geekgrains.java.section4.level1.mynet.controller;

import geekgrains.java.section4.level1.mynet.dto.UserLoginDto;
import geekgrains.java.section4.level1.mynet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final UserService userService;

    @GetMapping("/{login}")
    public List<UserLoginDto> getFriendList(@PathVariable String login) {
        return userService.getFriendLoginList(login);
    }
    @GetMapping("/find/{login}")
    public List<UserLoginDto> getUserList(@PathVariable String login) {
        return userService.getUserLoginList(login);
    }

    @PostMapping("/add/{login}")
    public void addFriend(@PathVariable String login, @RequestBody List<UserLoginDto> userLoginListDto) {
        userService.addFriend(login, userLoginListDto);
    }
    @PostMapping("/delete/{login}")
    public void deleteFriend(@PathVariable String login, @RequestBody List<UserLoginDto> userLoginListDto) {
        userService.deleteFriend(login, userLoginListDto);
    }
}
